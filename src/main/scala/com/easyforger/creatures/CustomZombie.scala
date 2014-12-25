package com.easyforger.creatures

import mods.SimpleMod
import net.minecraft.entity.monster.EntityZombie
import net.minecraft.world.World

case class ZombieConfig(common: CommonEntityConfig = CommonEntityConfig()) extends CreatureConfig

class CustomZombie(world: World) extends EntityZombie(world) with CommonCustomMonster {
  val zombie = SimpleMod.creaturesSeq.find(_.isInstanceOf[ZombieConfig]).map(_.asInstanceOf[ZombieConfig]).getOrElse(new ZombieConfig())
  val config = zombie.common

  init()
}
