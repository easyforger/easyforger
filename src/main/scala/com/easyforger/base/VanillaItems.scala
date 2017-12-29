/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.base

import net.minecraft.block.Block
import net.minecraft.init.Items
import net.minecraft.item.{EnumDyeColor, Item, ItemStack}

trait Dye {
  def itemStack: ItemStack
}

trait VanillaItems {
  implicit def toItemStack(item: Item): ItemStack = new ItemStack(item, 1)
  implicit def toItemStack(block: Block): ItemStack = new ItemStack(block, 1)

  private def itemStackFor(color: EnumDyeColor): ItemStack = new ItemStack(Items.DYE, 1, color.getMetadata)

  object blackDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.BLACK) }
  object redDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.RED) }
  object greenDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.GREEN) }
  object brownDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.BROWN) }
  object blueDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.BLUE) }
  object purpleDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.PURPLE) }
  object cyanDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.CYAN) }
  object silverDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.SILVER) }
  object grayDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.GRAY) }
  object pinkDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.PINK) }
  object limeDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.LIME) }
  object yellowDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.YELLOW) }
  object lightBlueDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.LIGHT_BLUE) }
  object magentaDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.MAGENTA) }
  object orangeDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.ORANGE) }
  object whiteDye extends Dye { val itemStack: ItemStack = itemStackFor(EnumDyeColor.WHITE) }
}
