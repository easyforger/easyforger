/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.dungeons

import com.easyforger.creatures.VanillaCreatures
import net.minecraftforge.common.DungeonHooks

/**
 * According to Forge, the default dungeon possibilities are:
 * Spider   100
 * Skeleton 100
 * Zombie   200
 */
trait VanillaDungeons {
  self: VanillaCreatures =>

  def dungeonMobs(mobMap: (EntityName.EntityName, Int)*): Unit = mapMobSpawn(mobMap: _*)

  private def mapMobSpawn(mobMap: (EntityName.EntityName, Int)*) = mobMap.foreach {
    case (entityName, spawnChance) =>
      DungeonHooks.removeDungeonMob(entityName.toString)
      DungeonHooks.addDungeonMob(entityName.toString, spawnChance)
  }
}
