/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{EnumDyeColor, Item, ItemStack}
import net.minecraftforge.fml.common.registry.GameRegistry

import scala.util.Try

trait RecipeSupport {
  implicit def toRecipeItemStack(item: Item): RecipeItemStack = RecipeItemStack(new ItemStack(item, 1), RecipeSupport.shortForItem(item))
  implicit def toRecipeItemStack(block: Block): RecipeItemStack = RecipeItemStack(new ItemStack(block, 1), RecipeSupport.shortForBlock(block))

  // FIXME: this will break if the ItemStack passed in was created with a Block
  implicit def toRecipeItemStack(itemStack: ItemStack): RecipeItemStack =
    RecipeItemStack(itemStack, RecipeSupport.shortForSpecialItem(itemStack.getItem, itemStack.getMetadata))

  implicit def recipeToSmelting(recipe: Recipe): SmeltingRecipe = SmeltingRecipe(recipe, 1)
  implicit def recipeToCrafting(recipe: Recipe): CraftingRecipe = CraftingRecipe(Set(recipe.source), None, Some(recipe.result))

  implicit class CraftingSourceItem(item: Item) {
    def +(block: Block): CraftingRecipe = CraftingRecipe(Set(item, block))
    def +(newItem: Item): CraftingRecipe = CraftingRecipe(Set(item, newItem))
    def +(recipeItemStack: RecipeItemStack): CraftingRecipe = CraftingRecipe(Set(item, recipeItemStack))
  }

  implicit class CraftingSourceBlock(block: Block) {
    def +(newBlock: Block): CraftingRecipe = CraftingRecipe(Set(block, newBlock))
    def +(item: Item): CraftingRecipe = CraftingRecipe(Set(block, item))
    def +(recipeItemStack: RecipeItemStack): CraftingRecipe = CraftingRecipe(Set(block, recipeItemStack))
  }

  def smelting(smelts: SmeltingRecipe*): Unit = Smelting.smelting(smelts: _*)

  def crafting(craftingRecipes: CraftingRecipe*): Unit =
    for (craftRecipe <- craftingRecipes) {
      require(craftRecipe.result.isDefined, "incomplete recipe!")
      val result = craftRecipe.result.get

      if (craftRecipe.shape.isDefined)
        GameRegistry.addRecipe(result, calcParamsArrays(craftRecipe): _*)
      else
        GameRegistry.addShapelessRecipe(result, craftRecipe.sources.map(_.itemStack).toArray: _*)
    }

  protected def calcParamsArrays(craftRecipe: CraftingRecipe): Array[Object] = {
    val params = craftRecipe.shape.get.trim.replace('.', ' ').split("\n")

    // turn into a list to avoid removing duplicated acronyms, which would end up hiding errors
    val acronyms = craftRecipe.sources.toList
      .flatMap(richItemStack => Seq(new Character(richItemStack.acronym), richItemStack.itemStack))

    params ++ acronyms
  }

  def enchanted(itemStack: ItemStack, enchantment: Enchantment, level: Int): ItemStack = {
    itemStack.addEnchantment(enchantment, level)
    itemStack
  }
}

object RecipeSupport {
  val errorShort = 'E'
  val blockShorts = Map(
    Blocks.bed -> 'b',
    Blocks.cake -> 'c',
    Blocks.brewing_stand -> 'b',
    Blocks.reeds -> 'r',
    Blocks.acacia_door -> 'a',
    Blocks.jungle_door -> 'j',
    Blocks.oak_door -> 'o',
    Blocks.dark_oak_door -> 'd',
    Blocks.birch_door -> 'b',
    Blocks.spruce_door -> 's'
  )

  def shortForItem(item: Item): Char = new ItemStack(item).getDisplayName.toLowerCase.charAt(0)

  def shortForSpecialItem(item: Item, meta: Int): Char =
    if (item == Items.dye)
      EnumDyeColor.byMetadata(meta).getName.toLowerCase.charAt(0)
    else errorShort

  def shortForBlock(block: Block): Char =
    Try(shortForItem(Item.getItemFromBlock(block))).toOption.getOrElse(blockShorts.getOrElse(block, errorShort))
}
