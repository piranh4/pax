package pir.mods.peace;

import net.minecraft.block.BlockTorch;

public class OilLantern extends BlockTorch {

	public OilLantern(int par1) 
	{
		super(par1);
		setHardness(0.0F);
		setLightValue(0.95F);
		setStepSound(soundWoodFootstep);
		setUnlocalizedName(PeaceInfo.BLOCKS.OIL_LANTERN_NAME);
		setTextureName(PeaceInfo.ID + ":oil_lantern");	
	}

}
