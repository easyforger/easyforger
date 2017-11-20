/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.lavasuit

import com.easyforger.items.EFItemArmor
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
import net.minecraft.world.World

class LavaSuitItemArmor(material: ArmorMaterial, slot: EntityEquipmentSlot)
  extends EFItemArmor(LavaSuitMod.modId, material, slot) {

  override def onArmorTick(world: World, player: EntityPlayer, itemStack: ItemStack): Unit = {
    player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 10, 0, false, true))
  }
}
