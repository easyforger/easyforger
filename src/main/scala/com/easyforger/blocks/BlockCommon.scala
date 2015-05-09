package com.easyforger.blocks

import java.util.Random

import com.easyforger.base.EasyForger
import net.minecraft.block.Block
import net.minecraft.block.Block.SoundType
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

trait BlockCommon extends Block with EasyForger {
  val modId: String
  val name: String

  val defaultMetadata = 0

  setUnlocalizedName(s"${modId}_$name")
  setCreativeTab(CreativeTabs.tabBlock)

  var dropItem: Option[Item] = None
  override def getItemDropped(state: IBlockState, random: Random, fortune: Int): Item =
    dropItem.getOrElse(super.getItemDropped(state, random, fortune))

  var quantityDropped: Option[Int] = None
  override def quantityDropped(random : Random): Int =
    quantityDropped.getOrElse(super.quantityDropped(random))

  override def setHardness(hardness: Float): Block = super.setHardness(hardness)
  override def setResistance(resistance: Float): Block = super.setResistance(resistance)
  override def setStepSound(soundType: SoundType): Block = super.setStepSound(soundType)


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
