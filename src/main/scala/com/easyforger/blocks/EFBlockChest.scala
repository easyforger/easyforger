package com.easyforger.blocks

import com.easyforger.blocks.ChestType.ChestType
import net.minecraft.block.BlockChest

class EFBlockChest(val modId: String, val name: String, chestType: ChestType)
  extends BlockChest(chestType.id) with BlockCommon

object ChestType extends Enumeration {
  type ChestType = Value

  val Normal = Value(0)
  val Trapped = Value(1)
}
