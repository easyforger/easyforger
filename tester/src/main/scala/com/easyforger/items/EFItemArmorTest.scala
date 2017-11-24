/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.items

import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor.ArmorMaterial
import utest._ // scalastyle:ignore

object EFItemArmorTest {
  val tests = Tests {
    "a new EFItemArmor" - {
      val myItemArmorFeet = new EFItemArmor("my-mod-id", ArmorMaterial.IRON, EntityEquipmentSlot.FEET)

      "have the correct name" - {
        assert(myItemArmorFeet.name == "iron_boots")
      }

      "have the correct unlocalized name" - {
        assert(myItemArmorFeet.getUnlocalizedName() == "item.my-mod-id_iron_boots")
      }
    }
  }
}
