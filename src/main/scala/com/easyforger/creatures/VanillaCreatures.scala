/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.monster.EntitySkeleton
import net.minecraft.item.{Item, ItemStack}

trait CreatureConfig

trait VanillaCreatures {
  object EntityName extends Enumeration {
    type EntityName = Value
    val Item, XPOrb, LeashKnot, Painting, Arrow, Snowball, Fireball, SmallFireball, ThrownEnderpearl,
    EyeOfEnderSignal, ThrownPotion, ThrownExpBottle, ItemFrame, WitherSkull, PrimedTnt, FallingSand,
    FireworksRocketEntity, Boat, MinecartRideable, MinecartChest, MinecartFurnace, MinecartTNT,
    MinecartHopper, MinecartSpawner, MinecartCommandBlock, Mob, Monster, Creeper, Skeleton,
    Spider, Giant, Zombie, Slime, Ghast, PigZombie, Enderman, CaveSpider, Silverfish, Blaze,
    LavaSlime, EnderDragon, WitherBoss, Bat, Witch, Pig, Sheep, Cow, Chicken, Squid, Wolf,
    MushroomCow, SnowMan, Ozelot, VillagerGolem, EntityHorse, Villager, EnderCrystal = Value
  }

  def creatures(creatures: CreatureConfig*): Unit = VanillaCreatures.creatures(creatures: _*)

  def common(dropItem: Option[Item] = None, heldItem: Option[ItemStack] = None): CommonEntityConfig =
    CommonEntityConfig(dropItem, heldItem)

  def creeper(common: CommonEntityConfig = CommonEntityConfig(), fuseTime: Option[Int] = None,
              explosionRadius: Option[Int] = None, powered: Option[Boolean] = None): CreeperConfig =
    new CreeperConfig(common, fuseTime, explosionRadius, powered)

  def zombie(common: CommonEntityConfig = CommonEntityConfig()): ZombieConfig =
    new ZombieConfig(common)

  def skeleton(common: CommonEntityConfig = CommonEntityConfig(),
               behavior: EntitySkeleton => SkeletonBehavior = _ => new SkeletonBehavior()): SkeletonConfig =
    new SkeletonConfig(common, behavior)

  // methods to hide Some and None from the kids in the optional arguments
  implicit def intToOption(i: Int): Option[Int] = Option(i)
  implicit def booleanToOption(b: Boolean): Option[Boolean] = Option(b)
  implicit def itemToOption(i: Item): Option[Item] = Option(i)
  implicit def itemToItemStackOption(i: Item): Option[ItemStack] = Option(new ItemStack(i, 1))
  implicit def entityItemToUnitOption(e: EntityItem): Option[Unit] = Option(())
}

object VanillaCreatures {
  def creatures(creatures: CreatureConfig*): Unit = {
    creatures.foreach {
      case c: CreeperConfig => _creeperConfig = c
      case c: ZombieConfig => _zombieConfig = c
      case c: SkeletonConfig => _skeletonConfig = c
    }
    
    CreaturesHandler.registerModdedVanillaCreatures()
  }

  private var _creeperConfig = new CreeperConfig()
  def creeperConfig: CreeperConfig = _creeperConfig

  private var _zombieConfig = new ZombieConfig()
  def zombieConfig: ZombieConfig = _zombieConfig
  
  private var _skeletonConfig = new SkeletonConfig()
  def skeletonConfig: SkeletonConfig = _skeletonConfig
}
