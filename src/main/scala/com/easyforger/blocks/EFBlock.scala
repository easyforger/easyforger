/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material

class EFBlock(val modId: String, val name: String, material: Material)
  extends Block(material) with BlockCommon
