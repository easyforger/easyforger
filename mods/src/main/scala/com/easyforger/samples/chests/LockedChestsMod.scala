/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.chests

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod(modid = LockedChestsMod.modId, name = "EasyForger Locked Chests Mod", version = "0.5", modLanguage = "scala")
object LockedChestsMod {
  final val modId = "easyforger_lockedchests"

  val lockedChest = new BlockLockedChest()

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    lockedChest.register()
    GameRegistry.registerTileEntity(classOf[TileEntityLockedChest], "LockedChestTileEntity")
  }
}
