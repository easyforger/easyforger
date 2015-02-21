package com.easyforger.base

import com.easyforger.chests.VanillaChests
import com.easyforger.creatures.VanillaCreatures
import com.easyforger.dungeons.VanillaDungeons

trait EasyForger extends ItemStackConversions with VanillaChests with VanillaDungeons with VanillaCreatures
