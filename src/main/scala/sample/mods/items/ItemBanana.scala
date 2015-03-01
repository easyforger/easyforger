package sample.mods.items

import com.easyforger.items.EasyForgerItemFood
import net.minecraft.potion.Potion

class ItemBanana extends EasyForgerItemFood(ItemsMod.modId, "banana", 5, 0.4f, false) {
  setPotionEffect(Potion.jump.id, 9, 1, 1f)
  addPotionEffect(Potion.digSlowdown.id, 5, 0, 0.5f)
}
