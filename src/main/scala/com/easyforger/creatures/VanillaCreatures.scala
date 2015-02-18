package com.easyforger.creatures

object VanillaCreatures {
  def creatures(creatures: CreatureConfig*) = {
    creatures.foreach {
      case c: CreeperConfig => _creeperConfig = c
      case c: ZombieConfig => _zombieConfig = c
      case c: SkeletonConfig => _skeletonConfig = c
    }
    
    CreaturesHandler.registerModdedVanillaCreatures()
  }

  
  private var _creeperConfig = new CreeperConfig()
  def creeperConfig = _creeperConfig

  private var _zombieConfig = new ZombieConfig()
  def zombieConfig = _zombieConfig
  
  private var _skeletonConfig = new SkeletonConfig()
  def skeletonConfig = _skeletonConfig
}
