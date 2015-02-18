package com.easyforger.creatures

import net.minecraft.entity.monster.EntityZombie
import net.minecraft.world.World

case class ZombieConfig(common: CommonEntityConfig = CommonEntityConfig()) extends CreatureConfig

class CustomZombie(world: World) extends EntityZombie(world) with CommonCustomMonster {
  val zombie = VanillaCreatures.zombieConfig
  val config = zombie.common

  init()
}
