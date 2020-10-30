package com.mlh.summoners.blocks

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraftforge.common.ToolType

object CorundumBlock : Block(
        Properties.create(Material.IRON)
                .hardnessAndResistance(5f, 6f)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .setRequiresTool()
)