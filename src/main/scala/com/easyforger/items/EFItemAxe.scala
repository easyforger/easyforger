package com.easyforger.items

import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.ItemAxe

class EFItemAxe(val modId: String, val name: String, material: ToolMaterial, val subItemsNames: List[String] = Nil)
  extends ItemAxe(material) with ItemCommon
