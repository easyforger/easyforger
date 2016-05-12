/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

case class CraftingRecipe(sources: Set[RecipeItemStack], shape: Option[String] = None, result: Option[ItemStack] = None) extends RecipeSupport {
  def +(block: Block): CraftingRecipe = this.copy(sources = sources + block)
  def +(item: Item): CraftingRecipe = this.copy(sources = sources + item)
  def +(recipeItemStack: RecipeItemStack): CraftingRecipe = this.copy(sources = sources + recipeItemStack)

  def to(item: Item): CraftingRecipe = this.copy(result = Some(new ItemStack(item)))
  def to(block: Block): CraftingRecipe = this.copy(result = Some(new ItemStack(block)))
  def to(itemStack: ItemStack): CraftingRecipe = this.copy(result = Some(itemStack))

  // FIXME: this removes an acronym that might have been created, but it is used in a result position, so this is probably not a big deal
  // still, this kind of thing should not be possible
  def to(recipeItemStack: RecipeItemStack): CraftingRecipe = this.copy(result = Some(recipeItemStack.itemStack))

  def withShape(shape: String): CraftingRecipe = this.copy(shape = Some(shape))
}
