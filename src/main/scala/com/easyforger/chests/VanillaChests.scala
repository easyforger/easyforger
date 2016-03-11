/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
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

  def addChestContent(chest: ChestName.ChestName, item: ItemStack, minStack: Int, maxStack: Int, chance: Int): Unit =
    ChestGenHooks.addItem(chest.toString, new WeightedRandomChestContent(item, minStack, maxStack, chance))

  def removeChestContent(chest: ChestName.ChestName, item: ItemStack): Unit =
    ChestGenHooks.removeItem(chest.toString, item)
}
