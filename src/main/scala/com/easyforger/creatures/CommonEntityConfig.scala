/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.EntityLiving
import net.minecraft.item.{ItemStack, Item}

case class CommonEntityConfig(dropItem: Option[Item] = None, heldItem: Option[ItemStack] = None)

trait CommonCustomMonster extends EntityLiving {
  val config: CommonEntityConfig

  /**
   * Subclasses should call this method as their constructor's last statement.
   */
  def init() {
    config.heldItem.foreach(setCurrentItemOrArmor(0, _))
  }

  override def getDropItem: Item = config.dropItem.getOrElse(super.getDropItem)
}
