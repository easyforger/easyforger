package sample.mods.items

import com.easyforger.items.EasyForgerItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack

class ItemExplosionRod extends EasyForgerItem(ItemsMod.modId, "explosionrod") {
  setCreativeTab(CreativeTabs.tabMisc)

  override def onEntitySwing(entity: EntityLivingBase, stack: ItemStack): Boolean = {
    val target = entity.rayTrace(100, 1f)
    entity.worldObj.createExplosion(entity, target.blockX, target.blockY, target.blockZ, 4f, true)

    true
  }
}
