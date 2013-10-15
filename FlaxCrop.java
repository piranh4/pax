package pir.mods.peace;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class FlaxCrop extends BlockCrops {

    private Icon[] iconArray;

	public FlaxCrop (int id) {
 //        super(id, 32, Material.plants);
    	super(id);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x,
            int y, int z) {
        return null;
    }

    @Override
    public int getRenderType () {
        return 6;
    }

    @Override
    public boolean isOpaqueCube () {
        return false;
    }

//	this used to get the correct texture from a sprite. textures have changed, how is it done now?
//    @Override
//    public int getBlockTextureFromSideAndMetadata (int side, int metadata) {
//        return 32 + metadata;
//    }

    @Override
    public void updateTick (World world, int x, int y, int z, Random random) {
        if (world.getBlockMetadata(x, y, z) == 1) {
            return;
        }

        if (random.nextInt(isFertile(world, x, y - 1, z) ? 12 : 25) != 0) {
            return;
        }

//        world.setBlockMetadataWithNotify(x, y, z, 1); // there is a new final par, a "flag".  for what?
        world.setBlockMetadataWithNotify(x, y, z, 1, 0);
    }

    @Override
    public void onNeighborBlockChange (World world, int x, int y, int z,
            int neighborId) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockMetadataWithNotify(x, y, z, 0, 0);
        }
    }

    @Override
    public boolean canBlockStay (World world, int x, int y, int z) {
        Block soil = blocksList[world.getBlockId(x, y - 1, z)];
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world
                .canBlockSeeTheSky(x, y, z))
                && (soil != null && soil.canSustainPlant(world, x, y - 1, z,
                        ForgeDirection.UP, Peace.flaxSeeds));
    }

    @Override
    public int idDropped (int metadata, Random random, int par2) {
        switch (metadata) {
        case 0:
            return Peace.flaxSeeds.itemID;
        case 1:
            return Peace.flaxStraw.itemID;
        default:
            // Error case!
            return -1; // air
        }
    }

    @Override
    public int idPicked (World world, int x, int y, int z) {
        return Peace.flaxSeeds.itemID;
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[8];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(PeaceInfo.ID.toLowerCase() + ":flax_stage_" + i);
        }
    }
}
