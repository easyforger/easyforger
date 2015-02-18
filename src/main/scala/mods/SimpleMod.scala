package mods

import com.easyforger.creatures.{CreaturesSupport, SkeletonBehavior}

object SimpleMod extends CreaturesSupport {
  import net.minecraft.init.Items._
  
  creatures {
    creeper(
      common(
        dropItem = diamond,
        heldItem = diamond_sword
      ),
      explosionRadius = 100,
      powered = false
    )
    zombie(
      common(
        heldItem = diamond_sword,
        dropItem = diamond
      )
    )
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
  }
}
