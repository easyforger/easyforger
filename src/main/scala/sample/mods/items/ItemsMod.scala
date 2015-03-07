package sample.mods.items

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry

@Mod(modid = ItemsMod.modId, name = "EasyForger Items Sample Mod", version = "0.1", modLanguage = "scala")
object ItemsMod {
  final val modId = "easyforger_items"

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    GameRegistry.registerItem(new ItemChestKey(), "ChestKey")
    GameRegistry.registerItem(new ItemBanana(), "Banana")
    GameRegistry.registerItem(new ItemExplosionRod(), "ExplosionRod")
  }
}
