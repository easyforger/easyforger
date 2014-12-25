package com.jcranky.forge.dsl

import com.jcranky.forge.dsl.creatures.CreaturesHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import mods.SimpleMod

@Mod(modid = "com.jcranky.forge.dsl.interpreter", name = "DSL Interpreter", version = "0.1", modLanguage = "scala")
object DSLInterpreter {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    // precisamos de um jeito simples de registrar esses mods, ou descobrí-los dinamicamente ... ServiceLoader ?

    new CreaturesHandler(SimpleMod.creaturesSeq).registerModdedVanillaCreatures()

    // Recipes, both smelting and crafting

    SimpleMod.smeltSeq.foreach { smelt =>
      GameRegistry.addSmelting(smelt.recipe.source.itemStack, smelt.recipe.result.itemStack, smelt.xp.toFloat)
    }

    // TODO: tem Option.get demais aqui ... pelo menos validar com require?
    SimpleMod.recipesSeq.foreach { recipe =>
      if (recipe.shape.isDefined) {
        val params = recipe.shape.get.trim.replace('.', ' ').split("\n")
        val acronyms = recipe.source.flatMap(richItemStack => Seq(new Character(richItemStack.acronym), richItemStack.itemStack))
        GameRegistry.addRecipe(recipe.result.get.itemStack, (params ++ acronyms).toArray: _*)
      }
      else
        GameRegistry.addShapelessRecipe(
          recipe.result.map(_.itemStack).getOrElse(throw new IllegalArgumentException("incomplete recipe!")),
          recipe.source.map(_.itemStack).toArray: _*)
    }
  }
}
