/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.items

import com.easyforger.items.EFItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack

class ItemExplosionRod extends EFItem(ItemsMod.modId, "explosionrod") {
  setCreativeTab(CreativeTabs.MISC)
  val blockReach = 100

  override def onEntitySwing(entity: EntityLivingBase, stack: ItemStack): Boolean = {
    val target = entity.rayTrace(blockReach, 1f)
    entity.world.createExplosion(entity, target.getBlockPos.getX, target.getBlockPos.getY, target.getBlockPos.getZ, 4f, true)

    false
  }
}
