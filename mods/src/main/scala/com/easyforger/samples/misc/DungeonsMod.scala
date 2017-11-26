/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.misc

import com.easyforger.base.EasyForger
import com.easyforger.util.Version
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = DungeonsMod.modId, name = "EasyForger Dungeons Mod", version = Version.version, modLanguage = "scala")
object DungeonsMod extends EasyForger {
  final val modId = "easyforger_dungeons"

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    dungeonMobs(
      EntityName.Creeper -> 100,
      EntityName.Zombie -> 400,
      EntityName.Enderman -> 50
    )

    chestDrops(
      Chests.spawnBonus -> s"$modId:chests/ef_spawn_bonus_chest"
    )
  }
}
