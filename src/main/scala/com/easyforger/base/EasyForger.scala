/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.base

import com.easyforger.chests.VanillaChests
import com.easyforger.creatures.VanillaCreatures
import com.easyforger.dungeons.VanillaDungeons
import com.easyforger.recipes.RecipeOps

// TODO: this trait should be only mixed in in classes annotated with @mod -> https://github.com/easyforger/easyforger/issues/71
trait EasyForger
  extends VanillaItems
  with VanillaDungeons
  with VanillaCreatures
  with VanillaChests
  with RecipeOps {

  def modId: String

  implicit val self: EasyForger = this
}
