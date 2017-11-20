/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.items

import com.easyforger.items.EFItemSpade
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.ItemStack

class ItemQuickSpade extends EFItemSpade(ItemsMod.modId, "quickspade", ToolMaterial.IRON) {
  override def getStrVsBlock(stack: ItemStack, state: IBlockState): Float = super.getStrVsBlock(stack, state) * 2
}
