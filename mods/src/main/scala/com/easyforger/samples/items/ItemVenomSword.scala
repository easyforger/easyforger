/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.items

import com.easyforger.items.EFItemSword
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.MobEffects
import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect

class ItemVenomSword(modId: String) extends EFItemSword(modId, "venomsword", ToolMaterial.IRON) {
  val poisonDuration = 3 * 20
  val poisonLevel = 1

  override def hitEntity(stack: ItemStack, target: EntityLivingBase, attacker: EntityLivingBase): Boolean = {
    target.addPotionEffect(new PotionEffect(MobEffects.POISON, poisonDuration, poisonLevel, false, true))
    super.hitEntity(stack, target, attacker)
  }
}
