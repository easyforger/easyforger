package com.easyforger

package object creatures {
  def setIntField(clazz: Class[_], obj: AnyRef, fieldName: String, value: Int) = {
    val field = clazz.getDeclaredField(fieldName)
    field.setAccessible(true)
    field.setInt(obj, value)
    field.setAccessible(false)
  }
}
