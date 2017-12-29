/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import com.easyforger.base.Dye
import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

case class Recipe(source: RecipeSource, result: RecipeResult)

sealed trait RecipeSource {
  def acronym: Option[Symbol]
  def itemStack: ItemStack
  def withAcronym(acronym: Symbol): RecipeSource

  def apply(acr: Symbol): RecipeSource = withAcronym(acr)

  def to(result: RecipeResult): Recipe = Recipe(this, result)
}

final case class ItemSource(item: Item, acronym: Option[Symbol] = None) extends RecipeSource {
  override def itemStack: ItemStack = new ItemStack(item)
  override def withAcronym(acronym: Symbol): RecipeSource = copy(acronym = Some(acronym))
}

final case class BlockSource(block: Block, acronym: Option[Symbol] = None) extends RecipeSource {
  override def itemStack: ItemStack = new ItemStack(block)
  override def withAcronym(acronym: Symbol): RecipeSource = copy(acronym = Some(acronym))
}

final case class DyeSource(dye: Dye, acronym: Option[Symbol] = None) extends RecipeSource {
  override def itemStack: ItemStack = dye.itemStack
  override def withAcronym(acronym: Symbol): RecipeSource = copy(acronym = Some(acronym))
}
