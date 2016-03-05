/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.base

import net.minecraft.block.Block
import net.minecraft.init.Items.dye
import net.minecraft.item.{Item, ItemStack}

trait VanillaItems {
  implicit def toItemStack(item: Item): ItemStack = new ItemStack(item, 1)
  implicit def toItemStack(block: Block): ItemStack = new ItemStack(block, 1)

  // TODO: use EnumDyeColor when migrating to minecraft 1.8

  val blackDye = new ItemStack(dye, 1, 0)
  val redDye = new ItemStack(dye, 1, 1)
  val greenDye = new ItemStack(dye, 1, 2)
  val brownDye = new ItemStack(dye, 1, 3)
  val blueDye = new ItemStack(dye, 1, 4)
  val purpleDye = new ItemStack(dye, 1, 5)
  val cyanDye = new ItemStack(dye, 1, 6)
  val silverDye = new ItemStack(dye, 1, 7)
  val grayDye = new ItemStack(dye, 1, 8)
  val pinkDye = new ItemStack(dye, 1, 9)
  val limeDye = new ItemStack(dye, 1, 10)
  val yellowDye = new ItemStack(dye, 1, 11)
  val lightBlueDye = new ItemStack(dye, 1, 12)
  val magentaDye = new ItemStack(dye, 1, 13)
  val orangeDye = new ItemStack(dye, 1, 14)
  val whiteDye = new ItemStack(dye, 1, 15)
}
