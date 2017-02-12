/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.EntityLiving
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.EnumHand

case class CommonEntityConfig(dropItem: Option[Item], heldItemMainHand: Option[ItemStack], heldItemOffHand: Option[ItemStack])

trait CommonCustomMonster extends EntityLiving {
  val config: CommonEntityConfig

  /**
    * TODO: this is bad because implies "memory-based" programming. Can we do this better?
    * issue: https://github.com/easyforger/easyforger/issues/64
    *
    * Subclasses should call this method as their constructor's last statement.
    */
  def init() {
    config.heldItemMainHand.foreach(setHeldItem(EnumHand.MAIN_HAND, _))
    config.heldItemOffHand.foreach(setHeldItem(EnumHand.OFF_HAND, _))
  }

  // TODO: This is apparently not working anymore - the new `dropLoot` method seems to require
  // the usage of the loot table mechanism - this will be fixed by: https://github.com/easyforger/easyforger/issues/73
  override def getDropItem: Item = config.dropItem.getOrElse(super.getDropItem)
}
