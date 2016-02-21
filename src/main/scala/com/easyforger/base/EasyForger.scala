/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.base

import com.easyforger.chests.VanillaChests
import com.easyforger.creatures.VanillaCreatures
import com.easyforger.dungeons.VanillaDungeons

/**
 * Note: when reading the EasyForger code, please keep in mind that there are several overrides in place with
 * the sole purpose of improving auto completing method param names, since the decompiled minecraft ones are terrible.
 */
trait EasyForger extends VanillaItems with VanillaChests with VanillaDungeons with VanillaCreatures
