/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
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
