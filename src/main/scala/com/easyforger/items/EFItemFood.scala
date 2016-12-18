/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.items

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{ItemFood, ItemStack}
import net.minecraft.potion.{Potion, PotionEffect}
import net.minecraft.world.World

import scala.collection.mutable.ListBuffer

class EFItemFood(val modId: String, val name: String, food: Int, saturation: Float, wolfFood: Boolean, val subItemsNames: List[String] = Nil)
  extends ItemFood(food, saturation, wolfFood) with ItemCommon {

  // store the creation function, a new PotionEffect object is needed every time the potion is going to be applied
  private val extraPotionEffects = ListBuffer.empty[(() => PotionEffect, Float)]

  def addPotionEffect(potion: Potion, duration: Int, level: Int, probability: Float): Unit = {
    extraPotionEffects += (() => new PotionEffect(potion, duration * 20, level, false, true)) -> probability
  }

  override def onFoodEaten(itemStack: ItemStack, world: World, player: EntityPlayer): Unit = {
    super.onFoodEaten(itemStack, world, player)

    if (!world.isRemote) {
      for ((effect, prob) <- extraPotionEffects) {
        if (world.rand.nextFloat() < prob) {
          player.addPotionEffect(effect.apply())
        }
      }
    }
  }
}
