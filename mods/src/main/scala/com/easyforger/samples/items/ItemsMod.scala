/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.items

import com.easyforger.base.EasyForger
import com.easyforger.util.Version
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = ItemsMod.modId, name = "EasyForger Items Sample Mod", version = Version.version, modLanguage = "scala")
object ItemsMod extends EasyForger {
  final val modId = "easyforger_items"

  val chestKey = new ItemChestKey(modId)
  val banana = new ItemBanana(modId)
  val explosionRod = new ItemExplosionRod(modId)
  val venomSword = new ItemVenomSword(modId)
  val quickPick = new ItemQuickPickaxe(modId)
  val quickSpade = new ItemQuickSpade(modId)
  val heavyAxe = new ItemHeavyAxe(modId)

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    chestKey.register()
    banana.register()
    explosionRod.register()
    venomSword.register()
    quickPick.register()
    quickSpade.register()
    heavyAxe.register()

    val yellowChestKey = new ItemStack(chestKey, 1, chestKey.metaForSubItemName("yellow"))
    val redChestKey = new ItemStack(chestKey, 1, chestKey.metaForSubItemName("red"))
    val blueChestKey = new ItemStack(chestKey, 1, chestKey.metaForSubItemName("blue"))

    crafting(
      Items.IRON_INGOT + yellowDye('y) to yellowChestKey withShape
        """
          |...
          |iiy
          |..i
        """.stripMargin,
      Items.IRON_INGOT + redDye to redChestKey withShape
        """
          |...
          |iir
          |..i
        """.stripMargin,
      Items.IRON_INGOT + blueDye('b) to blueChestKey withShape
        """
          |...
          |iib
          |..i
        """.stripMargin,
      Items.STICK + Blocks.TNT to explosionRod withShape
        """
          | ..t
          | .s.
          | s..
        """.stripMargin,
      Items.IRON_SWORD + Items.POISONOUS_POTATO to venomSword,
      Items.IRON_PICKAXE + Items.DIAMOND to quickPick,
      Items.IRON_SHOVEL + Items.DIAMOND to quickSpade,
      Items.IRON_AXE + Items.DIAMOND to heavyAxe,
      Items.APPLE to Blocks.CAKE,
      Blocks.COAL_BLOCK to (2 * Blocks.DIAMOND_BLOCK)
    )
  }
}
