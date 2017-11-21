/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.autotester

import com.easyforger.base.EasyForger
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

@Mod(modid = Specs2RunnerMod.modId, name = "EasyForger AutoTester", version = "0.6", modLanguage = "scala")
object Specs2RunnerMod extends EasyForger {
  final val modId = "easyforger-autotester"

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    org.specs2.runner.files.run(
      Array(
        "html", "console",
        "filesrunner.basepath", "../src/main/scala",
        "stats.outdir", "../build/specs2-reports/stats",
        "html.outdir", "../build/specs2-reports",
        "html.template", "../build/specs2-reports/templates/specs2.html"
      )
    )
  }
}
