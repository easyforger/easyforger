/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraftforge.fml.common.registry.GameRegistry

object Smelting {
  def smelting(smelts: SmeltingRecipe*) =
    for (smelt <- smelts)
      GameRegistry.addSmelting(smelt.recipe.source.itemStack, smelt.recipe.result.itemStack, smelt.xp.toFloat)
}

case class SmeltingRecipe(recipe: Recipe, xp: Double) {
  def withXp(newXp: Double) = this.copy(xp = newXp)
}
