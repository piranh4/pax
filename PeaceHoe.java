package pir.mods.peace;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

public class PeaceHoe extends ItemHoe {

	public PeaceHoe(int itemID, EnumToolMaterial toolMaterial) {
		super(itemID, toolMaterial);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName("peaceHoe");
		setTextureName(PeaceInfo.ID + ":peace_hoe");

	}

}
