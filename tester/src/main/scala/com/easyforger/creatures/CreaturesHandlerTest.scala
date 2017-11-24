/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import com.easyforger.autotester.uTestRunnerMod
import net.minecraft.entity.monster.{EntityCreeper, EntitySkeleton, EntityZombie}
import net.minecraft.entity.{EntityLiving, EnumCreatureType}
import net.minecraft.world.biome.Biome
import utest._ // scalastyle:ignore

import scala.collection.JavaConverters._ // scalastyle:ignore

object CreaturesHandlerTest {
  CreaturesHandler.registerModdedVanillaCreatures(uTestRunnerMod)
  private val allSpawns = allSpawnClasses()

  val tests = Tests {
    "registering the swapped vanilla mobs" - {
      "have the new Creeper" - {
        assert(!allSpawns.contains(classOf[EntityCreeper]))
        assert(allSpawns.contains(classOf[EFCustomCreeper]))
      }

      "have the new Zombie" - {
        assert(!allSpawns.contains(classOf[EntityZombie]))
        assert(allSpawns.contains(classOf[EFCustomZombie]))
      }

      "have the new Skeleton" - {
        assert(!allSpawns.contains(classOf[EntitySkeleton]))
        assert(allSpawns.contains(classOf[EFCustomSkeleton]))
      }
    }
  }

  def allSpawnClasses(): Set[Class[_ <: EntityLiving]] =
    Biome.EXPLORATION_BIOMES_LIST.asScala.toSet.flatMap { b: Biome =>
      b.getSpawnableList(EnumCreatureType.MONSTER).asScala.toSet.map((_: Biome.SpawnListEntry).entityClass)
    }
}
