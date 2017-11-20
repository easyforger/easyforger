/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.blocks

import com.easyforger.blocks.EFBlock
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.init.Items

class BlockCloth extends EFBlock(BlocksMod.modId, "clothblock", Material.CLOTH) {
  setHardness(0.5f)
  setResistance(1.0f)
  setSoundType(SoundType.WOOD)

  dropItem = Items.BOOK
  quantityDropped = 3
}
