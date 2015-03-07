package sample.mods

import com.easyforger.base.EasyForger
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.init.Items._

@Mod(modid = "easyforger_chests", name = "EasyForger Chests Mod Examples", version = "0.1", modLanguage = "scala")
object VanillaChestsMod extends EasyForger {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    removeChestContent(
      chest = ChestName.bonusChest,
      item = stick
    )
    
    addChestContent(
      chest = ChestName.bonusChest,
      item = diamond_axe,
      minStack = 1,
      maxStack = 2,
      chance = 50
    )

    addChestContent(
      chest = ChestName.mineshaftCorridor,
      item = emerald,
      minStack = 3,
      maxStack = 20,
      chance = 13
    )
  }
}
