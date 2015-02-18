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

  override def getDropItem = config.dropItem.getOrElse(super.getDropItem)
}
