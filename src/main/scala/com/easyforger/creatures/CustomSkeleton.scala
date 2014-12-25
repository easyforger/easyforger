package com.easyforger.creatures

import mods.SimpleMod
import net.minecraft.entity.monster.EntitySkeleton
import net.minecraft.world.World

case class SkeletonConfig(common: CommonEntityConfig = CommonEntityConfig(),
                          behavior: EntitySkeleton => SkeletonBehavior = _ => new SkeletonBehavior()) extends CreatureConfig

class CustomSkeleton(world: World) extends EntitySkeleton(world) with CommonCustomMonster {
  val skeleton = SimpleMod.creaturesSeq.find(_.isInstanceOf[SkeletonConfig]).map(_.asInstanceOf[SkeletonConfig]).getOrElse(new SkeletonConfig())
  val config = skeleton.common

  init()

  override def dropFewItems(recentlyHit: Boolean, lootingLevel: Int) =
    skeleton.behavior(this).dropFewItems(recentlyHit, lootingLevel).getOrElse(super.dropFewItems(recentlyHit, lootingLevel))
}

class SkeletonBehavior {
  def dropFewItems(recentlyHit: Boolean, lootingLevel: Int): Option[Unit] = None
}
