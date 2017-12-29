/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

case class RecipeResult(result: ItemStack)

object RecipeResult {
  implicit def itemResult(item: Item): RecipeResult =
    RecipeResult(new ItemStack(item))

  implicit def blockResult(block: Block): RecipeResult =
    RecipeResult(new ItemStack(block))

  implicit def itemStackResult(itemStack: ItemStack): RecipeResult =
    RecipeResult(itemStack)
}
