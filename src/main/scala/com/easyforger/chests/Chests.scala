/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.chests

import net.minecraft.util.ResourceLocation
import net.minecraft.world.storage.loot.LootTableList

final case class Chest(resourceLocation: ResourceLocation)

sealed trait Chests {
  val spawnBonus            = Chest(LootTableList.CHESTS_SPAWN_BONUS_CHEST)
  val endCityTreasure       = Chest(LootTableList.CHESTS_END_CITY_TREASURE)
  val simpleDungeon         = Chest(LootTableList.CHESTS_SIMPLE_DUNGEON)
  val villageBlacksmith     = Chest(LootTableList.CHESTS_VILLAGE_BLACKSMITH)
  val abandonedMineshaft    = Chest(LootTableList.CHESTS_ABANDONED_MINESHAFT)
  val netherBridge          = Chest(LootTableList.CHESTS_NETHER_BRIDGE)
  val strongholdLibrary     = Chest(LootTableList.CHESTS_STRONGHOLD_LIBRARY)
  val strongholdCrossing    = Chest(LootTableList.CHESTS_STRONGHOLD_CROSSING)
  val strongholdCorridor    = Chest(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)
  val desertPyramid         = Chest(LootTableList.CHESTS_DESERT_PYRAMID)
  val jungleTemple          = Chest(LootTableList.CHESTS_JUNGLE_TEMPLE)
  val jungleTempleDispenser = Chest(LootTableList.CHESTS_JUNGLE_TEMPLE_DISPENSER)
  val igloo                 = Chest(LootTableList.CHESTS_IGLOO_CHEST)
  val woodlandMansion       = Chest(LootTableList.CHESTS_WOODLAND_MANSION)
}

object Chests extends Chests
