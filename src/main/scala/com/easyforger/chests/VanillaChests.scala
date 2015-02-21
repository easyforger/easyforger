package com.easyforger.chests

import net.minecraft.item.ItemStack
import net.minecraft.util.WeightedRandomChestContent
import net.minecraftforge.common.ChestGenHooks

trait VanillaChests {

  object ChestName extends Enumeration {
    type ChestName = Value
    val mineshaftCorridor, pyramidDesertyChest, pyramidJungleChest, pyramidJungleDispenser,
    strongholdCorridor, strongholdLibrary, strongholdCrossing, villageBlacksmith,
    bonusChest, dungeonChest = Value
  }

  import ChestName._

  def addChestContent(chest: ChestName, item: ItemStack, minStack: Int, maxStack: Int, chance: Int) =
    ChestGenHooks.addItem(chest.toString, new WeightedRandomChestContent(item, minStack, maxStack, chance))

  def removeChestContent(chest: ChestName, item: ItemStack) = ChestGenHooks.removeItem(chest.toString, item)
}
