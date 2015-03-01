package com.easyforger.items

import net.minecraft.item.ItemFood

class EasyForgerItemFood(val modId: String, val name: String, food: Int, saturation: Float, wolfFood: Boolean,
                         val subItemsNames: List[String] = Nil)
  extends ItemFood(food, saturation, wolfFood) with ItemCommon {
  
  override def setPotionEffect(potionId: Int, duration: Int, level: Int, probability: Float): ItemFood =
    super.setPotionEffect(potionId, duration, level, probability)
}
