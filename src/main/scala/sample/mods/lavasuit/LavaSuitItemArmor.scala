package sample.mods.lavasuit

import com.easyforger.items.{ArmorType, EasyForgerItemArmor}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemArmor.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.potion.{Potion, PotionEffect}
import net.minecraft.world.World

class LavaSuitItemArmor(material: ArmorMaterial, armorType: ArmorType)
  extends EasyForgerItemArmor(LavaSuitMod.modId, material, armorType) {
  
  override def onArmorTick(world: World, player: EntityPlayer, itemStack: ItemStack): Unit = {
    player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 10, 0))
  }
}
