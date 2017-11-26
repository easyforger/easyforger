/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.misc

import com.easyforger.base.EasyForger
import com.easyforger.util.Version
import net.minecraft.init.{Blocks, Enchantments, Items}
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = RecipesMod.modId, name = "EasyForger Recipes Examples", version = Version.version, modLanguage = "scala")
object RecipesMod extends EasyForger {
  final val modId = "easyforger_recipes"

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    smelting(
      Blocks.GRAVEL to Blocks.DIAMOND_BLOCK(10) withXp 0.5,
      Blocks.DIRT to Blocks.EMERALD_BLOCK,
      Items.ARROW to Items.FLINT withXp 0.1,
      Blocks.TORCH(10) to Items.COAL,           // TODO: this `10` is ignored -> https://github.com/easyforger/easyforger/issues/70
      Items.APPLE to Items.CAKE
    )

    crafting(
      Items.COAL + Blocks.SAND to Items.DIAMOND,
      Items.COAL + Blocks.SAND + Blocks.RED_FLOWER to Blocks.TNT,
      Items.STONE_SWORD + Items.FLINT to enchanted(Items.STONE_SWORD, Enchantments.SHARPNESS, 1),
      Blocks.SAPLING('s') to Blocks.RED_FLOWER(2) withShape
        """
          |...
          |.s.
          |.s.
        """.stripMargin,
      Items.STICK + Items.DIAMOND to Items.DIAMOND_SWORD withShape
        """
          |..d
          |.d.
          |s..
        """.stripMargin,
      Blocks.DIRT + Blocks.SAND to Blocks.DIAMOND_BLOCK,
      Items.DIAMOND_SHOVEL + Blocks.OBSIDIAN to Blocks.DIAMOND_BLOCK(5),
      Items.DIAMOND + Items.CARROT to Items.ARMOR_STAND
    )
  }
}
