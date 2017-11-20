/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.lavasuit

import com.easyforger.base.EasyForger
import net.minecraft.init.{Items, SoundEvents}
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraftforge.common.util.EnumHelper
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = LavaSuitMod.modId, name = "EasyForger Armor LavaSuit Mod", version = "0.5", modLanguage = "scala")
object LavaSuitMod extends EasyForger {
  final val modId = "easyforger_lavasuit"
  val materialName = "lavasuit"
  val textureName = materialName
  val durability = 20
  val armorReductions = Array(4, 9, 7, 4)
  val enchantability = 10

  val lavaMaterial = EnumHelper.addArmorMaterial(
    materialName, textureName, durability, armorReductions, enchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0)

  val lavaHelmet = new LavaSuitItemArmor(lavaMaterial, EntityEquipmentSlot.HEAD)
  val lavaChestPlate = new LavaSuitItemArmor(lavaMaterial, EntityEquipmentSlot.CHEST)
  val lavaLeggings = new LavaSuitItemArmor(lavaMaterial, EntityEquipmentSlot.LEGS)
  val lavaBoots = new LavaSuitItemArmor(lavaMaterial, EntityEquipmentSlot.FEET)

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    lavaHelmet.register()
    lavaChestPlate.register()
    lavaLeggings.register()
    lavaBoots.register()

    crafting(
      Items.IRON_INGOT + Items.LAVA_BUCKET to lavaHelmet withShape
        """
          |iii
          |ili
          |...
        """.stripMargin,
      Items.IRON_INGOT + Items.LAVA_BUCKET to lavaChestPlate withShape
        """
          |ili
          |iii
          |iii
        """.stripMargin,
      Items.IRON_INGOT + Items.LAVA_BUCKET to lavaLeggings withShape
        """
          |iii
          |ili
          |i.i
        """.stripMargin,
      Items.IRON_INGOT + Items.LAVA_BUCKET to lavaBoots withShape
        """
          |...
          |i.i
          |ili
        """.stripMargin
    )
  }
}
