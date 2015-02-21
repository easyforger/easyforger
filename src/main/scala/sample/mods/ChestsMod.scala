package sample.mods

import com.easyforger.base.EasyForger
import com.easyforger.chests._
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.init.Items._
import net.minecraftforge.common.ChestGenHooks

@Mod(modid = "easyforger_chests", name = "EasyForger Chests Mod Examples", version = "0.1", modLanguage = "scala")
object ChestsMod extends EasyForger {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    removeChestContent(
      chest = ChestGenHooks.BONUS_CHEST,
      item = stick
    )
    
    addChestContent(
      chest = ChestGenHooks.BONUS_CHEST,
      item = diamond_axe,
      minStack = 1,
      maxStack = 2,
      chance = 50
    )

    addChestContent(
      chest = ChestGenHooks.MINESHAFT_CORRIDOR,
      item = emerald,
      minStack = 3,
      maxStack = 20,
      chance = 13
    )
  }
}
