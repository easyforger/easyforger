/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import com.easyforger.recipes.RecipeSupport
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import org.specs2.matcher.Matcher
import org.specs2.mutable.Specification

class ShapedRecipesSpec extends Specification {
  import com.easyforger.autotester.Specs2RunnerMod._ // scalastyle:ignore

  sequential
  isolated

  def beSameStackAs(itemStack: ItemStack): Matcher[ItemStack] =
    ((_: ItemStack).getCount) ^^ ===(itemStack.getCount) and
    ((_: ItemStack).getItem) ^^ ===(itemStack.getItem)

  "a dsl shaped recipe" should {
    "have the components for the saddle" in {
      val recipe = (Items.LEATHER + Items.IRON_INGOT) to Items.SADDLE withShape
        """
          |...
          |lll
          |i.i
        """.stripMargin

      val params = RecipeSupport.calcMCParamsArray(recipe)
      params(0) === "   "
      params(1) === "lll"
      params(2) === "i i"
      params(3) === 'l'
      params(4).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(Items.LEATHER))
      params(5) === 'i'
      params(6).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(Items.IRON_INGOT))
    }

    "have the components for diamond + carrots to armor stand" in {
      val recipe = Items.DIAMOND + Items.CARROT to Items.ARMOR_STAND withShape
        """
          |.c.
          |.d.
          |.d.
        """.stripMargin

      val params = RecipeSupport.calcMCParamsArray(recipe)
      params(0) === " c "
      params(1) === " d " // scalastyle:ignore
      params(2) === " d "
      params(3) === 'd'
      params(4).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(Items.DIAMOND))
      params(5) === 'c'
      params(6).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(Items.CARROT))
    }

    "have the components for iron_ingot + redDye to apple" in {
      val recipe = Items.IRON_INGOT + redDye to Items.APPLE withShape
        """
          |.i.
          |.i.
          |.r.
        """.stripMargin

      val params = RecipeSupport.calcMCParamsArray(recipe)

      params(0) === " i " // scalastyle:ignore
      params(1) === " i "
      params(2) === " r "
      params(3) === 'i'
      params(4).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(Items.IRON_INGOT))
      params(5) === 'r'
      params(6).asInstanceOf[ItemStack] must beSameStackAs(redDye)
    }
  }
}
