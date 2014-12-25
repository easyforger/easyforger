package mods

import com.jcranky.forge.dsl.DSLMod
import com.jcranky.forge.dsl.creatures.SkeletonBehavior

object SimpleMod extends DSLMod {
  import net.minecraft.init.Blocks._
  import net.minecraft.init.Items._

  smelting {
    smelt(gravel to diamond_block(10)) withXp 0.5
    smelt(dirt to emerald_block)
    smelt(arrow to flint) withXp 0.1
    smelt(torch(10) to coal)
  }

  recipes {
    recipe(coal + sand to diamond)
    recipe(coal + sand + red_flower to tnt)
    recipe(sapling('s') to red_flower(2) withShape
      """
        |...
        |.s.
        |.s.
      """.stripMargin)
    recipe(stick + diamond to diamond_sword withShape
      """
        |..d
        |.d.
        |s..
      """.stripMargin)
  }

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
