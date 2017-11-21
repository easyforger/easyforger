/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import com.easyforger.recipes.RecipeSupport
import net.minecraft.block.Block
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.Item
import org.specs2.matcher.Matcher
import org.specs2.mutable.Specification

class RecipeSupportSpec extends Specification {
  import com.easyforger.autotester.Specs2RunnerMod._ // scalastyle:ignore

  "RecipeSupport.shortForItemStack" should {
    "get the short for Items" in {
      "'a' for apple" >> (Items.APPLE must haveItemShort('a'))
      "'i' for iron_axe" >> (Items.IRON_AXE must haveItemShort('i'))
      "'s' for sign" >> (Items.SIGN must haveItemShort('s'))
      "'s' for wheat_seeds" >> (Items.WHEAT_SEEDS must haveItemShort('s'))
      "'a' for armor_stand" >> (Items.ARMOR_STAND must haveItemShort('a'))
      "'c' for item cake" >> (Items.CAKE must haveItemShort('c'))
      "'a' for item acacia_door" >> (Items.ACACIA_DOOR must haveItemShort('a'))
      "'j' for item jungle_door" >> (Items.JUNGLE_DOOR must haveItemShort('j'))
      "'b' for item bed" >> (Items.BED must haveItemShort('b'))
    }

    "get the short for Blocks" in {
      "'d' for dirt" >> (Blocks.DIRT must haveBlockShort('d'))
      "'f' for furnace" >> (Blocks.FURNACE must haveBlockShort('f'))
      "'g' for grass" >> (Blocks.GRASS must haveBlockShort('g'))
      "'b' for brewing_stand" >> (Blocks.BREWING_STAND must haveBlockShort('b'))
      "'r' for reeds" >> (Blocks.REEDS must haveBlockShort('r'))
      "'a' for acacia_fence" >> (Blocks.ACACIA_FENCE must haveBlockShort('a'))
      "'a' for acacia_fence_gate" >> (Blocks.ACACIA_FENCE_GATE must haveBlockShort('a'))
      "'a' for acacia_stairs" >> (Blocks.ACACIA_STAIRS must haveBlockShort('a'))
      "'b' for birch_stairs" >> (Blocks.BIRCH_STAIRS must haveBlockShort('b'))
      "'c' for block cake" >> (Blocks.CAKE must haveBlockShort('c'))
      "'a' for block acacia_door" >> (Blocks.ACACIA_DOOR must haveBlockShort('a'))
      "'j' for block jungle_door" >> (Blocks.JUNGLE_DOOR must haveBlockShort('j'))
      "'b' for block birch_door" >> (Blocks.BIRCH_DOOR must haveBlockShort('b'))
      "'o' for block oak_door" >> (Blocks.OAK_DOOR must haveBlockShort('o'))
      "'d' for block dark_oak_door" >> (Blocks.DARK_OAK_DOOR must haveBlockShort('d'))
      "'b' for block bed" >> (Blocks.BED must haveBlockShort('b'))
    }

    "get the short for special items" in {
      "'b' for black dye" >> ((blackDye.getItem, blackDye.getMetadata) must haveSpecialItemShort('b'))
      "'m' for magenta dye" >> ((magentaDye.getItem, magentaDye.getMetadata) must haveSpecialItemShort('m'))
      "'y' for yellow dye" >> ((yellowDye.getItem, yellowDye.getMetadata) must haveSpecialItemShort('y'))
    }
  }

  def haveBlockShort(c: Char): Matcher[Block] = { block: Block =>
    RecipeSupport.shortForBlock(block) === c
  }

  def haveItemShort(c: Char): Matcher[Item] = { item: Item =>
    RecipeSupport.shortForItem(item) === c
  }

  def haveSpecialItemShort(c: Char): Matcher[(Item, Int)] = { params: (Item, Int) =>
    val (item, meta) = params
    RecipeSupport.shortForSpecialItem(item, meta) === c
  }
}
