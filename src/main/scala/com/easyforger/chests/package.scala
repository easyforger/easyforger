package com.easyforger

import net.minecraft.item.ItemStack
import net.minecraftforge.common.ChestGenHooks

package object chests {
  // TODO: replace String chest with enum, for correctness and compile time support
  def dungeonChestContent(item: ItemStack, minStack: Int, maxStack: Int, chance: Int): Unit =
    chestContent(ChestGenHooks.DUNGEON_CHEST, item, minStack, maxStack, chance)
  
  def chestContent(chest: String, item: ItemStack, minStack: Int, maxStack: Int, chance: Int): Unit =
    VanillaChests.addChestContent(chest, item, minStack, maxStack, chance)
}
