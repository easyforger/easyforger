package com.easyforger.items

import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.ItemSword

class EFItemSword(val modId: String, val name: String, material: ToolMaterial, val subItemsNames: List[String] = Nil)
  extends ItemSword(material) with ItemCommon
