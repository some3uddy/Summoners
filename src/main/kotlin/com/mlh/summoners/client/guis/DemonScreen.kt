package com.mlh.summoners.client.guis

import com.mlh.summoners.Summoners
import com.mlh.summoners.util.CORUNDUM_BLOCK_ITEM
import com.mlh.summoners.util.CORUNDUM_ITEM
import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.client.gui.widget.button.Button.IPressable
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.Items.DIAMOND_BLOCK
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.MathHelper
import net.minecraft.util.text.StringTextComponent
import net.minecraft.util.text.TranslationTextComponent
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

class DemonScreen : Screen(TranslationTextComponent("variable.one")) {
    companion object {
        private val DEMON_SELECTION_TEXTURE = ResourceLocation(Summoners.ID, "textures/gui/demon_selection.png")
        private const val WIDTH = 100
        private const val HEIGHT = 166
    }

    private val buttons = arrayOfNulls<TradeButton>(7)
    private val amountOfElements = 10// TODO: change value 10 to whatever #################################
    private val guiTop = 0 // TODO: see where else this is required and how it works
    private var variableInt = 0
    private var variableBool = false

    init {
        super.init()
        val i: Int = (width - WIDTH) / 2
        val j: Int = (height - HEIGHT) / 2
        var k = j + 16 + 2

        for (l in 0..6) {
            buttons[l] = addButton(TradeButton(i + 5, k, l, IPressable { p_214132_1_: Button? ->
                if (p_214132_1_ is TradeButton) {
                    //this.selectedMerchantRecipe = (p_214132_1_ as TradeButton).func_212937_a() + this.field_214139_n
                    //this.func_195391_j()
                }
            }))
            k += 20
        }
    }

    override fun render(matrixStack: MatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.renderBackground(matrixStack)
        super.render(matrixStack, mouseX, mouseY, partialTicks)


        val relX /* old i */: Int = (width - WIDTH) / 2
        val relY/* old j */: Int = (height - HEIGHT) / 2
        var k = relY + 17  // var k = j + 16 + 1
        val l = relX + 10 // val l = i + 5 + 5
        RenderSystem.pushMatrix()
        RenderSystem.enableRescaleNormal()
        minecraft!!.getTextureManager().bindTexture(DEMON_SELECTION_TEXTURE)

        func_238840_a_(matrixStack, relX, relY, amountOfElements)

        var i1 = 0
        for (i in 1..amountOfElements) {
            if (amountOfElements > 7 && (i1 < variableInt || i1 >= 7 + variableInt)) {
                ++i1
            } else {
                val itemstack = ItemStack(Items.APPLE)
                val itemstack1 = ItemStack(Items.STONE)
                val itemstack2 = ItemStack(Items.EMERALD)
                val itemstack3 = ItemStack(Items.DIAMOND)
                itemRenderer.zLevel = 100.0f
                val j1 = k + 2
                func_238841_a_(matrixStack, itemstack1, itemstack, l, j1)
                if (!itemstack2.isEmpty) {
                    itemRenderer.renderItemAndEffectIntoGuiWithoutEntity(itemstack2, i + 5 + 35, j1)
                    itemRenderer.renderItemOverlays(font, itemstack2, i + 5 + 35, j1)
                }
                this.func_238842_a_(matrixStack, false, i, j1) // TODO: check what false / true does
                itemRenderer.renderItemAndEffectIntoGuiWithoutEntity(itemstack3, i + 5 + 68, j1)
                itemRenderer.renderItemOverlays(font, itemstack3, i + 5 + 68, j1)
                itemRenderer.zLevel = 0.0f
                k += 20
                ++i1
            }
        }

        //TODO: check if needed
/*
        val k1: Int = selectedIndex
        val merchantoffer1: MerchantOffer = merchantoffers.get(k1)
        if (this.container.func_217042_i()) {
            this.func_238839_a_(matrixStack, i, j, merchantoffer1)
        }

        if (merchantoffer1.hasNoUsesLeft() && this.isPointInRegion(186, 35, 22, 21, mouseX.toDouble(), mouseY.toDouble()) && this.container.func_223432_h()) {
            this.renderTooltip(matrixStack, MerchantScreen.field_243353_D, mouseX, mouseY)
        }
*/
        for (button in buttons) {
            button.visible = true
        }


/*
        for (`merchantscreen$tradebutton` in this.field_214138_m) {
            if (`merchantscreen$tradebutton`.isHovered()) {
                `merchantscreen$tradebutton`.renderToolTip(matrixStack, mouseX, mouseY)
            }
            `merchantscreen$tradebutton`.visible = `merchantscreen$tradebutton`.field_212938_a < this.container.getOffers().size
        }
*/
        RenderSystem.popMatrix()
        RenderSystem.enableDepthTest()


        //renderHoveredTooltip(matrixStack, mouseX, mouseY)

        this.renderTooltip(matrixStack, ItemStack(DIAMOND_BLOCK), mouseX, mouseY)


    }

    private fun func_238840_a_(p_238840_1_: MatrixStack, p_238840_2_: Int, p_238840_3_: Int, amountOfElements: Int) {
        val i = amountOfElements + 1 - 7
        if (i > 1) {
            val j = 139 - (27 + (i - 1) * 139 / i)
            val k = 1 + j / i + 139 / i
            val l = 113
            var i1 = 113.coerceAtMost(variableInt * k)
            if (variableInt == i - 1) {
                i1 = 113
            }
            blit(p_238840_1_, p_238840_2_ + 94, p_238840_3_ + 18 + i1, blitOffset, 0.0f, 199.0f, 6, 27, 256, 512)
        } else {
            blit(p_238840_1_, p_238840_2_ + 94, p_238840_3_ + 18, blitOffset, 6.0f, 199.0f, 6, 27, 256, 512)
        }
    }

    //TODO: see if i moved the offset etc...
    private fun func_238841_a_(p_238841_1_: MatrixStack, p_238841_2_: ItemStack, p_238841_3_: ItemStack, p_238841_4_: Int, p_238841_5_: Int) {
        itemRenderer.renderItemAndEffectIntoGuiWithoutEntity(p_238841_2_, p_238841_4_, p_238841_5_)
        if (p_238841_3_.count == p_238841_2_.count) {
            itemRenderer.renderItemOverlays(font, p_238841_2_, p_238841_4_, p_238841_5_)
        } else {
            itemRenderer.renderItemOverlayIntoGUI(font, p_238841_3_, p_238841_4_, p_238841_5_, if (p_238841_3_.count == 1) "1" else null)
            itemRenderer.renderItemOverlayIntoGUI(font, p_238841_2_, p_238841_4_ + 14, p_238841_5_, if (p_238841_2_.count == 1) "1" else null)
            minecraft!!.getTextureManager().bindTexture(DEMON_SELECTION_TEXTURE)
            blitOffset += 300
            blit(p_238841_1_, p_238841_4_ + 7, p_238841_5_ + 12, blitOffset, 0.0f, 176.0f, 9, 2, 256, 512)
            blitOffset -= 300
        }
    }


    //TODO: see if i moved the offset etc...
    private fun func_238842_a_(p_238842_1_: MatrixStack, isEmpty: Boolean, p_238842_3_: Int, p_238842_4_: Int) {
        RenderSystem.enableBlend()
        minecraft!!.getTextureManager().bindTexture(DEMON_SELECTION_TEXTURE)
        if (isEmpty) {
            blit(p_238842_1_, p_238842_3_ + 5 + 35 + 20, p_238842_4_ + 3, blitOffset, 25.0f, 171.0f, 10, 9, 256, 512)
        } else {
            blit(p_238842_1_, p_238842_3_ + 5 + 35 + 20, p_238842_4_ + 3, blitOffset, 15.0f, 171.0f, 10, 9, 256, 512)
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        variableBool = false
        val i: Int = (width - WIDTH) / 2
        val j: Int = (height - HEIGHT) / 2
        if (amountOfElements > 7 && mouseX > (i + 94).toDouble() && mouseX < (i + 94 + 6).toDouble() && mouseY > (j + 18).toDouble() && mouseY <= (j + 18 + 139 + 1).toDouble()) {
            variableBool = true
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean {
        if (amountOfElements > 7) {
            val j = amountOfElements - 7
            variableInt = (variableInt.toDouble() - delta).toInt()
            variableInt = MathHelper.clamp(variableInt, 0, j)
        }
        return true
    }

    override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, dragX: Double, dragY: Double): Boolean {
        return if (variableBool) {
            val j: Int = guiTop + 18
            val k = j + 139
            val l = amountOfElements - 7
            var f = (mouseY.toFloat() - j.toFloat() - 13.5f) / ((k - j).toFloat() - 27.0f)
            f = f * l.toFloat() + 0.5f
            variableInt = MathHelper.clamp(f.toInt(), 0, l)
            true
        } else {
            super.mouseDragged(mouseX, mouseY, button, dragX, dragY)
        }
    }


    @OnlyIn(Dist.CLIENT)
    inner class TradeButton(p_i50601_2_: Int, p_i50601_3_: Int, val field_212938_a: Int, p_i50601_5_: IPressable) : Button(p_i50601_2_, p_i50601_3_, 89, 20, StringTextComponent.EMPTY, p_i50601_5_) {
        init {
            visible = false
        }

        fun func_212937_a(): Int {
            return field_212938_a
        }

        override fun renderToolTip(matrixStack: MatrixStack, mouseX: Int, mouseY: Int) {
            if (isHovered) {
                if (mouseX < x + 20) {
                    val itemStack = ItemStack(CORUNDUM_ITEM)
                    renderTooltip(matrixStack, itemStack, mouseX, mouseY)
                } else if (mouseX < x + 50 && mouseX > x + 30) {
                    val itemStack = ItemStack(CORUNDUM_BLOCK_ITEM)
                    renderTooltip(matrixStack, itemStack, mouseX, mouseY)
                } else if (mouseX > x + 65) {
                    val itemStack = ItemStack(CORUNDUM_BLOCK_ITEM)
                    renderTooltip(matrixStack, itemStack, mouseX, mouseY)
                }
            }
        }


    }

}