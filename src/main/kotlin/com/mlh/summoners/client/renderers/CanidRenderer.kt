package com.mlh.summoners.client.renderers

import com.mlh.summoners.Summoners
import com.mlh.summoners.client.models.CanidModel
import com.mlh.summoners.entities.CanidEntity
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.util.ResourceLocation

class CanidRenderer(renderManager: EntityRendererManager) : MobRenderer<CanidEntity, CanidModel>(renderManager, CanidModel(), 0.6f) {
    companion object {
        private val TEXTURE = ResourceLocation(Summoners.ID, "textures/entity/canid.png")
    }

    override fun getEntityTexture(entity: CanidEntity): ResourceLocation {
        return TEXTURE
    }


}