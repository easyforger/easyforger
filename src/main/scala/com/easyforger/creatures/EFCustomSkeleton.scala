/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.monster.EntitySkeleton
import net.minecraft.world.World

case class SkeletonConfig(common: CommonEntityConfig = CommonEntityConfig(),
                          behavior: EntitySkeleton => SkeletonBehavior = _ => new SkeletonBehavior()) extends CreatureConfig

class EFCustomSkeleton(world: World) extends EntitySkeleton(world) with CommonCustomMonster {
  val skeleton = VanillaCreatures.skeletonConfig
  val config = skeleton.common

  init()

  override def dropFewItems(recentlyHit: Boolean, lootingLevel: Int): Unit =
    skeleton.behavior(this).dropFewItems(recentlyHit, lootingLevel).getOrElse(super.dropFewItems(recentlyHit, lootingLevel))
}

object EFCustomSkeleton {
  val bgColor = 0x002266
  val fgColor = 0x332266
}

class SkeletonBehavior {
  def dropFewItems(recentlyHit: Boolean, lootingLevel: Int): Option[Unit] = None
}
