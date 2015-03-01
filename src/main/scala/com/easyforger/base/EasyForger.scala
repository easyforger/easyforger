package com.easyforger.base

import com.easyforger.chests.VanillaChests
import com.easyforger.creatures.VanillaCreatures
import com.easyforger.dungeons.VanillaDungeons

/**
 * Note: when reading the EasyForger code, please keep in mind that there are several overrides in place with
 * the sole purpose of improving auto completing method param names, since the decompiled minecraft ones are terrible.
 */
trait EasyForger extends VanillaItems with VanillaChests with VanillaDungeons with VanillaCreatures
