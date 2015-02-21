package com.easyforger.base

import net.minecraft.block.Block
import net.minecraft.item.{ItemStack, Item}

trait ItemStackConversions {
  implicit def toItemStack(item: Item): ItemStack = new ItemStack(item, 1)
  implicit def toItemStack(block: Block): ItemStack = new ItemStack(block, 1)
}
