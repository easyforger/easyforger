package sample.mods.lavasuit

import com.easyforger.items.{Boots, Leggings, ChestPlate, Helmet}
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.common.util.EnumHelper

@Mod(modid = LavaSuitMod.modId, name = "EasyForger Armor LavaSuit Mod", version = "0.1", modLanguage = "scala")
object LavaSuitMod {
  final val modId = "easyforger_lavasuit"

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    val lavaMaterial = EnumHelper.addArmorMaterial("lavasuit", 20, Array(4, 9, 7, 4), 10)

    GameRegistry.registerItem(new LavaSuitItemArmor(lavaMaterial, Helmet), "LavaSuitHelmet")
    GameRegistry.registerItem(new LavaSuitItemArmor(lavaMaterial, ChestPlate), "LavaSuitChest")
    GameRegistry.registerItem(new LavaSuitItemArmor(lavaMaterial, Leggings), "LavaSuitLeggings")
    GameRegistry.registerItem(new LavaSuitItemArmor(lavaMaterial, Boots), "LavaSuitBoots")
  }
}
