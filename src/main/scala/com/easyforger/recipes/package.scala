/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger

import net.minecraft.item.ItemStack

package object recipes {
  case class Recipe(source: RecipeItemStack, result: RecipeItemStack)

  case class RecipeItemStack(itemStack: ItemStack, acronym: Char) {
    def apply(stackSize: Int): RecipeItemStack = this.copy(itemStack = new ItemStack(itemStack.getItem, stackSize))
    def apply(acr: Char): RecipeItemStack = this.copy(acronym = acr)

    def to(result: RecipeItemStack): Recipe = Recipe(this, result)
  }
}
