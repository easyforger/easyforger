package com.easyforger

import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.item.{Item, ItemStack}

package object recipes {
  case class Recipe(source: RecipeItemStack, result: RecipeItemStack)
  
  implicit def toRecipeItemStack(item: Item): RecipeItemStack = toRecipeItemStack(new ItemStack(item, 1))
  implicit def toRecipeItemStack(block: Block): RecipeItemStack = toRecipeItemStack(new ItemStack(block, 1))
  implicit def toRecipeItemStack(itemStack: ItemStack): RecipeItemStack = RecipeItemStack(itemStack, itemStack.getDisplayName.toLowerCase.charAt(0))

  implicit def recipeToSmelting(recipe: Recipe): SmeltingRecipe = SmeltingRecipe(recipe, 1)
  implicit def recipeToCrafting(recipe: Recipe): CraftingRecipe = CraftingRecipe(Set(recipe.source), None, Some(recipe.result))
  
  case class RecipeItemStack(itemStack: ItemStack, acronym: Char) {
    def apply(stackSize: Int) = this.copy(itemStack = new ItemStack(itemStack.getItem, stackSize))
    def apply(acr: Char) = this.copy(acronym = acr)

    def to(result: RecipeItemStack) = Recipe(this, result)
  }

  implicit class CraftingSource(item: Item) {
    def +(block: Block) = CraftingRecipe(Set(item, block))
    def +(newItem: Item) = CraftingRecipe(Set(item, newItem))
    def +(itemStack: ItemStack) = CraftingRecipe(Set(item, itemStack))
    def +(recipeItemStack: RecipeItemStack) = CraftingRecipe(Set(item, recipeItemStack))
  }

  def smelting(smelts: SmeltingRecipe*): Unit = Smelting.smelting(smelts: _*)
  def crafting(craftingRecipes: CraftingRecipe*): Unit = Crafting.crafting(craftingRecipes: _*)
  def enchanted(itemStack: ItemStack, enchantment: Enchantment, level: Int): ItemStack = {
    itemStack.addEnchantment(enchantment, level)
    itemStack
  }
}
