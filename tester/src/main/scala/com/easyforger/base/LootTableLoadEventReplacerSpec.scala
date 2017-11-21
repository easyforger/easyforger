/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.base

import com.easyforger.chests.Chests
import net.minecraft.util.ResourceLocation
import net.minecraft.world.storage.loot.{LootTable, LootTableManager}
import net.minecraftforge.event.LootTableLoadEvent
import org.specs2.Specification
import org.specs2.execute.Result
import org.specs2.matcher.ThrownExpectations
import org.specs2.mock.Mockito
import org.specs2.specification.core.SpecStructure

class LootTableLoadEventReplacerSpec extends Specification with Mockito with ThrownExpectations {
  def is: SpecStructure =
    s2"""
      Replace the spawn chest loot table $spawnChestLoot
    """

  def spawnChestLoot: Result = {
    val resLocation = "new-res-location"
    val replacer = new LootTableLoadEventReplacer(
      Map(Chests.spawnBonus.resourceLocation -> resLocation))

    val lootTableManager = mock[LootTableManager]
    val event = spy(new LootTableLoadEvent(Chests.spawnBonus.resourceLocation, mock[LootTable], lootTableManager))

    replacer.onLootTableLoadEvent(event)

    there was one(lootTableManager).getLootTableFromLocation(new ResourceLocation(resLocation))
    there was one(event).setTable(any[LootTable])
  }
}
