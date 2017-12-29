/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

case class CraftingRecipe(sources: Set[RecipeSource], shape: Option[String] = None, result: Option[RecipeResult] = None) extends RecipeOps {
  def +(block: Block): CraftingRecipe = this.copy(sources = sources + block)
  def +(item: Item): CraftingRecipe = this.copy(sources = sources + item)
  def +(recipeSource: RecipeSource): CraftingRecipe = this.copy(sources = sources + recipeSource)

  def to(item: Item): CraftingRecipe = this.copy(result = Some(item))
  def to(block: Block): CraftingRecipe = this.copy(result = Some(block))
  def to(itemStack: ItemStack): CraftingRecipe = this.copy(result = Some(RecipeResult(itemStack)))

  def withShape(shape: String): CraftingRecipe = this.copy(shape = Some(shape))
}
