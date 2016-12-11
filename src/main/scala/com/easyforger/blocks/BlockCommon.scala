/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.blocks

import java.util.Random

import com.easyforger.base.EasyForger
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

trait BlockCommon extends Block with EasyForger {
  val modId: String
  val name: String

  val defaultMetadata = 0

  setUnlocalizedName(s"${modId}_$name")
  setCreativeTab(CreativeTabs.BUILDING_BLOCKS)

  // ignoring the 'var' checks below because fixing them would take a redesign of the API - which will
  // be done a some point in the future

  var dropItem: Option[Item] = None // scalastyle:ignore
  override def getItemDropped(state: IBlockState, random: Random, fortune: Int): Item =
    dropItem.getOrElse(super.getItemDropped(state, random, fortune))

  var quantityDropped: Option[Int] = None // scalastyle:ignore
  override def quantityDropped(random : Random): Int =
    quantityDropped.getOrElse(super.quantityDropped(random))

  /**
   * Caution: this method must be called from inside the init() method of your mod!
   */
  def register(): Unit = {
    registerBlock()
    registerItemModel()
  }

  def registerBlock(): Unit = GameRegistry.registerBlock(this, name)

  /**
   * Caution: this method must be called from inside the init() method of your mod!
   */
  def registerItemModel(): Unit = {
    val itemBlock = GameRegistry.findItem(modId, name)
    val itemModelResourceLocation = new ModelResourceLocation(s"$modId:$name", "inventory")

    Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(itemBlock, defaultMetadata, itemModelResourceLocation)
  }
}
