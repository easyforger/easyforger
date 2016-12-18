/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.items

import net.minecraft.entity.Entity
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.inventory.EntityEquipmentSlot._ //scalastyle:ignore
import net.minecraft.item.ItemArmor.ArmorMaterial
import net.minecraft.item.{ItemArmor, ItemStack}

// TODO: this hardcoded `-1` could be bad in the future but it is not currently used: https://github.com/easyforger/easyforger/issues/65
class EFItemArmor(val modId: String, material: ArmorMaterial, slot: EntityEquipmentSlot, val subItemsNames: List[String] = Nil)
  extends ItemArmor(material, -1, slot) with ItemCommon {

  lazy val materialName = material.name().toLowerCase
  lazy val name = s"${materialName}_${mappedName(slot)}"

  override def getArmorTexture(stack: ItemStack, entity: Entity, slot: EntityEquipmentSlot, subType: String): String = {
    val texture1 = List(s"${materialName}_helmet", s"${materialName}_chestplate", s"${materialName}_boots")
    val texture2 = List(s"${materialName}_leggings")

    if (texture1.exists(stack.getItem.getUnlocalizedName.contains(_))) s"$modId:textures/models/armor/${materialName}_layer_1.png"
    else if (texture2.exists(stack.getItem.getUnlocalizedName.contains(_))) s"$modId:textures/models/armor/${materialName}_layer_2.png"
    else super.getArmorTexture(stack, entity, slot, subType)
  }

  private def mappedName(slot: EntityEquipmentSlot): String = slot match {
    case MAINHAND => MAINHAND.getName
    case OFFHAND => OFFHAND.getName
    case FEET => "boots"
    case LEGS => "leggings"
    case CHEST => "chestplate"
    case HEAD => "helmet"
  }
}
