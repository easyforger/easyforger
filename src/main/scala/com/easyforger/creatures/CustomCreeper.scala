package com.easyforger.creatures

import net.minecraft.entity.monster.EntityCreeper
import net.minecraft.world.World

case class CreeperConfig(common: CommonEntityConfig = CommonEntityConfig(),
                         fuseTime: Option[Int] = None,
                         explosionRadius: Option[Int] = None,
                         powered: Option[Boolean] = None) extends CreatureConfig

class CustomCreeper(world: World) extends EntityCreeper(world) with CommonCustomMonster {1
  val creeperConfig = VanillaCreatures.creeperConfig
  val config = creeperConfig.common
  
  creeperConfig.fuseTime.foreach(setIntField(classOf[EntityCreeper], this, "fuseTime", _))
  creeperConfig.explosionRadius.foreach(setIntField(classOf[EntityCreeper], this, "explosionRadius", _))

  init()

  override def getPowered = creeperConfig.powered.getOrElse(super.getPowered)
}
