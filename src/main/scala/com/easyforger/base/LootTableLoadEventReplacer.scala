/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.base

import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.LootTableLoadEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.apache.logging.log4j.LogManager

final class LootTableLoadEventReplacer(replacements: Map[ResourceLocation, String]) {
  private val logger = LogManager.getLogger

  @SubscribeEvent
  def onLootTableLoadEvent(event: LootTableLoadEvent): Unit =
    replacements.get(event.getName).foreach { newLootTable =>
      logger.info(s"Replacing ${event.getName}")

      val lootTable = event.getLootTableManager.getLootTableFromLocation(new ResourceLocation(newLootTable))
      event.setTable(lootTable)
    }
}
