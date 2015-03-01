package com.easyforger.items

import net.minecraft.item.Item

class EasyForgerItem(val modId: String, val name: String, val subItemsNames: List[String] = Nil)
  extends Item with ItemCommon
