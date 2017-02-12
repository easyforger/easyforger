/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import com.easyforger.base.EasyForger
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.monster.EntitySkeleton
import net.minecraft.item.{Item, ItemStack}

/**
  * A trait for allowing vanilla creature to be configured. See the specific implementations for details.
  * */
trait CreatureConfig

// TODO: perhaps all mob drops could now be implemented with the loot tables they return ? -> https://github.com/easyforger/easyforger/issues/73

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

  def creatures(mod: EasyForger, creatures: CreatureConfig*): Unit =
    VanillaCreatures.creatures(mod, creatures: _*)

  def common(dropItem: Option[Item] = None, heldItemMainHand: Option[ItemStack] = None, heldItemOffHand: Option[ItemStack] = None): CommonEntityConfig =
    CommonEntityConfig(dropItem, heldItemMainHand, heldItemOffHand)

  def creeper(commonConfig: CommonEntityConfig = common(), fuseTime: Option[Int] = None,
              explosionRadius: Option[Int] = None, powered: Option[Boolean] = None): CreeperConfig =
    new CreeperConfig(commonConfig, fuseTime, explosionRadius, powered)

  def zombie(commonConfig: CommonEntityConfig = common()): ZombieConfig =
    new ZombieConfig(commonConfig)

  def skeleton(commonConfig: CommonEntityConfig = common(),
               behavior: EntitySkeleton => SkeletonBehavior = _ => new SkeletonBehavior()): SkeletonConfig =
    new SkeletonConfig(commonConfig, behavior)

  // methods to hide Some and None from the kids in the optional arguments
  implicit def intToOption(i: Int): Option[Int] = Option(i)
  implicit def booleanToOption(b: Boolean): Option[Boolean] = Option(b)
  implicit def itemToOption(i: Item): Option[Item] = Option(i)
  implicit def itemToItemStackOption(i: Item): Option[ItemStack] = Option(new ItemStack(i, 1))
  implicit def entityItemToUnitOption(e: EntityItem): Option[Unit] = Option(())
}

object VanillaCreatures {
  def creatures(mod: EasyForger, creatures: CreatureConfig*): Unit = {
    creatures.foreach {
      case c: CreeperConfig => _creeperConfig = c
      case c: ZombieConfig => _zombieConfig = c
      case c: SkeletonConfig => _skeletonConfig = c
    }

    CreaturesHandler.registerModdedVanillaCreatures(mod)
  }

  // ignoring the 'var' checks below because fixing them would take a redesign of the API - which will
  // be done a some point in the future

  private var _creeperConfig = new CreeperConfig() // scalastyle:ignore
  def creeperConfig: CreeperConfig = _creeperConfig

  private var _zombieConfig = new ZombieConfig() // scalastyle:ignore
  def zombieConfig: ZombieConfig = _zombieConfig

  private var _skeletonConfig = new SkeletonConfig() // scalastyle:ignore
  def skeletonConfig: SkeletonConfig = _skeletonConfig
}
