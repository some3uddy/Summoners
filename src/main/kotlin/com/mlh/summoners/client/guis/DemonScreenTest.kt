package com.mlh.summoners.client.guis

import com.mlh.summoners.Summoners
import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.item.ItemStack
import net.minecraft.item.Items.DIAMOND
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.MathHelper
import net.minecraft.util.text.StringTextComponent
import net.minecraft.util.text.TranslationTextComponent
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

class DemonScreenTest : Screen(TranslationTextComponent("variable.one")) {
    companion object {
        private const val TEXTURE_WIDTH = 105
        private const val TEXTURE_HEIGHT = 226

        private const val INVENTORY_WIDTH = 105
        private const val INVENTORY_HEIGHT = 167

        private val DEMON_SELECTION_TEXTURE = ResourceLocation(Summoners.ID, "textures/gui/demon_selection.png")

        @OnlyIn(Dist.CLIENT)
        fun open() {
            Minecraft.getInstance().displayGuiScreen(DemonScreenTest())
        }
    }

    private val demonAmount = 10
    private val needsScrolling = demonAmount > 7
    private var firstVisibleCell = 0
    private var isScrollWheelClicked = false

    override fun init() {
        super.init()
        val xPos = ((width - INVENTORY_WIDTH) / 2) + 5
        var yPos = ((height - INVENTORY_HEIGHT) / 2) + 18

        for (i in 0 until demonAmount) {// TODO: maybe only 6 buttons and change the values every time
            addButton(DemonButton(i, xPos, yPos) { pressedButton: Button ->
                val pressedDemonButton = pressedButton as DemonButton
                Summoners.LOGGER.info("Pressed button with index: ${pressedDemonButton.index}")
                // reload other parts depending on this (or maybe do this lower?)
            })
            yPos += 20
        }
    }

    override fun render(matrixStack: MatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        renderBackground(matrixStack)
        super.render(matrixStack, mouseX, mouseY, partialTicks)

        val xPosBeginning: Int = (width - INVENTORY_WIDTH) / 2
        val yPosBeginning: Int = (height - INVENTORY_HEIGHT) / 2
        var yPos = yPosBeginning + 17

        RenderSystem.pushMatrix()
        RenderSystem.enableRescaleNormal()
        minecraft!!.getTextureManager().bindTexture(DEMON_SELECTION_TEXTURE)
        renderScrollWheel(matrixStack, xPosBeginning, yPosBeginning)
        for (i in 0 until demonAmount) {
            // check if this cell is scrolled out of view
            if (needsScrolling && i < firstVisibleCell || i >= 7 + firstVisibleCell) {
                buttons[i].visible = false
                continue
            }

            val itemstack2 = ItemStack(DIAMOND)
            val itemstack3 = ItemStack(DIAMOND)
            itemRenderer.zLevel = 100.0f
            val actualYPos = yPos + 2
            itemRenderer.renderItemAndEffectIntoGuiWithoutEntity(itemstack2, xPosBeginning + 5 + 35 - 40, actualYPos) // remove -40 (or don't lol)
            itemRenderer.renderItemOverlays(font, itemstack2, xPosBeginning + 5 + 35, actualYPos)
            itemRenderer.renderItemAndEffectIntoGuiWithoutEntity(itemstack3, xPosBeginning + 5 + 68, actualYPos)
            itemRenderer.renderItemOverlays(font, itemstack3, xPosBeginning + 5 + 68, actualYPos)

            itemRenderer.zLevel = 0.0f

            buttons[i].visible = true
            buttons[i].y = actualYPos - 1 // TODO: maybe only 6 buttons and change the values every time

            yPos += 20
        }

        // possibly render other parts of the gui here


        RenderSystem.popMatrix()
        RenderSystem.enableDepthTest()
    }

    private fun renderScrollWheel(matrixStack: MatrixStack, xPos: Int, yPos: Int) {
        val amountOverLimit = demonAmount + 1 - 7
        if (amountOverLimit > 1) {
            val j = 139 - (27 + (amountOverLimit - 1) * 139 / amountOverLimit)
            val k = 1 + j / amountOverLimit + 139 / amountOverLimit
            val l = 113
            var i1 = l.coerceAtMost(firstVisibleCell * k)
            if (firstVisibleCell == amountOverLimit - 1) {
                i1 = l
            }
            blit(matrixStack, xPos + 94, yPos + 18 + i1, blitOffset, 0.0f, 199.0f, 6, 27, TEXTURE_HEIGHT, TEXTURE_WIDTH)
        } else {
            blit(matrixStack, xPos + 94, yPos + 18, blitOffset, 6.0f, 199.0f, 6, 27, TEXTURE_HEIGHT, TEXTURE_WIDTH)
        }
    }

    override fun renderBackground(matrixStack: MatrixStack) {
        super.renderBackground(matrixStack)
        minecraft!!.getTextureManager().bindTexture(DEMON_SELECTION_TEXTURE)
        val distanceX = (width - INVENTORY_WIDTH) / 2
        val disctanceY = (height - INVENTORY_HEIGHT) / 2
        blit(matrixStack, distanceX, disctanceY, 0f, 0f, INVENTORY_WIDTH, INVENTORY_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT)
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean {
        if (needsScrolling) {
            val numberOfCellsOverLimit = demonAmount - 7
            firstVisibleCell = (firstVisibleCell - delta).toInt()
            firstVisibleCell = MathHelper.clamp(firstVisibleCell, 0, numberOfCellsOverLimit)
        }
        return true
    }

    override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, dragX: Double, dragY: Double): Boolean {// TODO: check if remove To double and float
        return if (isScrollWheelClicked) {
            val j = 18
            val k = j + 139
            val numberOfCellsOverLimit = demonAmount - 7
            var f = (mouseY - j - 13.5f) / ((k - j) - 27.0f)
            f = f * numberOfCellsOverLimit + 0.5f
            firstVisibleCell = MathHelper.clamp(f.toInt(), 0, numberOfCellsOverLimit)
            true
        } else {
            super.mouseDragged(mouseX, mouseY, button, dragX, dragY)
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        isScrollWheelClicked = false
        val i: Int = (width - INVENTORY_WIDTH) / 2
        val j: Int = (height - INVENTORY_HEIGHT) / 2
        if (needsScrolling && mouseX > (i + 94) && mouseX < (i + 94 + 6) && mouseY > (j + 18) && mouseY <= (j + 18 + 139 + 1)) {
            isScrollWheelClicked = true
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun isPauseScreen(): Boolean = false

    @OnlyIn(Dist.CLIENT)
    inner class DemonButton(val index: Int, xPos: Int, yPos: Int, pressedAction: IPressable)
        : Button(xPos, yPos, 89, 20, StringTextComponent("$index"), pressedAction) {
        init {
            visible = false
        }
    }

}