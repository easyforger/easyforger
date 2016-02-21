/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.items

import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.ItemSpade

class EFItemSpade(val modId: String, val name: String, material: ToolMaterial, val subItemsNames: List[String] = Nil)
  extends ItemSpade(material) with ItemCommon
