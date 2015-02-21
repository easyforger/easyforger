package com.easyforger

import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.item.{Item, ItemStack}

package object recipes {
  case class Recipe(source: RichItemStack, result: RichItemStack)

  implicit def toItemStack(item: Item): ItemStack = new ItemStack(item, 1)
  implicit def toItemStack(block: Block): ItemStack = new ItemStack(block, 1)

  implicit def toRichItemStack(item: Item): RichItemStack = toRichItemStack(new ItemStack(item, 1))
  implicit def toRichItemStack(block: Block): RichItemStack = toRichItemStack(new ItemStack(block, 1))
  implicit def toRichItemStack(itemStack: ItemStack): RichItemStack = RichItemStack(itemStack, itemStack.getDisplayName.toLowerCase.charAt(0))

  implicit def recipeToSmelting(recipe: Recipe): SmeltingRecipe = SmeltingRecipe(recipe, 1)
  implicit def recipeToCrafting(recipe: Recipe): CraftingRecipe = CraftingRecipe(Set(recipe.source), None, Some(recipe.result))
  
  case class RichItemStack(itemStack: ItemStack, acronym: Char) {
    def apply(stackSize: Int) = this.copy(itemStack = new ItemStack(itemStack.getItem, stackSize))
    def apply(acr: Char) = this.copy(acronym = acr)

    def to(result: RichItemStack) = Recipe(this, result)
  }

  implicit class CraftingSource(item: Item) {
    def +(block: Block) = CraftingRecipe(Set(item, block))
    def +(newItem: Item) = CraftingRecipe(Set(item, newItem))
  }

  def smelting(smelts: SmeltingRecipe*): Unit = Smelting.smelting(smelts: _*)
  def crafting(craftingRecipes: CraftingRecipe*): Unit = Crafting.crafting(craftingRecipes: _*)
  def enchanted(itemStack: ItemStack, enchantment: Enchantment, level: Int): ItemStack = {
    itemStack.addEnchantment(enchantment, level)
    itemStack
  }
}
