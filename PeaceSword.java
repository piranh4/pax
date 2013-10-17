package pir.mods.peace;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class PeaceSword extends ItemSword {

	public PeaceSword(int itemID, EnumToolMaterial toolMaterial) {
		super(itemID, toolMaterial);

		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabCombat);
		setUnlocalizedName("peaceSword");
		setTextureName(PeaceInfo.ID + ":peace_sword");
	}
	
}
