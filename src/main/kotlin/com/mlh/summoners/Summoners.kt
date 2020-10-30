package com.mlh.summoners

import com.mlh.summoners.entities.CanidEntity
import com.mlh.summoners.entities.ScarabEntity
import com.mlh.summoners.events.KeyInputEvent
import com.mlh.summoners.util.CANID
import com.mlh.summoners.util.CORUNDUM_ITEM
import com.mlh.summoners.util.SCARAB
import com.mlh.summoners.util.registerRegistry
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.fml.DeferredWorkQueue
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS


@Mod(Summoners.ID)
object Summoners {
    val LOGGER: Logger = LogManager.getLogger()
    const val ID = "summoners"

    val CUSTOM_TAB: ItemGroup = object : ItemGroup("summoners_tab") {
        override fun createIcon(): ItemStack {
            return ItemStack(CORUNDUM_ITEM)
        }
    }

    init {
        registerRegistry()

        MOD_BUS.addListener(::setup)
        FORGE_BUS.addListener(EventPriority.NORMAL, KeyInputEvent::onKeyInput)
    }

    private fun setup(event: FMLCommonSetupEvent) {
        LOGGER.log(Level.INFO, "common setup")

        // event.enqueueWork {} ?

        DeferredWorkQueue.runLater {
            GlobalEntityTypeAttributes.put(CANID, CanidEntity.getAttributes().create())
            GlobalEntityTypeAttributes.put(SCARAB, ScarabEntity.getAttributes().create())
        }

    }

}