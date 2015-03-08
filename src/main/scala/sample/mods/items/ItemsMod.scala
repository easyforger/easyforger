package sample.mods.items

import com.easyforger.base.EasyForger
import com.easyforger.recipes._
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack

@Mod(modid = ItemsMod.modId, name = "EasyForger Items Sample Mod", version = "0.1", modLanguage = "scala")
object ItemsMod extends EasyForger {
  final val modId = "easyforger_items"

  val chestKey = new ItemChestKey()
  val banana = new ItemBanana()
  val explosionRod = new ItemExplosionRod()

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    GameRegistry.registerItem(chestKey, "ChestKey")
    GameRegistry.registerItem(banana, "Banana")
    GameRegistry.registerItem(explosionRod, "ExplosionRod")

    val yellowChestKey = new ItemStack(chestKey, 1, chestKey.metaForSubItemName("yellow"))
    val redChestKey = new ItemStack(chestKey, 1, chestKey.metaForSubItemName("red"))
    val blueChestKey = new ItemStack(chestKey, 1, chestKey.metaForSubItemName("blue"))

    crafting(
      Items.iron_ingot + yellowDye('y') to yellowChestKey withShape
        """
          |...
          |iiy
          |..i
        """.stripMargin,
      Items.iron_ingot + redDye to redChestKey withShape
        """
          |...
          |iir
          |..i
        """.stripMargin,
      Items.iron_ingot + blueDye('b') to blueChestKey withShape
        """
          |...
          |iib
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
