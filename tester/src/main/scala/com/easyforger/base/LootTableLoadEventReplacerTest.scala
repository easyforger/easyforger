/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.base

import com.easyforger.chests.Chests
import net.minecraft.util.ResourceLocation
import net.minecraft.world.storage.loot.{LootTable, LootTableManager}
import net.minecraftforge.event.LootTableLoadEvent
import org.mockito.ArgumentMatchers
import org.mockito.Mockito._ // scalastyle:ignore
import utest._ // scalastyle:ignore

object LootTableLoadEventReplacerTest {
  val tests = Tests {
    "Replace spawn chest loot table" - {
      val resLocation = "new-res-location"
      val replacer = new LootTableLoadEventReplacer(Map(Chests.spawnBonus.resourceLocation -> resLocation))

      val lootTableManager = mock(classOf[LootTableManager])
      val lootTable = mock(classOf[LootTable])
      val event = spy(new LootTableLoadEvent(Chests.spawnBonus.resourceLocation, lootTable, lootTableManager))

      replacer.onLootTableLoadEvent(event)

      verify(lootTableManager).getLootTableFromLocation(new ResourceLocation(resLocation))
      verify(event).setTable(ArgumentMatchers.any[LootTable])
    }
  }
}
