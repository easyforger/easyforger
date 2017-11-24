/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{CraftingManager, ShapelessRecipes}

import scala.collection.JavaConverters.asScalaBufferConverter

import utest._ // scalastyle:ignore

object RegisterRecipesTest {
  import com.easyforger.autotester.uTestRunnerMod._ // scalastyle:ignore

  val tests = Tests {
    "a recipe" - {
      "register diamond + carrot to armor_stand" - {
        crafting(Items.DIAMOND + Items.CARROT to Items.ARMOR_STAND(2))

        val recipesList = CraftingManager.getInstance().getRecipeList

        val recipes = recipesList.asScala
          .filter(_.isInstanceOf[ShapelessRecipes])
          .filter(_.asInstanceOf[ShapelessRecipes].getRecipeOutput.getUnlocalizedName == "item.armorStand")

        // asserts that we found the recipe that results in an armorstand (assumes there is no vanilla recipe for that!)
        assert(recipes.size == 1)

        val addedRecipe = recipes.head.asInstanceOf[ShapelessRecipes]
        assert(addedRecipe.recipeItems.size == 2)

        val recipeItems = addedRecipe.recipeItems.asScala

        assert(recipeItems.exists((itemStack: ItemStack) => itemStack.getUnlocalizedName == "item.carrots"))
        assert(recipeItems.exists((itemStack: ItemStack) => itemStack.getUnlocalizedName == "item.diamond"))
      }
    }
  }
}
