package pir.mods.peace;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class PeacePickaxe extends ItemPickaxe {

	public PeacePickaxe(int itemID, EnumToolMaterial toolMaterial) {
		super(itemID, toolMaterial);

		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName("peacePickaxe");
		setTextureName(PeaceInfo.ID + ":peace_pickaxe");
	}

}
