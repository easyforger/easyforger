/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.monster.EntityCreeper
import net.minecraft.world.World

case class CreeperConfig(common: CommonEntityConfig = CommonEntityConfig(None, None, None),
                         fuseTime: Option[Int] = None,
                         explosionRadius: Option[Int] = None,
                         powered: Option[Boolean] = None) extends CreatureConfig

class EFCustomCreeper(world: World) extends EntityCreeper(world) with CommonCustomMonster {
  val creeperConfig = VanillaCreatures.creeperConfig
  val config = creeperConfig.common

  creeperConfig.fuseTime.foreach(setIntField(classOf[EntityCreeper], this, "fuseTime", _))
  creeperConfig.explosionRadius.foreach(setIntField(classOf[EntityCreeper], this, "explosionRadius", _))

  init()

  override def getPowered: Boolean = creeperConfig.powered.getOrElse(super.getPowered)
}

object EFCustomCreeper {
  val bgColor = 0x66ff99
  val fgColor = 0x77ee55
}
