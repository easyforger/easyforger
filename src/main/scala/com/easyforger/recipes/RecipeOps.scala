/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes

import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{EnumDyeColor, Item, ItemStack}

trait RecipeOps extends RecipeSourceOps with CraftingSourceOps {

  implicit class RecipeIntOps(quantity: Int) {
    def *(item: Item): ItemStack = new ItemStack(item, quantity)
    def *(block: Block): ItemStack = new ItemStack(block, quantity)
  }

  implicit def recipeToSmelting(recipe: Recipe): SmeltingRecipe = SmeltingRecipe(recipe, 1)
  implicit def recipeToCrafting(recipe: Recipe): CraftingRecipe = CraftingRecipe(Set(recipe.source), None, recipe.result)

  def enchanted(itemStack: ItemStack, enchantment: Enchantment, level: Int): ItemStack = {
    itemStack.addEnchantment(enchantment, level)
    itemStack
  }

  def smelting(smelts: SmeltingRecipe*): Unit = Smelting.smelting(smelts: _*)
  def crafting(crafts: CraftingRecipe*): Unit = Crafting.crafting(crafts: _*)
}

object RecipeOps {
  val errorShort = 'E
  val blockShorts = Map(
    Blocks.BED -> 'b,
    Blocks.CAKE -> 'c,
    Blocks.BREWING_STAND -> 'b,
    Blocks.REEDS -> 'r,
    Blocks.ACACIA_DOOR -> 'a,
    Blocks.JUNGLE_DOOR -> 'j,
    Blocks.OAK_DOOR -> 'o,
    Blocks.DARK_OAK_DOOR -> 'd,
    Blocks.BIRCH_DOOR -> 'b,
    Blocks.SPRUCE_DOOR -> 's
  )

  def shortForItem(item: Item): Symbol =
    new ItemStack(item).getDisplayName.toLowerCase.charAt(0).symbol

  def shortForSpecialItem(item: Item, meta: Int): Symbol =
    if (item == Items.DYE)
      EnumDyeColor.byMetadata(meta).getName.toLowerCase.charAt(0).symbol
    else
      errorShort

  def shortForBlock(block: Block): Symbol =
    Item.getItemFromBlock(block) match {
      case Items.AIR  => blockShorts.getOrElse(block, errorShort)
      case item: Any  => shortForItem(item)
    }

  def calcMCParamsArray(craftRecipe: CraftingRecipe): Array[Object] = {
    val params = craftRecipe.shape
      .map(_.trim.replace(" ", "").replace('.', ' ').split("\n"))
      .getOrElse(Array.empty)

    // turn into a list to avoid removing duplicated acronyms, which would end up hiding errors
    val acronyms = craftRecipe.sources.toList.flatMap { recipeSource =>
      Seq(new Character(recipeSource.acronym.flatMap(_.name.headOption).getOrElse('E')), recipeSource.itemStack)
    }

    params ++ acronyms
  }

  implicit class SymbolOps(char: Char) {
    val symbol = Symbol(char.toString)
  }
}
