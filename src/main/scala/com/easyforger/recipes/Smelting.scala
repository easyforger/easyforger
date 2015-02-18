package com.easyforger.recipes

import cpw.mods.fml.common.registry.GameRegistry

object Smelting {
  def smelting(smelts: SmeltingRecipe*) =
    for (smelt <- smelts)
      GameRegistry.addSmelting(smelt.recipe.source.itemStack, smelt.recipe.result.itemStack, smelt.xp.toFloat)
}

case class SmeltingRecipe(recipe: Recipe, xp: Double) {
  def withXp(newXp: Double) = this.copy(xp = newXp)
}
