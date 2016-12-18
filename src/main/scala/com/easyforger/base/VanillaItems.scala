/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.base

import net.minecraft.block.Block
import net.minecraft.init.Items
import net.minecraft.item.{EnumDyeColor, Item, ItemStack}

trait VanillaItems {
  implicit def toItemStack(item: Item): ItemStack = new ItemStack(item, 1)
  implicit def toItemStack(block: Block): ItemStack = new ItemStack(block, 1)

  private def itemStackFor(color: EnumDyeColor): ItemStack = new ItemStack(Items.DYE, 1, color.getMetadata)

  val blackDye = itemStackFor(EnumDyeColor.BLACK)
  val redDye = itemStackFor(EnumDyeColor.RED)
  val greenDye = itemStackFor(EnumDyeColor.GREEN)
  val brownDye = itemStackFor(EnumDyeColor.BROWN)
  val blueDye = itemStackFor(EnumDyeColor.BLUE)
  val purpleDye = itemStackFor(EnumDyeColor.PURPLE)
  val cyanDye = itemStackFor(EnumDyeColor.CYAN)
  val silverDye = itemStackFor(EnumDyeColor.SILVER)
  val grayDye = itemStackFor(EnumDyeColor.GRAY)
  val pinkDye = itemStackFor(EnumDyeColor.PINK)
  val limeDye = itemStackFor(EnumDyeColor.LIME)
  val yellowDye = itemStackFor(EnumDyeColor.YELLOW)
  val lightBlueDye = itemStackFor(EnumDyeColor.LIGHT_BLUE)
  val magentaDye = itemStackFor(EnumDyeColor.MAGENTA)
  val orangeDye = itemStackFor(EnumDyeColor.ORANGE)
  val whiteDye = itemStackFor(EnumDyeColor.WHITE)
}
