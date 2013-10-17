package pir.mods.peace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class PeaceArmor extends ItemArmor{

	private String texturePath = PeaceInfo.ID + ":";
	private String iconPath = PeaceInfo.ID + ":";

	/**
	 * material parameters:
	 * 1. int - maximum damage factor before armor piece breaks
	 * 2. int[] - damage reduction array (each 1 point is half a shield on GUI) of each piece of armor
	 * 3. int - enchantability factor of the material 
	 * 
	 * references:
	 * 
     * CLOTH(5, new int[]{1, 3, 2, 1}, 15),
     * CHAIN(15, new int[]{2, 5, 4, 1}, 12),
     * IRON(15, new int[]{2, 6, 5, 2}, 9),
     * GOLD(7, new int[]{2, 5, 3, 1}, 25),
     * DIAMOND(33, new int[]{3, 8, 6, 3}, 10);
    */	
	public PeaceArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3,
			int par4, String type) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.SetArmorType(type.toLowerCase(), par4);
	}
	
	// 0 = helmet
	// 1 = chestplate
	// 2 = leggings
	// 3 = boots
	private void SetArmorType(String type, int par4)
	{
		switch(par4)
		{
		case 0:
			this.setUnlocalizedName(type + "Helmet");
			this.texturePath += type + "_layer_1.png";
			this.iconPath +=type + "_helmet";
			break;
		case 1:
			this.setUnlocalizedName(type + "Chest");
			this.texturePath += type + "_layer_1.png";
			this.iconPath +=type + "_chest";
			break;
		case 2:
			this.setUnlocalizedName(type + "Leggings");
			this.texturePath += type + "_layer_2.png";
			this.iconPath +=type + "_leggings";
			break;
		case 3:
			this.setUnlocalizedName(type + "Boots");
			this.texturePath += type + "_layer_1.png";
			this.iconPath +=type + "_boots";
			break;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg)
	{
		this.itemIcon = reg.registerIcon(this.iconPath);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
	{
		return this.texturePath;
	}
	

}
