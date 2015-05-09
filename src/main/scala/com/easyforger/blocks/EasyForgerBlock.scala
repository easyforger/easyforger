package com.easyforger.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material

class EasyForgerBlock(val modId: String, val name: String, material: Material)
  extends Block(material) with BlockCommon
