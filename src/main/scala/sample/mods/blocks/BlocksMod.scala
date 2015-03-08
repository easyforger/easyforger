package sample.mods.blocks

import com.easyforger.recipes._
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.Items

@Mod(modid = BlocksMod.modId, name = "EasyForger Blocks Sample Mod", version = "0.1", modLanguage = "scala")
object BlocksMod {
  final val modId = "easyforger_blocks"

  val cloth = new BlockCloth()

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    GameRegistry.registerBlock(cloth, "BlockCloth")

    crafting(
      Items.string to cloth withShape
        """
          |sss
          |s.s
          |sss
        """.stripMargin
    )
  }
}
