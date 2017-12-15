/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.items

import com.easyforger.items.EFItemAxe
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemHeavyAxe(modId: String) extends EFItemAxe(modId, "heavyaxe", ToolMaterial.IRON) {
  override def getStrVsBlock(stack: ItemStack, state: IBlockState): Float = super.getStrVsBlock(stack, state) / 2

  override def onBlockDestroyed(stack: ItemStack, worldIn: World, blockIn: IBlockState, pos: BlockPos, playerIn: EntityLivingBase): Boolean = {
    if (blockIn.getMaterial == Material.WOOD) {
      destroyAllConnectedWoodBlocks(worldIn, pos)
    }

    super.onBlockDestroyed(stack, worldIn, blockIn, pos, playerIn)
  }

  private def destroyAllConnectedWoodBlocks(world: World, startPos: BlockPos): Unit = {
    val allPositions = Vector(startPos.up(), startPos.down(), startPos.south(), startPos.north(), startPos.west(), startPos.east())

    for (pos <- allPositions) {
      if (world.getBlockState(pos).getMaterial == Material.WOOD) {
        world.destroyBlock(pos, true)
        destroyAllConnectedWoodBlocks(world, pos)
      }
    }
  }
}
