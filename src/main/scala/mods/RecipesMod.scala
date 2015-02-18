package mods

import com.easyforger.recipes._
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.init.Blocks._
import net.minecraft.init.Items._

@Mod(modid = "easyforger_recipes", name = "EasyForger Recipes Examples", version = "0.1", modLanguage = "scala")
object RecipesMod {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    smelting(
      gravel to diamond_block(10) withXp 0.5,
      dirt to emerald_block,
      arrow to flint withXp 0.1,
      torch(10) to coal
    )

    crafting(
      coal + sand to diamond,
      coal + sand + red_flower to tnt,
      sapling('s') to red_flower(2) withShape
        """
          |...
          |.s.
          |.s.
        """.stripMargin,
      stick + diamond to diamond_sword withShape
        """
          |..d
          |.d.
          |s..
        """.stripMargin
    )
  }
}
