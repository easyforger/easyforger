package sample.mods.items

import com.easyforger.recipes._
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.{Blocks, Items}

@Mod(modid = ItemsMod.modId, name = "EasyForger Items Sample Mod", version = "0.1", modLanguage = "scala")
object ItemsMod {
  final val modId = "easyforger_items"

  val chestKey = new ItemChestKey()
  val banana = new ItemBanana()
  val explosionRod = new ItemExplosionRod()

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    GameRegistry.registerItem(chestKey, "ChestKey")
    GameRegistry.registerItem(banana, "Banana")
    GameRegistry.registerItem(explosionRod, "ExplosionRod")

    // TODO: recipe for colored keys, using corresponding dyes
    crafting(
      Items.iron_ingot to chestKey withShape
        """
          |...
          |iii
          |..i
        """.stripMargin,
      Items.stick + Blocks.tnt to explosionRod withShape
        """
          |..t
          |.s.
          |s..
        """.stripMargin
    )
  }
}
