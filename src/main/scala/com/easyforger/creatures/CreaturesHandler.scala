/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.creatures

import net.minecraft.entity.monster.{EntityCreeper, EntitySkeleton, EntityZombie}
import net.minecraft.entity.{EntityLiving, EnumCreatureType}
import net.minecraft.world.biome.BiomeGenBase
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry
import net.minecraftforge.fml.common.registry.EntityRegistry

import scala.collection.JavaConverters.asScalaBufferConverter

object CreaturesHandler {

  /**
   * This should be called as soon as possible, to avoid the vanilla creatures to be ever used without the modded behavior.
   */
  def registerModdedVanillaCreatures(): Unit = {
    val availableBiomes = BiomeGenBase.getBiomeGenArray.filterNot(_ == null) // scalastyle:ignore

    swapMonster(availableBiomes, "EasyForgerCreeper", CustomCreeper.bgColor, CustomCreeper.fgColor, classOf[EntityCreeper], classOf[CustomCreeper])
    swapMonster(availableBiomes, "EasyForgerZombie", CustomZombie.bgColor, CustomZombie.fgColor, classOf[EntityZombie], classOf[CustomZombie])
    swapMonster(availableBiomes, "EasyForgerSkeleton", CustomSkeleton.bgColor, CustomSkeleton.fgColor, classOf[EntitySkeleton], classOf[CustomSkeleton])
  }

  def swapMonster(allBiomes: Array[BiomeGenBase], monsterName: String, backgroundEggColour: Int, foregroundEggColour: Int,
                  monsterOldClass: Class[_ <: EntityLiving], monsterNewClass: Class[_ <: EntityLiving]): Unit = {

    EntityRegistry.registerGlobalEntityID(monsterNewClass, monsterName, EntityRegistry.findGlobalUniqueEntityId(), backgroundEggColour, foregroundEggColour)

    val creatureBiomes = allBiomes.foldLeft(Map.empty[BiomeGenBase, SpawnListEntry]) { (biomes, biome) =>
      val entryOpt = biome.getSpawnableList(EnumCreatureType.MONSTER).asScala.find(_.asInstanceOf[SpawnListEntry].entityClass == monsterOldClass)
      entryOpt.map(entry => biomes + (biome -> entry.asInstanceOf[SpawnListEntry])).getOrElse(biomes)
    }

    EntityRegistry.removeSpawn(monsterOldClass, EnumCreatureType.MONSTER, creatureBiomes.keySet.toArray: _*)
    creatureBiomes.foreach { case (biome, entry) =>
      EntityRegistry.addSpawn(monsterNewClass, entry.itemWeight, entry.minGroupCount, entry.maxGroupCount, EnumCreatureType.MONSTER, biome)
    }
  }
}
