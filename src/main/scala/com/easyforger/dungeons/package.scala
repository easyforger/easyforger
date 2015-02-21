package com.easyforger

import com.easyforger.chests._
import net.minecraft.item.ItemStack

package object dungeons {
  def dungeonMobs(mobMap: (String, Int)*) = VanillaDungeons.mapMobSpawn(mobMap: _*)

  def dungeonChest(item: ItemStack, minStack: Int, maxStack: Int, chance: Int): Unit =
    dungeonChestContent(item, minStack, maxStack, chance)
}
