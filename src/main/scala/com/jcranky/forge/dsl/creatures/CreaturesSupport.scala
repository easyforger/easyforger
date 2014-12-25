package com.jcranky.forge.dsl.creatures

import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.monster.EntitySkeleton
import net.minecraft.item.{Item, ItemStack}

trait CreatureConfig

trait CreaturesSupport {
  var creaturesSeq = Seq.empty[CreatureConfig]

  def creatures(cs: => Unit) = cs

  def common(dropItem: Option[Item] = None, heldItem: Option[ItemStack] = None) = CommonEntityConfig(dropItem, heldItem)

  def creeper(common: CommonEntityConfig = CommonEntityConfig(),
              fuseTime: Option[Int] = None,
              explosionRadius: Option[Int] = None,
              powered: Option[Boolean] = None) = {

    val c = new CreeperConfig(common, fuseTime, explosionRadius, powered)
    creaturesSeq = creaturesSeq :+ c
  }

  def zombie(common: CommonEntityConfig = CommonEntityConfig()) = {
    val z = new ZombieConfig(common)
    creaturesSeq = creaturesSeq :+ z
  }

  def skeleton(common: CommonEntityConfig = CommonEntityConfig(),
               behavior: EntitySkeleton => SkeletonBehavior = _ => new SkeletonBehavior()) = {

    val s = new SkeletonConfig(common, behavior)
    creaturesSeq = creaturesSeq :+ s
  }

  // methods to hide Some and None from the kids in the optional arguments
  implicit def intToOption(i: Int) = Option(i)
  implicit def booleanToOption(b: Boolean) = Option(b)
  implicit def itemToOption(i: Item) = Option(i)
  implicit def itemToItemStackOption(i: Item) = Option(new ItemStack(i, 1))
  implicit def entityItemToUnitOption(e: EntityItem) = Option(())
}

object CreaturesSupport {
  def setIntField(clazz: Class[_], obj: AnyRef, fieldName: String, value: Int) = {
    val field = clazz.getDeclaredField(fieldName)
    field.setAccessible(true)
    field.setInt(obj, value)
    field.setAccessible(false)
  }

//  def callMethod(clazz: Class[_], obj: AnyRef, methodName: String, paramTypes: Seq[Class[_]], args: Seq[Object]) = {
//    val method = clazz.getDeclaredMethod(methodName, paramTypes: _*)
//    method.setAccessible(true)
//    method.invoke(obj, args: _*)
//    //method.setAccessible(false)
//  }
}
