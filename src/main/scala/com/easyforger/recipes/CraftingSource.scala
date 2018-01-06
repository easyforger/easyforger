/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

case class CraftingSource(sources: Set[RecipeSource], shape: Option[String] = None) {
  def +(block: Block): CraftingSource = this.copy(sources = sources + BlockSource(block))
  def +(item: Item): CraftingSource = this.copy(sources = sources + ItemSource(item))
  def +(recipeSource: RecipeSource): CraftingSource = this.copy(sources = sources + recipeSource)

  def to(item: Item): CraftingRecipe = CraftingRecipe(sources, shape, item)
  def to(block: Block): CraftingRecipe = CraftingRecipe(sources, shape, block)
  def to(itemStack: ItemStack): CraftingRecipe = CraftingRecipe(sources, shape, RecipeResult(itemStack))
}

trait CraftingSourceOps extends RecipeSourceOps {
  implicit class CraftingSourceItem(item: Item) {
    def +(block: Block): CraftingSource = CraftingSource(Set(item, block))
    def +(newItem: Item): CraftingSource = CraftingSource(Set(item, newItem))
    def +(recipeSource: RecipeSource): CraftingSource = CraftingSource(Set(item, recipeSource))
  }

  implicit class CraftingSourceBlock(block: Block) {
    def +(newBlock: Block): CraftingSource = CraftingSource(Set(block, newBlock))
    def +(item: Item): CraftingSource = CraftingSource(Set(block, item))
    def +(recipeSource: RecipeSource): CraftingSource = CraftingSource(Set(block, recipeSource))
  }
}
