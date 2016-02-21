/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.items

import net.minecraft.entity.Entity
import net.minecraft.item.ItemArmor.ArmorMaterial
import net.minecraft.item.{ItemArmor, ItemStack}

class EFItemArmor(val modId: String, material: ArmorMaterial, armorType: ArmorType, val subItemsNames: List[String] = Nil)
  extends ItemArmor(material, 0, armorType.id) with ItemCommon {

  lazy val name = s"${material.name()}_${armorType.name}"
  setUnlocalizedName(s"${modId}_${armorType.name}")

  override def getArmorTexture(stack: ItemStack, entity: Entity, slot: Int, subType: String): String = {
    val texture1 = List(s"${modId}_helmet", s"${modId}_chestplate", s"${modId}_boots")
    val texture2 = List(s"${modId}_leggings")

    if (texture1.exists(stack.getItem.getUnlocalizedName.contains(_))) s"$modId:textures/models/armor/${material.name()}_layer_1.png"
    else if (texture2.exists(stack.getItem.getUnlocalizedName.contains(_))) s"$modId:textures/models/armor/${material.name()}_layer_2.png"
    else super.getArmorTexture(stack, entity, slot, subType)
  }
}

sealed trait ArmorType {
  val name: String
  val id: Int
}

object Helmet extends ArmorType {
  val name = "helmet"
  val id = 0
}

object ChestPlate extends ArmorType {
  val name = "chestplate"
  val id = 1
}

object Leggings extends ArmorType {
  val name = "leggings"
  val id = 2
}

object Boots extends ArmorType {
  val name = "boots"
  val id = 3
}
