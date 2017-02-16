/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.monster.EntityZombie
import net.minecraft.world.World

case class ZombieConfig(common: CommonEntityConfig = CommonEntityConfig(None, None, None)) extends CreatureConfig

class EFCustomZombie(world: World) extends EntityZombie(world) with CommonCustomMonster {
  val zombie: ZombieConfig = VanillaCreatures.zombieConfig
  val config: CommonEntityConfig = zombie.common

  init()
}

object EFCustomZombie {
  val bgColor = 0x003333
  val fgColor = 0x337734
}
