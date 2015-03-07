package sample.mods.chests

import net.minecraft.block.BlockChest
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

class BlockLockedChest extends BlockChest(0) {
  setBlockName(LockedChestsMod.modId + "_lockedchest")

  override def createNewTileEntity(world: World, p_149915_2_ : Int): TileEntity = new TileEntityLockedChest()
}
