package com.mlh.summoners.util

import com.mlh.summoners.Summoners
import net.minecraft.block.Block
import net.minecraft.item.BlockItem

class BlockItemBase(block: Block) : BlockItem(block, Properties().group(Summoners.CUSTOM_TAB))