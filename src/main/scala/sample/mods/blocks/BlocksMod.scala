package sample.mods.blocks

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry

@Mod(modid = BlocksMod.modId, name = "EasyForger Blocks Sample Mod", version = "0.1", modLanguage = "scala")
object BlocksMod {
  final val modId = "easyforger_blocks"
  
  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    GameRegistry.registerBlock(new BlockCloth(), "BlockCloth")
  }
}
