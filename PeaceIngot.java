package pir.mods.peace;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PeaceIngot extends Item{

	public PeaceIngot(int par1) {
		super(par1);

		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("peaceIngot");		
	}
	
	@Override
	public void registerIcons(IconRegister reg)
	{
		this.itemIcon = reg.registerIcon(PeaceInfo.ID + ":peace_ingot");
	}

}