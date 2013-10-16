package pir.mods.peace;

/**
 * not used at this time because it was simpler to do individual files for the tools
 * 
 * refactor all of that once you've got a handle on exactly how things work.
 * 
 */
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemTool;

public class PeaceTools extends ItemTool {

	/**
	 * parameters:
	 * 1. int - toolID
	 * 2. float - damage vs entities
	 * 3. enum - tool material 
	 * 4. block[] - array of blocks tool is effective against
	 * 
    */
	
	public PeaceTools(int par1, float par2,
			EnumToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock) 
	{
		super(par1, par2, par3EnumToolMaterial, par4ArrayOfBlock);

        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabTools);
		
	}

}
