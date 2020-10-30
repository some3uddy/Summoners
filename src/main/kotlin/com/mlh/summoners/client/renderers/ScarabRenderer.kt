package com.mlh.summoners.client.renderers

import com.mlh.summoners.Summoners
import com.mlh.summoners.client.models.ScarabModel
import com.mlh.summoners.entities.ScarabEntity
import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.util.ResourceLocation

class ScarabRenderer(renderManager: EntityRendererManager) : MobRenderer<ScarabEntity, ScarabModel>(renderManager, ScarabModel(), 0.7f) {
    companion object {
        private val TEXTURE = ResourceLocation(Summoners.ID, "textures/entity/scarab.png")
    }

    override fun getEntityTexture(entity: ScarabEntity): ResourceLocation {
        return TEXTURE
    }

    override fun preRenderCallback(entitylivingbaseIn: ScarabEntity, matrixStackIn: MatrixStack, partialTickTime: Float) {
        matrixStackIn.scale(0.5f, 0.5f, 0.5f)
    }

}