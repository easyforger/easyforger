/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import com.easyforger.base.EasyForger
import net.minecraft.entity.monster.{EntityCreeper, EntitySkeleton, EntityZombie}
import net.minecraft.entity.{EntityLiving, EnumCreatureType}
import net.minecraft.util.ResourceLocation
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biome.SpawnListEntry
import net.minecraftforge.fml.common.registry.EntityRegistry

import scala.collection.JavaConverters.{asScalaBufferConverter, asScalaSetConverter}

object CreaturesHandler {
  // see EntityTracker.trackEntity for Vanilla values, creatures are included under the IAnimals entry
  val trackingRange = 80
  val updateFrequency = 3
  val sendVelocityUpdates = true

  /**
   * This should be called as soon as possible, to avoid the vanilla creatures to be ever used without the modded behavior.
   */
  def registerModdedVanillaCreatures(mod: EasyForger): Unit = {
    val biomes = Biome.EXPLORATION_BIOMES_LIST.asScala.toList

    // TODO: the mob id values below could potential clash with user-created new mobs, if they use the same value, in the same mod
    // this will be fixed as part of: https://github.com/easyforger/easyforger/issues/75
    swapMonster(mod, biomes, "EasyForgerCreeper", 10101, EFCustomCreeper.bgColor, EFCustomCreeper.fgColor, classOf[EntityCreeper], classOf[EFCustomCreeper])      //scalastyle:ignore
    swapMonster(mod, biomes, "EasyForgerZombie", 10102, EFCustomZombie.bgColor, EFCustomZombie.fgColor, classOf[EntityZombie], classOf[EFCustomZombie])           //scalastyle:ignore
    swapMonster(mod, biomes, "EasyForgerSkeleton", 10103, EFCustomSkeleton.bgColor, EFCustomSkeleton.fgColor, classOf[EntitySkeleton], classOf[EFCustomSkeleton]) //scalastyle:ignore
  }

  protected def swapMonster(mod: EasyForger, allBiomes: List[Biome], monsterName: String, monsterId: Int, backgroundEggColour: Int, foregroundEggColour: Int,
                            monsterOldClass: Class[_ <: EntityLiving], monsterNewClass: Class[_ <: EntityLiving]): Unit = {

    EntityRegistry.registerModEntity(
      new ResourceLocation(mod.modId, monsterName), monsterNewClass, monsterName, monsterId, mod,
      trackingRange, updateFrequency, sendVelocityUpdates, backgroundEggColour, foregroundEggColour)

    val creatureBiomes = allBiomes.foldLeft(Map.empty[Biome, SpawnListEntry]) { (biomes, biome) =>
      val entryOpt = biome.getSpawnableList(EnumCreatureType.MONSTER).asScala.find(_.entityClass == monsterOldClass)
      entryOpt.map(entry => biomes + (biome -> entry)).getOrElse(biomes)
    }

    EntityRegistry.removeSpawn(monsterOldClass, EnumCreatureType.MONSTER, creatureBiomes.keySet.toArray: _*)
    creatureBiomes.foreach { case (biome, entry) =>
      EntityRegistry.addSpawn(monsterNewClass, entry.itemWeight, entry.minGroupCount, entry.maxGroupCount, EnumCreatureType.MONSTER, biome)
    }
  }
}
