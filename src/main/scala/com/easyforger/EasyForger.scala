package com.easyforger

import com.easyforger.creatures.CreaturesHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import mods.SimpleMod

@Mod(modid = "com.easyforger", name = "EasyForger", version = "0.1", modLanguage = "scala")
object EasyForger {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    new CreaturesHandler(SimpleMod.creaturesSeq).registerModdedVanillaCreatures()
  }
}
