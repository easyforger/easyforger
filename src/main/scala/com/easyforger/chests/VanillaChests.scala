/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.chests

import com.easyforger.base.LootTableLoadEventReplacer
import net.minecraftforge.common.MinecraftForge

trait VanillaChests {
  // cheap trick to avoid this having to be imported in the user code
  val Chests = com.easyforger.chests.Chests

  def chestDrops(chestsMap: (Chest, String)*): Unit = {
    val replacer = new LootTableLoadEventReplacer(
      chestsMap.map { case (k, v) => (k.resourceLocation, v) }.toMap
    )

    MinecraftForge.EVENT_BUS.register(replacer)
  }
}
