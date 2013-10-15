package pir.mods.peace;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class PeaceOre extends BlockOre{

	public PeaceOre(int par1) {
		super(par1);
		
		this.setHardness(3.0F); // Diamond Hardness
		this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName("peaceOre");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void registerIcons(IconRegister reg)
	{
		this.blockIcon = reg.registerIcon(PeaceInfo.ID + ":peace_ore");
	}
	
}

