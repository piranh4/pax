package pir.mods.peace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class PeaceBlock extends Block {

	public PeaceBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		
		this.setHardness(3.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName("peaceBlock");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void registerIcons(IconRegister reg)
	{
		this.blockIcon = reg.registerIcon(PeaceInfo.ID + ":peace_block");
	}

}
