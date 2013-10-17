package pir.mods.peace;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class FlaxCrop extends BlockCrops {

    private Icon[] iconArray;

	public FlaxCrop (int itemId) {
    	super(itemId);
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs)null);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.disableStats();
    }

	//
    // From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
    //
    public Icon getIcon(int side, int metadata)
    {
        if (metadata < 0 || metadata > 7)
        {
            metadata = 7;
        }

        return this.iconArray[metadata];
    }
 
	//
    // return the seed ID that the block drops when harvested
    //
	@Override
	protected int getSeedItem()
    {
        return Peace.flaxSeeds.itemID;
    }

    //
	// return the crop ID that the block drops when harvested
	//
	@Override
	protected int getCropItem()
    {
        return Peace.flaxStraw.itemID;
    }

	
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[8];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(PeaceInfo.ID + ":flax_stage_" + i);
        }
    }

}
