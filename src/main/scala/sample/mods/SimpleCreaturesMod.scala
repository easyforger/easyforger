package sample.mods

import com.easyforger.base.EasyForger
import com.easyforger.creatures._
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.init.Items._

@Mod(modid = "easyforger_creatures_simple", name = "EasyForger Vanilla Creatures Replacements", version = "0.1", modLanguage = "scala")
object SimpleCreaturesMod extends EasyForger {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    creatures(
      creeper(
        common(
          dropItem = diamond,
          heldItem = diamond_sword
        ),
        explosionRadius = 100,
        powered = false
      ),
      zombie(
        common(
          heldItem = diamond_sword,
          dropItem = diamond
        )
      ),
      skeleton(
        common(
          dropItem = diamond,
          heldItem = stone_sword
        ),
        behavior = skeleton => new SkeletonBehavior {
          override def dropFewItems(recentlyHit: Boolean, lootingLevel: Int) = {
            if (skeleton.getSkeletonType == 1) {
              skeleton.dropItem(diamond, 1)
            } else {
              skeleton.dropItem(emerald, 1)
            }
          }
        }
      )
    )
  }
}
