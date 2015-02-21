package com.easyforger.dungeons

import com.easyforger.chests._
import net.minecraft.item.ItemStack
import net.minecraftforge.common.DungeonHooks

/**
 * According to Forge, the default dungeon possibilities are:
 * Spider   100
 * Skeleton 100
 * Zombie   200
 */
trait VanillaDungeons {
  self: VanillaChests =>

  def dungeonMobs(mobMap: (String, Int)*) = VanillaDungeons.mapMobSpawn(mobMap: _*)

  def dungeonChest(item: ItemStack, minStack: Int, maxStack: Int, chance: Int): Unit =
    addChestContent(ChestName.dungeonChest, item, minStack, maxStack, chance)
}

object VanillaDungeons {
  def mapMobSpawn(mobMap: (String, Int)*) = mobMap.foreach {
    case (mob, spawnChance) =>
      DungeonHooks.removeDungeonMob(mob)
      DungeonHooks.addDungeonMob(mob, spawnChance)
  }
}
