/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import utest._ // scalastyle:ignore

object RecipeCompositionTest {
  val tests = Tests {
    "Don't compile invalid recipes" - {
      "recipe multiplier in source position" - compileError(
        """
          import net.minecraft.init.{Blocks, Items}
          import com.easyforger.tester.uTestRunnerMod._

          smelting(
            10 * Blocks.TORCH to Items.COAL
          )
        """
      )
    }
  }
}
