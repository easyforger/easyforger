/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.chests

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityChest

class TileEntityLockedChest extends TileEntityChest {
  setCustomName("locked chest")

  override def isUsableByPlayer(player: EntityPlayer): Boolean =
    super.isUsableByPlayer(player) && hasChestKey(player)

  def hasChestKey(player: EntityPlayer): Boolean =
    isKey(player.getHeldItemMainhand) || isKey(player.getHeldItemOffhand)

  def isKey(itemStack: ItemStack): Boolean =
    itemStack != null && itemStack.getUnlocalizedName.contains("chestkey") // scalastyle:ignore
}
