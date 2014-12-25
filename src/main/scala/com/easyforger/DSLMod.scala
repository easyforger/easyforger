package com.easyforger

import com.easyforger.creatures.CreaturesSupport
import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}

class DSLMod extends SmeltingSupport with CraftingRecipeSupport with CreaturesSupport

trait RecipesBase {
  case class Recipe(source: RichItemStack, result: RichItemStack)

  implicit def toRichItemStack(item: Item): RichItemStack = toRichItemStack(new ItemStack(item, 1))
  implicit def toRichItemStack(block: Block): RichItemStack = toRichItemStack(new ItemStack(block, 1))
  implicit def toRichItemStack(itemStack: ItemStack): RichItemStack = RichItemStack(itemStack, itemStack.getDisplayName.toLowerCase.charAt(0))

  case class RichItemStack(itemStack: ItemStack, acronym: Char) {
    def apply(stackSize: Int) = this.copy(itemStack = new ItemStack(itemStack.getItem, stackSize))
    def apply(acr: Char) = this.copy(acronym = acr)

    def to(result: RichItemStack) = Recipe(this, result)
  }
}

trait SmeltingSupport extends RecipesBase {
  var smeltSeq = Seq.empty[SmeltingRecipe]
  
  case class SmeltingRecipe(recipe: Recipe, xp: Double) {
    def withXp(newXp: Double) = {
      smeltSeq = smeltSeq.filterNot(_ == this) :+ this.copy(xp = newXp)
    }
  }

  object SmeltingRecipe {
    implicit def recipeToSmelting(recipe: Recipe) = SmeltingRecipe(recipe, 1)
  }

  def smelting(smelts: => Unit): Unit = smelts
  def smelt(newSmelt: => SmeltingRecipe): SmeltingRecipe = {
    smeltSeq = smeltSeq :+ newSmelt
    newSmelt
  }
}

trait CraftingRecipeSupport extends RecipesBase {
  var recipesSeq = Seq.empty[CraftingRecipe]

  def recipes(recs: => Unit): Unit = recs
  def recipe(rec: => CraftingRecipe): Unit = { recipesSeq = recipesSeq :+ rec }

  implicit def recipeToCrafting(recipe: Recipe) = CraftingRecipe(Set(recipe.source), None, Some(recipe.result))
  
  implicit class CraftingSource(item: Item) {
    def +(block: Block) = CraftingRecipe(Set(item, block))
    def +(newItem: Item) = CraftingRecipe(Set(item, newItem))
  }

  case class CraftingRecipe(source: Set[RichItemStack], shape: Option[String] = None, result: Option[RichItemStack] = None) {
    def + (block: Block) = this.copy(source = source + block)
    def + (item: Item) = this.copy(source = source + item)

    def to(item: Item) = this.copy(result = Some(item))
    def to(block: Block) = this.copy(result = Some(block))
    def to(itemStack: ItemStack) = this.copy(result = Some(itemStack))

    def withShape(shape: String) = this.copy(shape = Some(shape))
  }
}
