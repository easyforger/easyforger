package com.easyforger.dungeons

import net.minecraftforge.common.DungeonHooks

/**
 * According to Forge, the default dungeon possibilities are:
 *  Spider   100
 *  Skeleton 100
 *  Zombie   200
 */
object VanillaDungeons {
  def mapMobSpawn(mobMap: (String, Int)*) = mobMap.foreach {
    case (mob, spawnChance) =>
      DungeonHooks.removeDungeonMob(mob)
      DungeonHooks.addDungeonMob(mob, spawnChance)
  }
}
