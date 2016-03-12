/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.items

import java.util

import net.minecraft.client.Minecraft
import net.minecraft.client.resources.model.{ModelBakery, ModelResourceLocation}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.MathHelper
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

trait ItemCommon extends Item {
  val modId: String
  val name: String
  val subItemsNames: List[String]

  val defaultMetadata = 0

  setUnlocalizedName(s"${modId}_$name")
  if (subItemsNames.nonEmpty) setHasSubtypes(true)

  def metaForSubItemName(subItemName: String): Int =
    if (subItemsNames.isEmpty) 0
    else subItemsNames.indexOf(subItemName)

  override def getMetadata(damage: Int): Int = damage

  override def getUnlocalizedName(itemStack: ItemStack): String =
    if (getHasSubtypes) {
      val metadata = MathHelper.clamp_int(itemStack.getItemDamage, 0, 15)
      s"${super.getUnlocalizedName}_${subItemsNames(metadata)}"
    } else
      super.getUnlocalizedName(itemStack)

  @SideOnly(Side.CLIENT)
  override def getSubItems(item: Item, tabs: CreativeTabs, subItems: util.List[ItemStack]): Unit =
    if (getHasSubtypes)
      subItemsNames.zipWithIndex.foreach { case (_, i) => subItems.add(new ItemStack(this, 1, i)) }
    else
      super.getSubItems(item, tabs, subItems)

  override def setCreativeTab(creativeTab: CreativeTabs): Item = super.setCreativeTab(creativeTab)

  /**
   * Caution: this method must be called from inside the init() method of your mod!
   */
  def register(): Unit = {
    registerItem()
    registerItemModel()
  }

  def registerItem(): Unit = GameRegistry.registerItem(this, name)

  /**
   * Caution: this method must be called from inside the init() method of your mod!
   */
  def registerItemModel(): Unit = if (subItemsNames.isEmpty) {
    val itemModelResourceLocation = new ModelResourceLocation(s"$modId:$name", "inventory")
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(this, defaultMetadata, itemModelResourceLocation)

  } else {
    ModelBakery.addVariantName(this, subItemsNames.map(s"$modId:${name}_" + _): _*)
    subItemsNames.zipWithIndex.foreach { case (subItemName, metadata) =>
      val itemModelName = s"$modId:${name}_$subItemName"
      val itemModelResourceLocation = new ModelResourceLocation(itemModelName, "inventory")
      Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(this, metadata, itemModelResourceLocation)
    }
  }
}
