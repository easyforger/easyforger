package sample.mods.chests

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntityChest

class TileEntityLockedChest extends TileEntityChest {
  func_145976_a("locked chest")

  override def isUseableByPlayer(player: EntityPlayer): Boolean = {
    player.getHeldItem != null && player.getHeldItem.getUnlocalizedName.contains("chestkey")
  }
}
