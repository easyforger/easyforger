package com.easyforger

import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.monster.EntitySkeleton
import net.minecraft.item.{Item, ItemStack}

package object creatures {

  trait CreatureConfig


  def creatures(creatures: CreatureConfig*) = VanillaCreatures.creatures(creatures: _*)

  def common(dropItem: Option[Item] = None, heldItem: Option[ItemStack] = None) = CommonEntityConfig(dropItem, heldItem)


  def creeper(common: CommonEntityConfig = CommonEntityConfig(), fuseTime: Option[Int] = None,
              explosionRadius: Option[Int] = None, powered: Option[Boolean] = None) =
    new CreeperConfig(common, fuseTime, explosionRadius, powered)

  def zombie(common: CommonEntityConfig = CommonEntityConfig()) = new ZombieConfig(common)

  def skeleton(common: CommonEntityConfig = CommonEntityConfig(),
               behavior: EntitySkeleton => SkeletonBehavior = _ => new SkeletonBehavior()) =
    new SkeletonConfig(common, behavior)


  // methods to hide Some and None from the kids in the optional arguments
  implicit def intToOption(i: Int): Option[Int] = Option(i)

  implicit def booleanToOption(b: Boolean): Option[Boolean] = Option(b)

  implicit def itemToOption(i: Item): Option[Item] = Option(i)

  implicit def itemToItemStackOption(i: Item): Option[ItemStack] = Option(new ItemStack(i, 1))

  implicit def entityItemToUnitOption(e: EntityItem): Option[Unit] = Option(())


  def setIntField(clazz: Class[_], obj: AnyRef, fieldName: String, value: Int) = {
    val field = clazz.getDeclaredField(fieldName)
    field.setAccessible(true)
    field.setInt(obj, value)
    field.setAccessible(false)
  }
}
