/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger

package object creatures {
  def setIntField(clazz: Class[_], obj: AnyRef, fieldName: String, value: Int) = {
    val field = clazz.getDeclaredField(fieldName)
    field.setAccessible(true)
    field.setInt(obj, value)
    field.setAccessible(false)
  }
}
