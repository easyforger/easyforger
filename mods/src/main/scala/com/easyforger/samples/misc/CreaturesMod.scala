/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.samples.misc

import com.easyforger.base.EasyForger
import com.easyforger.util.Version
import net.minecraft.init.Items
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = CreaturesMod.modId, name = "EasyForger Vanilla Creatures Replacements", version = Version.version, modLanguage = "scala")
object CreaturesMod extends EasyForger {
  final val modId = "easyforger_creatures"

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    creatures(
      creeper(
        common(
          heldItemMainHand = Items.DIAMOND_SWORD,
          dropJson = s"$modId:entities/ef_creeper"
        ),
        explosionRadius = 100, // scalastyle:ignore
        powered = false
      ),
      zombie(
        common(
          heldItemMainHand = Items.DIAMOND_SWORD,
          heldItemOffHand = Items.APPLE,
          dropJson = s"$modId:entities/ef_zombie"
        )
      ),
      skeleton(
        common(
          heldItemMainHand = Items.STONE_SWORD,
          dropJson = s"$modId:entities/ef_skeleton"
        )
      )
    )
  }
}
