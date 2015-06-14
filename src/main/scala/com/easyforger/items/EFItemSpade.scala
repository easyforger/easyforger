package com.easyforger.items

import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.ItemSpade

class EFItemSpade(val modId: String, val name: String, material: ToolMaterial, val subItemsNames: List[String] = Nil)
  extends ItemSpade(material) with ItemCommon
