package com.mlh.summoners.events

import com.mlh.summoners.Summoners
import com.mlh.summoners.client.renderers.CanidRenderer
import com.mlh.summoners.client.renderers.ScarabRenderer
import com.mlh.summoners.util.CANID
import com.mlh.summoners.util.SCARAB
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent

@Mod.EventBusSubscriber(modid = Summoners.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object ClientEventBusSubscriber {
    @SubscribeEvent
    fun onClientSetup(event: FMLClientSetupEvent) {
        RenderingRegistry.registerEntityRenderingHandler(CANID) { renderManagerIn: EntityRendererManager ->
            CanidRenderer(renderManagerIn)
        }
        RenderingRegistry.registerEntityRenderingHandler(SCARAB) { renderManagerIn: EntityRendererManager ->
            ScarabRenderer(renderManagerIn)
        }
    }

}