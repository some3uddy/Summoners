package com.mlh.summoners.util

import com.mlh.summoners.Summoners
import com.mlh.summoners.blocks.CorundumBlock
import com.mlh.summoners.entities.CanidEntity
import com.mlh.summoners.entities.ScarabEntity
import net.minecraft.client.settings.KeyBinding
import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.registries.ForgeRegistries
import org.lwjgl.glfw.GLFW
import thedarkcolour.kotlinforforge.forge.KDeferredRegister
import thedarkcolour.kotlinforforge.forge.MOD_BUS

private val BLOCK_REGISTRY = KDeferredRegister(ForgeRegistries.BLOCKS, Summoners.ID)
private val ITEM_REGISTRY = KDeferredRegister(ForgeRegistries.ITEMS, Summoners.ID)
private val ENTITY_REGISTRY = KDeferredRegister(ForgeRegistries.ENTITIES, Summoners.ID)

fun registerRegistry() {
    BLOCK_REGISTRY.register(MOD_BUS)
    ITEM_REGISTRY.register(MOD_BUS)
    ENTITY_REGISTRY.register(MOD_BUS)
}

//register keybindings here
val OPEN_DEMON_GUI_KEY_BIND = KeyBinding("key.open_demon_gui", GLFW.GLFW_KEY_G, "key.category.summoners").also { ClientRegistry.registerKeyBinding(it) }

//register blocks here
val CORUNDUM_BLOCK by BLOCK_REGISTRY.register("corundum_block") { CorundumBlock }


// register block items here
val CORUNDUM_BLOCK_ITEM by ITEM_REGISTRY.register("corundum_block") { BlockItemBase(CORUNDUM_BLOCK) }


// register items here
val CORUNDUM_ITEM by ITEM_REGISTRY.register("corundum") { ItemBase() }


// register entities here
val CANID: EntityType<CanidEntity> by ENTITY_REGISTRY.register("canid") {
    EntityType.Builder.create(::CanidEntity, EntityClassification.CREATURE)
            .size(1f / (16f / 12.5f), 1f)
            .build(ResourceLocation(Summoners.ID, "canid").toString())
}
val SCARAB: EntityType<ScarabEntity> by ENTITY_REGISTRY.register("scarab") {
    EntityType.Builder.create(::ScarabEntity, EntityClassification.CREATURE)
            .size(0.4f, 0.3f)
            .build(ResourceLocation(Summoners.ID, "scarab").toString())
}





