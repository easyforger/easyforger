/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import com.easyforger.autotester.Specs2RunnerMod
import net.minecraft.entity.monster.{EntityCreeper, EntitySkeleton, EntityZombie}
import net.minecraft.entity.{EntityLiving, EnumCreatureType}
import net.minecraft.world.biome.Biome
import org.specs2.matcher.ThrownExpectations
import org.specs2.mutable.Specification

import scala.collection.JavaConverters._ // scalastyle:ignore

class CreaturesHandlerSpec extends Specification with ThrownExpectations {
  "registering the swapped vanilla mobs" should {
    CreaturesHandler.registerModdedVanillaCreatures(Specs2RunnerMod)
    val all = allSpawnClasses()

    "have the new Creeper" in {
      all must not contain classOf[EntityCreeper]
      all must contain(classOf[EFCustomCreeper])
    }

    "have the new Zombie" in {
      all must not contain classOf[EntityZombie]
      all must contain(classOf[EFCustomZombie])
    }

    "have the new Skeleton" in {
      all must not contain classOf[EntitySkeleton]
      all must contain(classOf[EFCustomSkeleton])
    }
  }

  def allSpawnClasses(): Set[Class[_ <: EntityLiving]] =
    Biome.EXPLORATION_BIOMES_LIST.asScala.toSet.flatMap { b: Biome =>
      b.getSpawnableList(EnumCreatureType.MONSTER).asScala.toSet.map((_: Biome.SpawnListEntry).entityClass)
    }
}
