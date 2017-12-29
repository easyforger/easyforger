/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraftforge.fml.common.registry.GameRegistry

object Crafting {
  def crafting(craftingRecipes: CraftingRecipe*): Unit =
    for (recipe <- craftingRecipes) {
      require(recipe.result.isDefined, "incomplete recipe!")
      val result = recipe.result.get

      if (recipe.shape.isDefined)
        GameRegistry.addRecipe(result.result, RecipeOps.calcMCParamsArray(recipe): _*)
      else
        GameRegistry.addShapelessRecipe(result.result, recipe.sources.map(_.itemStack).toArray: _*)
    }
}
