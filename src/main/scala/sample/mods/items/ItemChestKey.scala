package sample.mods.items

import com.easyforger.items.EasyForgerItem
import net.minecraft.creativetab.CreativeTabs

class ItemChestKey extends EasyForgerItem(ItemsMod.modId, "chestkey", List("yellow", "red", "blue")) {
  setCreativeTab(CreativeTabs.tabMisc)
}
