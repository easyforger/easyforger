package sample.mods.chests

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry

@Mod(modid = LockedChestsMod.modId, name = "EasyForger Locked Chests Mod", version = "0.1", modLanguage = "scala")
object LockedChestsMod {
  final val modId = "easyforger_lockedchests"

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    GameRegistry.registerBlock(new BlockLockedChest(), "LockedChest")
    GameRegistry.registerTileEntity(classOf[TileEntityLockedChest], "LockedChestTileEntity")
  }
}
