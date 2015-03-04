package sample.mods.blocks

import com.easyforger.blocks.EasyForgerBlock
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.init.Items

class BlockCloth extends EasyForgerBlock(BlocksMod.modId, "clothblock", Material.cloth) {
  setHardness(0.5f)
  setResistance(1.0f)
  setStepSound(Block.soundTypeWood)

  dropItem = Items.book
  quantityDropped = 3
}
