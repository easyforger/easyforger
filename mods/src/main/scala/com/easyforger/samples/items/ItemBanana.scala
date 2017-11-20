/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.items

import com.easyforger.items.EFItemFood
import net.minecraft.init.MobEffects
import net.minecraft.potion.PotionEffect

class ItemBanana extends EFItemFood(ItemsMod.modId, "banana", 5, 0.4f, false) {
  setPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 9 * 20, 1), 1f)
  addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 5 * 20, 0), 0.5f)
}
