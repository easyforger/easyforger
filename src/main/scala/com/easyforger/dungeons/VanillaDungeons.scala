/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.dungeons

import com.easyforger.creatures.VanillaCreatures
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.DungeonHooks

/**
  * See forge's DungeonHooks for the default probability levels.
  */
trait VanillaDungeons {
  self: VanillaCreatures =>

  def dungeonMobs(mobMap: (EntityName.EntityName, Int)*): Unit =
    mapMobSpawn(mobMap: _*)

  private def mapMobSpawn(mobMap: (EntityName.EntityName, Int)*) =
    mobMap.foreach {
      case (entityName, spawnChance) =>
        val resource = new ResourceLocation(entityName.toString)

        DungeonHooks.removeDungeonMob(resource)
        DungeonHooks.addDungeonMob(resource, spawnChance)
    }
}
