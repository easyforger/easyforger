/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import utest._ // scalastyle:ignore

object SmeltingRecipesTest {
  import com.easyforger.autotester.uTestRunnerMod._ // scalastyle:ignore

  val tests = Tests {
    "creating smelting recipes" - {
      "register baking apple resulting in a cake" - {
        smelting(Items.APPLE to Items.CAKE)
        val smeltingResult = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(Items.APPLE))

        assert(smeltingResult.getItem == Items.CAKE)
      }
    }
  }
}
