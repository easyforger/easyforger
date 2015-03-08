package sample.mods.lavasuit

import com.easyforger.items.{Boots, ChestPlate, Helmet, Leggings}
import com.easyforger.recipes._
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.Items
import net.minecraftforge.common.util.EnumHelper

@Mod(modid = LavaSuitMod.modId, name = "EasyForger Armor LavaSuit Mod", version = "0.1", modLanguage = "scala")
object LavaSuitMod {
  final val modId = "easyforger_lavasuit"

  val lavaMaterial = EnumHelper.addArmorMaterial("lavasuit", 20, Array(4, 9, 7, 4), 10)

  val lavaHelmet = new LavaSuitItemArmor(lavaMaterial, Helmet)
  val lavaChestPlate = new LavaSuitItemArmor(lavaMaterial, ChestPlate)
  val lavaLeggings = new LavaSuitItemArmor(lavaMaterial, Leggings)
  val lavaBoots = new LavaSuitItemArmor(lavaMaterial, Boots)

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    GameRegistry.registerItem(lavaHelmet, "LavaSuitHelmet")
    GameRegistry.registerItem(lavaChestPlate, "LavaSuitChest")
    GameRegistry.registerItem(lavaLeggings, "LavaSuitLeggings")
    GameRegistry.registerItem(lavaBoots, "LavaSuitBoots")

    crafting(
      Items.iron_ingot + Items.lava_bucket to lavaHelmet withShape
        """
          |iii
          |ili
          |...
        """.stripMargin,
      Items.iron_ingot + Items.lava_bucket to lavaChestPlate withShape
        """
          |ili
          |iii
          |iii
        """.stripMargin,
      Items.iron_ingot + Items.lava_bucket to lavaLeggings withShape
        """
          |iii
          |ili
          |i.i
        """.stripMargin,
      Items.iron_ingot + Items.lava_bucket to lavaBoots withShape
        """
          |...
          |i.i
          |ili
        """.stripMargin
    )
  }
}
