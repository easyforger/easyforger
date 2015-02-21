package sample.mods

import com.easyforger.base.EasyForger
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.init.Blocks._
import net.minecraft.init.Items._

@Mod(modid = "easyforger_dungeons", name = "EasyForger Dungeons Mod Examples", version = "0.1", modLanguage = "scala")
object DungeonsMod extends EasyForger {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    dungeonMobs(
      EntityName.Creeper -> 100,
      EntityName.Zombie -> 400,
      EntityName.Enderman -> 50
    )

    dungeonChest(
      item = diamond_block,
      minStack = 1,
      maxStack = 5,
      chance = 2
    )

    dungeonChest(
      item = spawn_egg,
      minStack = 1,
      maxStack = 1,
      chance = 1
    )
  }
}
