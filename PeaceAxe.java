package pir.mods.peace;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;

public class PeaceAxe extends ItemAxe {

	public PeaceAxe(int itemID, EnumToolMaterial toolMaterial) {
		super(itemID, toolMaterial);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName("peaceAxe");
		setTextureName(PeaceInfo.ID + ":peace_axe");
	}

}
