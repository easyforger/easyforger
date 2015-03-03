package com.easyforger.items

import net.minecraft.entity.Entity
import net.minecraft.item.ItemArmor.ArmorMaterial
import net.minecraft.item.{ItemArmor, ItemStack}

class EasyForgerItemArmor(val modId: String, material: ArmorMaterial, armorType: ArmorType, val subItemsNames: List[String] = Nil)
  extends ItemArmor(material, 0, armorType.id) with ItemCommon {
  
  lazy val name = s"${material.name()}_${armorType.name}"

  override def getArmorTexture(stack: ItemStack, entity: Entity, slot: Int, subType: String): String = {
    val texture1 = List(s"${modId}_${material.name()}_helmet", s"${modId}_${material.name()}_chestplate", s"${modId}_${material.name()}_boots")
    val texture2 = List(s"${modId}_${material.name()}_leggings")

    if (texture1.exists(stack.getItem.getUnlocalizedName.contains(_))) s"$modId:textures/models/armor/${material.name()}_layer_1.png"
    else if (texture2.exists(stack.getItem.getUnlocalizedName.contains(_))) s"$modId:textures/models/armor/${material.name()}_layer_2.png"
    else null
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
