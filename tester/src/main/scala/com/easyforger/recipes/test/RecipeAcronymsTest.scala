/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import com.easyforger.recipes.RecipeOps
import net.minecraft.block.Block
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.Item
import utest._ // scalastyle:ignore

object RecipeAcronymsTest {
  import com.easyforger.tester.uTestRunnerMod._ // scalastyle:ignore

  val tests = Tests {
    "RecipeSupport.shortForItemStack" - {
      "get the short for Items" - {
        "'a for apple" - haveItemAcronym(Items.APPLE, 'a)
        "'i for iron_axe" - haveItemAcronym(Items.IRON_AXE, 'i)
        "'s for sign" - haveItemAcronym(Items.SIGN, 's)
        "'s for wheat_seeds" - haveItemAcronym(Items.WHEAT_SEEDS, 's)
        "'a for armor_stand" - haveItemAcronym(Items.ARMOR_STAND, 'a)
        "'c for item cake" - haveItemAcronym(Items.CAKE, 'c)
        "'a for item acacia_door" - haveItemAcronym(Items.ACACIA_DOOR, 'a)
        "'j for item jungle_door" - haveItemAcronym(Items.JUNGLE_DOOR, 'j)
        "'b for item bed" - haveItemAcronym(Items.BED, 'b)
      }

      "get the short for Blocks" - {
        "'d for dirt" - haveBlockAcronym(Blocks.DIRT, 'd)
        "'f for furnace" - haveBlockAcronym(Blocks.FURNACE, 'f)
        "'g for grass" - haveBlockAcronym(Blocks.GRASS, 'g)
        "'b for brewing_stand" - haveBlockAcronym(Blocks.BREWING_STAND, 'b)
        "'r for reeds" - haveBlockAcronym(Blocks.REEDS, 'r)
        "'a for acacia_fence" - haveBlockAcronym(Blocks.ACACIA_FENCE, 'a)
        "'a for acacia_fence_gate" - haveBlockAcronym(Blocks.ACACIA_FENCE_GATE, 'a)
        "'a for acacia_stairs" - haveBlockAcronym(Blocks.ACACIA_STAIRS, 'a)
        "'b for birch_stairs" - haveBlockAcronym(Blocks.BIRCH_STAIRS, 'b)
        "'c for block cake" - haveBlockAcronym(Blocks.CAKE, 'c)
        "'a for block acacia_door" - haveBlockAcronym(Blocks.ACACIA_DOOR, 'a)
        "'j for block jungle_door" - haveBlockAcronym(Blocks.JUNGLE_DOOR, 'j)
        "'b for block birch_door" - haveBlockAcronym(Blocks.BIRCH_DOOR, 'b)
        "'o for block oak_door" - haveBlockAcronym(Blocks.OAK_DOOR, 'o)
        "'d for block dark_oak_door" - haveBlockAcronym(Blocks.DARK_OAK_DOOR, 'd)
        "'b for block bed" - haveBlockAcronym(Blocks.BED, 'b)
      }

      "get the short for special items" - {
        "'b for black dye" - haveSpecialItemAcronym(blackDye.itemStack.getItem, blackDye.itemStack.getMetadata, 'b)
        "'m for magenta dye" - haveSpecialItemAcronym(magentaDye.itemStack.getItem, magentaDye.itemStack.getMetadata, 'm)
        "'y for yellow dye" - haveSpecialItemAcronym(yellowDye.itemStack.getItem, yellowDye.itemStack.getMetadata, 'y)
      }
    }
  }

  def haveBlockAcronym(block: Block, s: Symbol): Unit =
    assert(RecipeOps.shortForBlock(block) == s)

  def haveItemAcronym(item: Item, s: Symbol): Unit =
    assert(RecipeOps.shortForItem(item) == s)

  def haveSpecialItemAcronym(item: Item, metadata: Int, s: Symbol): Unit =
    assert(RecipeOps.shortForSpecialItem(item, metadata) == s)
}
