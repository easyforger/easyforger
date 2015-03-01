package com.easyforger.items

import java.util

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.{IIcon, MathHelper}

trait ItemCommon extends Item {
  val name: String
  val modId: String
  val subItemsNames: List[String]

  setUnlocalizedName(s"${modId}_$name")

  if (subItemsNames.isEmpty) setTextureName(modId + ":" + name)
  else setHasSubtypes(true)

  @SideOnly(Side.CLIENT)
  private var icons: Seq[IIcon] = Nil

  @SideOnly(Side.CLIENT)
  override def registerIcons(iconRegister: IIconRegister): Unit =
    if (getHasSubtypes) icons = subItemsNames.map(subName => iconRegister.registerIcon(s"$modId:${name}_$subName"))
    else super.registerIcons(iconRegister)

  override def getUnlocalizedName(itemStack: ItemStack): String =
    if (getHasSubtypes) {
      val metadata = MathHelper.clamp_int(itemStack.getItemDamage, 0, 15)
      s"${super.getUnlocalizedName}_${subItemsNames(metadata)}"
    } else
      super.getUnlocalizedName(itemStack)

  override def getIconFromDamage(damage: Int): IIcon =
    if (getHasSubtypes) icons(damage)
    else super.getIconFromDamage(damage)

  override def getSubItems(item: Item, tabs: CreativeTabs, subItems: util.List[_]): Unit =
    if (getHasSubtypes) {
      val typedSubItemsList = subItems.asInstanceOf[util.List[ItemStack]]
      subItemsNames.zipWithIndex.foreach { case (_, i) => typedSubItemsList.add(new ItemStack(this, 1, i))}
    } else
      super.getSubItems(item, tabs, subItems)

  override def setCreativeTab(creativeTab: CreativeTabs): Item = super.setCreativeTab(creativeTab)
}
