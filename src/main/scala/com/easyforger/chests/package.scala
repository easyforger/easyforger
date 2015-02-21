package com.easyforger

import net.minecraft.item.ItemStack
import net.minecraftforge.common.ChestGenHooks

package object chests {
  // TODO: replace String chest with enum, for correctness and compile time support
  def dungeonChestContent(item: ItemStack, minStack: Int, maxStack: Int, chance: Int): Unit =
    addChestContent(ChestGenHooks.DUNGEON_CHEST, item, minStack, maxStack, chance)
  
  def addChestContent(chest: String, item: ItemStack, minStack: Int, maxStack: Int, chance: Int): Unit =
    VanillaChests.addChestContent(chest, item, minStack, maxStack, chance)
  
  def removeChestContent(chest: String, item: ItemStack): Unit =
    VanillaChests.removeChestContent(chest, item)
}
