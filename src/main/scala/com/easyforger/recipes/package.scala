/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

case class Recipe(source: RecipeItemStack, result: ItemStack)

case class RecipeItemStack(itemStack: ItemStack, acronym: Char) {
  def apply(acr: Char): RecipeItemStack = this.copy(acronym = acr)

  def to(item: Item): Recipe = Recipe(this, new ItemStack(item))
  def to(block: Block): Recipe = Recipe(this, new ItemStack(block))
  def to(result: ItemStack): Recipe = Recipe(this, result)

  // FIXME: this removes an acronym that might have been created, but it is used in a result position, so this is probably not a big deal
  // still, this kind of thing should not be possible
  def to(result: RecipeItemStack): Recipe = Recipe(this, result.itemStack)
}
