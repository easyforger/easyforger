package com.easyforger.items

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{ItemFood, ItemStack}
import net.minecraft.potion.PotionEffect
import net.minecraft.world.World

class EasyForgerItemFood(val modId: String, val name: String, food: Int, saturation: Float, wolfFood: Boolean,
                         val subItemsNames: List[String] = Nil)
  extends ItemFood(food, saturation, wolfFood) with ItemCommon {

  // store the creation function, need a new object every time the potion is going to be applied
  private var extraPotionEffects = List.empty[(() => PotionEffect, Float)]

  override def setPotionEffect(potionId: Int, duration: Int, level: Int, probability: Float): ItemFood =
    super.setPotionEffect(potionId, duration, level, probability)

  def addPotionEffect(potionId: Int, duration: Int, level: Int, probability: Float): Unit = {
    extraPotionEffects = extraPotionEffects :+ (() => new PotionEffect(potionId, duration * 20, level)) -> probability
  }

  override def onFoodEaten(itemStack: ItemStack, world: World, player: EntityPlayer): Unit = {
    super.onFoodEaten(itemStack, world, player)

    if (!world.isRemote)
      for ((effect, prob) <- extraPotionEffects) {
        if (world.rand.nextFloat() < prob) player.addPotionEffect(effect.apply())
      }
  }
}
