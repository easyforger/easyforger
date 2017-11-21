/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.items

import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor.ArmorMaterial
import org.specs2.matcher.ThrownExpectations
import org.specs2.mutable.Specification

class EFItemArmorSpec extends Specification with ThrownExpectations {
  "a new EFItemArmor" should {
    "have the correct name" in {
      myItemArmorFeet.name must_=== "iron_boots"
    }

    "have the correct unlocalized name" in {
      myItemArmorFeet.getUnlocalizedName() must_=== "item.my-mod-id_iron_boots"
    }
  }

  val myItemArmorFeet = new EFItemArmor("my-mod-id", ArmorMaterial.IRON, EntityEquipmentSlot.FEET)
}
