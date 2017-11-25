/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import com.easyforger.recipes.RecipeSupport
import net.minecraft.block.Block
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.Item
import utest._ // scalastyle:ignore

object RecipeSupportTest {
  import com.easyforger.tester.uTestRunnerMod._ // scalastyle:ignore

  val tests = Tests {
    "RecipeSupport.shortForItemStack" - {
      "get the short for Items" - {
        "'a' for apple" - haveItemShort(Items.APPLE, 'a')
        "'i' for iron_axe" - haveItemShort(Items.IRON_AXE, 'i')
        "'s' for sign" - haveItemShort(Items.SIGN, 's')
        "'s' for wheat_seeds" - haveItemShort(Items.WHEAT_SEEDS, 's')
        "'a' for armor_stand" - haveItemShort(Items.ARMOR_STAND, 'a')
        "'c' for item cake" - haveItemShort(Items.CAKE, 'c')
        "'a' for item acacia_door" - haveItemShort(Items.ACACIA_DOOR, 'a')
        "'j' for item jungle_door" - haveItemShort(Items.JUNGLE_DOOR, 'j')
        "'b' for item bed" - haveItemShort(Items.BED, 'b')
      }

      "get the short for Blocks" - {
        "'d' for dirt" - haveBlockShort(Blocks.DIRT, 'd')
        "'f' for furnace" - haveBlockShort(Blocks.FURNACE, 'f')
        "'g' for grass" - haveBlockShort(Blocks.GRASS, 'g')
        "'b' for brewing_stand" - haveBlockShort(Blocks.BREWING_STAND, 'b')
        "'r' for reeds" - haveBlockShort(Blocks.REEDS, 'r')
        "'a' for acacia_fence" - haveBlockShort(Blocks.ACACIA_FENCE, 'a')
        "'a' for acacia_fence_gate" - haveBlockShort(Blocks.ACACIA_FENCE_GATE, 'a')
        "'a' for acacia_stairs" - haveBlockShort(Blocks.ACACIA_STAIRS, 'a')
        "'b' for birch_stairs" - haveBlockShort(Blocks.BIRCH_STAIRS, 'b')
        "'c' for block cake" - haveBlockShort(Blocks.CAKE, 'c')
        "'a' for block acacia_door" - haveBlockShort(Blocks.ACACIA_DOOR, 'a')
        "'j' for block jungle_door" - haveBlockShort(Blocks.JUNGLE_DOOR, 'j')
        "'b' for block birch_door" - haveBlockShort(Blocks.BIRCH_DOOR, 'b')
        "'o' for block oak_door" - haveBlockShort(Blocks.OAK_DOOR, 'o')
        "'d' for block dark_oak_door" - haveBlockShort(Blocks.DARK_OAK_DOOR, 'd')
        "'b' for block bed" - haveBlockShort(Blocks.BED, 'b')
      }

      "get the short for special items" - {
        "'b' for black dye" - haveSpecialItemShort(blackDye.getItem, blackDye.getMetadata, 'b')
        "'m' for magenta dye" - haveSpecialItemShort(magentaDye.getItem, magentaDye.getMetadata, 'm')
        "'y' for yellow dye" - haveSpecialItemShort(yellowDye.getItem, yellowDye.getMetadata, 'y')
      }
    }
  }

  def haveBlockShort(block: Block, c: Char): Unit =
    assert(RecipeSupport.shortForBlock(block) == c)

  def haveItemShort(item: Item, c: Char): Unit =
    assert(RecipeSupport.shortForItem(item) == c)

  def haveSpecialItemShort(item: Item, metadata: Int, c: Char): Unit =
    assert(RecipeSupport.shortForSpecialItem(item, metadata) == c)
}
