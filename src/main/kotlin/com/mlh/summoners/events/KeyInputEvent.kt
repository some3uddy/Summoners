package com.mlh.summoners.events

import com.mlh.summoners.Summoners
import com.mlh.summoners.client.guis.DemonScreenTest
import com.mlh.summoners.util.OPEN_DEMON_GUI_KEY_BIND
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.InputEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = Summoners.ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = [Dist.CLIENT])
object KeyInputEvent {
    @SubscribeEvent
    fun onKeyInput(event: InputEvent.KeyInputEvent) {
        when (event.key) {
            OPEN_DEMON_GUI_KEY_BIND.key.keyCode -> DemonScreenTest.open()
        }
    }
}