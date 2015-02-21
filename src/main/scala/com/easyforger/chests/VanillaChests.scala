package com.easyforger.chests

import net.minecraft.item.ItemStack
import net.minecraft.util.WeightedRandomChestContent
import net.minecraftforge.common.ChestGenHooks

object VanillaChests {
  def addChestContent(chest: String, item: ItemStack, minStack: Int, maxStack: Int, chance: Int) =
    ChestGenHooks.addItem(chest, new WeightedRandomChestContent(item, minStack, maxStack, chance))
}
