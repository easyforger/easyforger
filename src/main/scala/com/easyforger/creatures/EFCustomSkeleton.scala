/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.monster.EntitySkeleton
import net.minecraft.world.World

case class SkeletonConfig(common: CommonEntityConfig = CommonEntityConfig(None, None, None)) extends CreatureConfig

class EFCustomSkeleton(world: World) extends EntitySkeleton(world) with CommonCustomMonster {
  val skeleton: SkeletonConfig = VanillaCreatures.skeletonConfig
  val config: CommonEntityConfig = skeleton.common

  init()
}

object EFCustomSkeleton {
  val bgColor = 0x002266
  val fgColor = 0x332266
}
