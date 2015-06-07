package com.easyforger.items

import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.ItemPickaxe

class EFItemPickaxe(val modId: String, val name: String, material: ToolMaterial, val subItemsNames: List[String] = Nil)
  extends ItemPickaxe(material) with ItemCommon
