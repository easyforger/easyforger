package com.easyforger.blocks

import java.util.Random

import com.easyforger.base.EasyForger
import net.minecraft.block.Block
import net.minecraft.block.Block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

class EasyForgerBlock(modId: String, name: String, material: Material) extends Block(material) with EasyForger {
  setBlockName(s"${modId}_$name")
  setBlockTextureName(s"$modId:$name")
  setCreativeTab(CreativeTabs.tabBlock)

  var dropItem: Option[Item] = None
  override def getItemDropped(meta: Int, random: Random, fortune: Int): Item = 
    dropItem.getOrElse(super.getItemDropped(meta, random, fortune))
  
  var quantityDropped: Option[Int] = None
  override def quantityDropped(random : Random): Int =
    quantityDropped.getOrElse(super.quantityDropped(random))

  override def setHardness(hardness: Float): Block = super.setHardness(hardness)
  override def setResistance(resistance: Float): Block = super.setResistance(resistance)
  override def setStepSound(soundType: SoundType): Block = super.setStepSound(soundType)
}
