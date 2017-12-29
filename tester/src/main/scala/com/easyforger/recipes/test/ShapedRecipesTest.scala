/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import com.easyforger.recipes.RecipeOps
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import utest._ // scalastyle:ignore

object ShapedRecipesTest {
  import com.easyforger.tester.uTestRunnerMod._ // scalastyle:ignore

  val tests = Tests {
    "dsl shaped recipe" - {
      "have the components for the saddle" - {
        val recipe = (Items.LEATHER + Items.IRON_INGOT) to Items.SADDLE withShape
          """
            |...
            |lll
            |i.i
          """.stripMargin

        val params = RecipeOps.calcMCParamsArray(recipe)
        assertParam(params(0), "   ") // scalastyle:ignore
        assertParam(params(1), "lll")
        assertParam(params(2), "i i")
        assertParam(params(3), 'l')
        assertSameStackAs(params(4).asInstanceOf[ItemStack], new ItemStack(Items.LEATHER))
        assertParam(params(5), 'i')
        assertSameStackAs(params(6).asInstanceOf[ItemStack], new ItemStack(Items.IRON_INGOT))
      }

      "have the components for diamond + carrots to armor stand" - {
        val recipe = Items.DIAMOND + Items.CARROT to Items.ARMOR_STAND withShape
          """
            |.c.
            |.d.
            |.d.
          """.stripMargin

        val params = RecipeOps.calcMCParamsArray(recipe)
        assertParam(params(0), " c ")
        assertParam(params(1), " d ") // scalastyle:ignore
        assertParam(params(2), " d ")
        assertParam(params(3), 'd')
        assertSameStackAs(params(4).asInstanceOf[ItemStack], new ItemStack(Items.DIAMOND))
        assertParam(params(5), 'c')
        assertSameStackAs(params(6).asInstanceOf[ItemStack], new ItemStack(Items.CARROT))
      }

      "have the components for iron_ingot + redDye to apple" - {
        val recipe = Items.IRON_INGOT + redDye to Items.APPLE withShape
          """
            |.i.
            |.i.
            |.r.
          """.stripMargin

        val params = RecipeOps.calcMCParamsArray(recipe)

        assertParam(params(0), " i ") // scalastyle:ignore
        assertParam(params(1), " i ")
        assertParam(params(2), " r ")
        assertParam(params(3), 'i')
        assertSameStackAs(params(4).asInstanceOf[ItemStack], new ItemStack(Items.IRON_INGOT))
        assertParam(params(5), 'r')
        assertSameStackAs(params(6).asInstanceOf[ItemStack], redDye.itemStack)
      }

      "ignore leading and trailing spaces" - {
        val recipe = Items.SIGN to Items.APPLE withShape
          """
            | s.s
            | .s.
            | ...
          """.stripMargin

        val params = RecipeOps.calcMCParamsArray(recipe)

        assertParam(params(0), "s s")
        assertParam(params(1), " s ")
        assertParam(params(2), "   ")
        assertParam(params(3), 's')
        assertSameStackAs(params(4).asInstanceOf[ItemStack], new ItemStack(Items.SIGN))
      }
    }
  }

  def assertParam(param: Object, other: Any): Unit =
    assert(param == other)

  def assertSameStackAs(itemStack: ItemStack, otherStack: ItemStack): Unit =
    assert(
      itemStack.getCount == otherStack.getCount,
      itemStack.getItem == otherStack.getItem
    )
}
