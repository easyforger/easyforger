/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.item.{Item, ItemStack}
import net.minecraftforge.fml.common.registry.GameRegistry

trait RecipeSupport {
  implicit def toRecipeItemStack(item: Item): RecipeItemStack = toRecipeItemStack(new ItemStack(item, 1))
  implicit def toRecipeItemStack(block: Block): RecipeItemStack = toRecipeItemStack(new ItemStack(block, 1))
  implicit def toRecipeItemStack(itemStack: ItemStack): RecipeItemStack = RecipeItemStack(itemStack, itemStack.getDisplayName.toLowerCase.charAt(0))

  implicit def recipeToSmelting(recipe: Recipe): SmeltingRecipe = SmeltingRecipe(recipe, 1)
  implicit def recipeToCrafting(recipe: Recipe): CraftingRecipe = CraftingRecipe(Set(recipe.source), None, Some(recipe.result))

  implicit class CraftingSourceItem(item: Item) {
    def +(block: Block): CraftingRecipe = CraftingRecipe(Set(item, block))
    def +(newItem: Item): CraftingRecipe = CraftingRecipe(Set(item, newItem))
    def +(itemStack: ItemStack): CraftingRecipe = CraftingRecipe(Set(item, itemStack))
    def +(recipeItemStack: RecipeItemStack): CraftingRecipe = CraftingRecipe(Set(item, recipeItemStack))
  }

  implicit class CraftingSourceBlock(block: Block) {
    def +(newBlock: Block): CraftingRecipe = CraftingRecipe(Set(block, newBlock))
    def +(item: Item): CraftingRecipe = CraftingRecipe(Set(block, item))
    def +(itemStack: ItemStack): CraftingRecipe = CraftingRecipe(Set(block, itemStack))
    def +(recipeItemStack: RecipeItemStack): CraftingRecipe = CraftingRecipe(Set(block, recipeItemStack))
  }

  def smelting(smelts: SmeltingRecipe*): Unit = Smelting.smelting(smelts: _*)

  def crafting(craftingRecipes: CraftingRecipe*): Unit =
    for (craftRecipe <- craftingRecipes) {
      require(craftRecipe.result.isDefined, "incomplete recipe!")
      val result = craftRecipe.result.get.itemStack

      if (craftRecipe.shape.isDefined)
        GameRegistry.addRecipe(result, calcParamsArrays(craftRecipe): _*)
      else
        GameRegistry.addShapelessRecipe(result, craftRecipe.sources.map(_.itemStack).toArray: _*)
    }

  private def calcParamsArrays(craftRecipe: CraftingRecipe): Array[Object] = {
    val params = craftRecipe.shape.get.trim.replace('.', ' ').split("\n")
    val acronyms = craftRecipe.sources.flatMap(richItemStack => Seq(new Character(richItemStack.acronym), richItemStack.itemStack))

    params ++ acronyms
  }

  def enchanted(itemStack: ItemStack, enchantment: Enchantment, level: Int): ItemStack = {
    itemStack.addEnchantment(enchantment, level)
    itemStack
  }
}
