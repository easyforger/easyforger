/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.EntityLiving
import net.minecraft.item.ItemStack
import net.minecraft.util.{EnumHand, ResourceLocation}

case class CommonEntityConfig(heldItemMainHand: Option[ItemStack],
                              heldItemOffHand: Option[ItemStack],
                              dropJson: Option[String])

trait CommonCustomMonster extends EntityLiving {
  val config: CommonEntityConfig

  lazy val dropJsonResource: Option[ResourceLocation] =
    config.dropJson.map(new ResourceLocation(_))

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

  override def getLootTable: ResourceLocation =
    dropJsonResource.getOrElse(super.getLootTable)
}
