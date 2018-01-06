/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

case class CraftingRecipe(sources: Set[RecipeSource], shape: Option[String], result: RecipeResult) {
  def withShape(shape: String): CraftingRecipe = this.copy(shape = Some(shape))
}
