package pir.mods.peace;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;

public class PeaceShovel extends ItemSpade {

	public PeaceShovel(int itemID, EnumToolMaterial toolMaterial) {
		super(itemID, toolMaterial);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName("peaceShovel");
		setTextureName(PeaceInfo.ID + ":peace_shovel");
	}

}
