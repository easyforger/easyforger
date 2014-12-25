package com.easyforger.creatures

import mods.SimpleMod
import net.minecraft.entity.monster.EntityCreeper
import net.minecraft.world.World

case class CreeperConfig(common: CommonEntityConfig = CommonEntityConfig(),
                         fuseTime: Option[Int] = None,
                         explosionRadius: Option[Int] = None,
                         powered: Option[Boolean] = None) extends CreatureConfig

class CustomCreeper(world: World) extends EntityCreeper(world) with CommonCustomMonster {
  val creeper = SimpleMod.creaturesSeq.find(_.isInstanceOf[CreeperConfig]).map(_.asInstanceOf[CreeperConfig]).getOrElse(new CreeperConfig())
  val config = creeper.common

  import CreaturesSupport._

  creeper.fuseTime.foreach(setIntField(classOf[EntityCreeper], this, "fuseTime", _))
  creeper.explosionRadius.foreach(setIntField(classOf[EntityCreeper], this, "explosionRadius", _))

  init()

  override def getPowered = creeper.powered.getOrElse(super.getPowered)
}
