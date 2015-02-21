package com.easyforger.dungeons

import com.easyforger.chests._
import com.easyforger.creatures.VanillaCreatures
import net.minecraft.item.ItemStack
import net.minecraftforge.common.DungeonHooks

/**
 * According to Forge, the default dungeon possibilities are:
 * Spider   100
 * Skeleton 100
 * Zombie   200
 */
trait VanillaDungeons {
  self: VanillaChests with VanillaCreatures =>

  import EntityName._

  def dungeonMobs(mobMap: (EntityName, Int)*) = mapMobSpawn(mobMap: _*)

  def dungeonChest(item: ItemStack, minStack: Int, maxStack: Int, chance: Int): Unit =
    addChestContent(ChestName.dungeonChest, item, minStack, maxStack, chance)


  private def mapMobSpawn(mobMap: (EntityName, Int)*) = mobMap.foreach {
    case (entityName, spawnChance) =>
      DungeonHooks.removeDungeonMob(entityName.toString)
      DungeonHooks.addDungeonMob(entityName.toString, spawnChance)
  }
}
