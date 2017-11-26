/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.tester

import com.easyforger.base.{EasyForger, LootTableLoadEventReplacerTest}
import com.easyforger.creatures.CreaturesHandlerTest
import com.easyforger.items.EFItemArmorTest
import com.easyforger.recipes.test.{RecipeSupportTest, RegisterRecipesTest, ShapedRecipesTest, SmeltingRecipesTest}
import com.easyforger.util.Version
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import utest.TestRunner

import scala.util.control.NonFatal

@Mod(modid = uTestRunnerMod.modId, name = "EasyForger Integration Tester", version = Version.version, modLanguage = "scala")
object uTestRunnerMod extends EasyForger {
  final val modId = "easyforger-integration-tester"

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    // the tests are manually executed here, so we don't need to extend TestSuite in the tests themselves
    // and maybe we actually _can't_ do it, or the tests might be picked up by gradle in the test phase, and they would fail

    val allTests = Map(
      CreaturesHandlerTest.getClass.getSimpleName -> CreaturesHandlerTest.tests,
      EFItemArmorTest.getClass.getSimpleName -> EFItemArmorTest.tests,
      RecipeSupportTest.getClass.getSimpleName -> RecipeSupportTest.tests,
      RegisterRecipesTest.getClass.getSimpleName -> RegisterRecipesTest.tests,
      ShapedRecipesTest.getClass.getSimpleName -> ShapedRecipesTest.tests,
      SmeltingRecipesTest.getClass.getSimpleName -> SmeltingRecipesTest.tests,
      LootTableLoadEventReplacerTest.getClass.getSimpleName -> LootTableLoadEventReplacerTest.tests
    )

    println(" =========== Running integration tests =========== ") // scalastyle:ignore
    allTests.map { case (name, tests) =>
      try {
        TestRunner.runAndPrint(tests, name)
      } catch {
        case NonFatal(t) =>
          println(s"Failed to run $name") // scalastyle:ignore
          t.printStackTrace()
      }
    }
    println(" =========== Finished integration tests ========== ") // scalastyle:ignore
  }
}
