/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.monster.EntityZombie
import net.minecraft.world.World

case class ZombieConfig(common: CommonEntityConfig = CommonEntityConfig()) extends CreatureConfig

class CustomZombie(world: World) extends EntityZombie(world) with CommonCustomMonster {
  val zombie = VanillaCreatures.zombieConfig
  val config = zombie.common

  init()
}

object CustomZombie {
  val bgColor = 0x003333
  val fgColor = 0x337734
}
