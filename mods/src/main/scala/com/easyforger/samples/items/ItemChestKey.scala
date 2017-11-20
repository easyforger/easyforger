/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.items

import com.easyforger.items.EFItem
import net.minecraft.creativetab.CreativeTabs

class ItemChestKey extends EFItem(ItemsMod.modId, "chestkey", List("yellow", "red", "blue")) {
  setCreativeTab(CreativeTabs.MISC)
}
