package com.easyforger.recipes

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

object Crafting {
  def crafting(craftingRecipes: CraftingRecipe*) =
    for (craftRecipe <- craftingRecipes)
      if (craftRecipe.shape.isDefined)
        GameRegistry.addRecipe(craftRecipe.result.get.itemStack, calcParamsArrays(craftRecipe): _*)
      else
        GameRegistry.addShapelessRecipe(
          craftRecipe.result.map(_.itemStack).getOrElse(throw new IllegalStateException("incomplete recipe!")),
          craftRecipe.sources.map(_.itemStack).toArray: _*)

  def calcParamsArrays(craftRecipe: CraftingRecipe): Array[Object] = {
    val params = craftRecipe.shape.get.trim.replace('.', ' ').split("\n")
    val acronyms = craftRecipe.sources.flatMap(richItemStack => Seq(new Character(richItemStack.acronym), richItemStack.itemStack))

    params ++ acronyms
  }
}

case class CraftingRecipe(sources: Set[RecipeItemStack], shape: Option[String] = None, result: Option[RecipeItemStack] = None) {
  def +(block: Block) = this.copy(sources = sources + block)
  def +(item: Item) = this.copy(sources = sources + item)
  def +(itemStack: ItemStack) = this.copy(sources = sources + itemStack)
  def +(recipeItemStack: RecipeItemStack) = this.copy(sources = sources + recipeItemStack)

  def to(item: Item) = this.copy(result = Some(item))
  def to(block: Block) = this.copy(result = Some(block))
  def to(itemStack: ItemStack) = this.copy(result = Some(itemStack))
  def to(recipeItemStack: RecipeItemStack) = this.copy(result = Some(recipeItemStack))

  def withShape(shape: String) = this.copy(shape = Some(shape))
}
