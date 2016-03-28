/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

case class CraftingRecipe(sources: Set[RecipeItemStack], shape: Option[String] = None, result: Option[RecipeItemStack] = None) extends RecipeSupport {
  def +(block: Block): CraftingRecipe = this.copy(sources = sources + block)
  def +(item: Item): CraftingRecipe = this.copy(sources = sources + item)
  def +(itemStack: ItemStack): CraftingRecipe = this.copy(sources = sources + itemStack)
  def +(recipeItemStack: RecipeItemStack): CraftingRecipe = this.copy(sources = sources + recipeItemStack)

  def to(item: Item): CraftingRecipe = this.copy(result = Some(item))
  def to(block: Block): CraftingRecipe = this.copy(result = Some(block))
  def to(itemStack: ItemStack): CraftingRecipe = this.copy(result = Some(itemStack))
  def to(recipeItemStack: RecipeItemStack): CraftingRecipe = this.copy(result = Some(recipeItemStack))

  def withShape(shape: String): CraftingRecipe = this.copy(shape = Some(shape))
}
