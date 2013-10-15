package pir.mods.peace;
/*
 * minecraft mod to make peaceful mode more complex; adding hunger and making all items obtainable without killing mobs
 * 
 * credits:  mojang, the forge API developers, and countless people whose tutorials have taught me, and whose
 * code i am using.
 * 
 * (and i'll name them once this is finished)
 * 
 */
import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=PeaceInfo.ID, name=PeaceInfo.NAME, version=PeaceInfo.VERSION)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class Peace {
	
	// block IDs
	int peaceBlockID;
	int peaceOreID;
	int peaceIngotID;
	
	// armor IDs
	int peaceHelmetID;
	int peaceChestID;
	int peaceLeggingsID;
	int peaceBootsID;

	// tool IDs
	int peaceAxeID;
	int peaceShovelID;
	int peacePickaxeID;
	int peaceHoeID;
	int peaceSwordID;
	
	// blocks
	public static Block peaceBlock;
	public static Block peaceOre;
	public static Block flaxCrop = new FlaxCrop(504);
	
	// tools
	public static Item peaceAxe;
	public static Item peaceShovel;
	public static Item peacePickaxe;
	public static Item peaceHoe;
	public static Item peaceSword;
	
	// items
	public static Item peaceIngot;
	public static ItemSeeds flaxSeeds = (ItemSeeds) new ItemSeeds(5002, flaxCrop.blockID, Block.tilledField.blockID)
		.setUnlocalizedName("flaxSeeds")    
		.setTextureName(PeaceInfo.ID + ":seeds_flax");
    public static Item flaxStraw = new GenericItem(5003);    
	public static Item paintRed = new GenericItem(5004)
	.setMaxStackSize(1)
	.setCreativeTab(CreativeTabs.tabMisc)
	.setUnlocalizedName("paintRed")
	.setTextureName(PeaceInfo.ID.toLowerCase() + ":bucket_paint_red");

	// armor
	public static Item peaceHelmet;
	public static Item peaceChest;
	public static Item peaceLeggings;
	public static Item peaceBoots;
	
	// materials
	// diamond has 33 durability and 10 enchantibility, frex 
	public static EnumArmorMaterial peaceArmor = 
			EnumHelper.addArmorMaterial(PeaceInfo.ARMOR.PEACE_MATERIAL, 
					21, 
					new int[] { 4, 10, 4, 3 }, 
					5);	
	
	
    // The instance of your mod that Forge uses.
    @Instance(PeaceInfo.ID)
    public static Peace instance;
   
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide=PeaceInfo.CLIENTPROXY + "ClientProxy", serverSide=PeaceInfo.COMMONPROXY + "CommonProxy")
    public static CommonProxy proxy;
   
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    		this.SetupConfiguration(event.getSuggestedConfigurationFile());
    		this.InitializeAssets();
    		this.SetupLanguageRegistry();
    		this.MinecraftForgeSetup();
    		this.RegisterBlocks();
    		this.RegisterRecipes();	    
    }
    
    // handles hunger and natural regeneration
    //
    // warning!  if some other mod acts on those, things may not work!
    //
	EventManager manager = new EventManager();

	@EventHandler
	public void load(FMLInitializationEvent event) 
	{
		MinecraftForge.EVENT_BUS.register(manager);
	
        //
        // flax
        //
        MinecraftForge.addGrassSeed(new ItemStack(flaxSeeds), 10);

        LanguageRegistry.addName(flaxStraw, "Flax");
        GameRegistry.addShapelessRecipe(new ItemStack(flaxSeeds, 4),
                new ItemStack(flaxStraw));

        GameRegistry.registerBlock(flaxCrop, "flaxCrop");
    }
            
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    }
    
	private void SetupConfiguration(File configFile)
	{
		// configuration code
		Configuration config = new Configuration(configFile);
		
		config.load();		
	
		peaceBlockID = config.get("Block IDs", "Peace Block ID", 1000).getInt();
		peaceOreID = config.get("Block IDs", "Peace Ore ID", 1001).getInt();
		peaceIngotID = config.get("Item IDs", "Peace Ingot ID", 1002).getInt();

		peaceAxeID = config.get("Item IDs", "Peace Axe ID", 1003).getInt();
		peaceShovelID = config.get("Item IDs", "Peace Shovel ID", 1004).getInt();
		peacePickaxeID = config.get("Item IDs", "Peace Pickaxe ID", 1005).getInt();
		peaceHoeID = config.get("Item IDs", "Peace Hoe ID", 1006).getInt();
		peaceSwordID = config.get("Item IDs", "Peace Sword ID", 1007).getInt();
	
		peaceHelmetID = config.get("Armor IDs", "Peace Helmet ID", 1008).getInt();
		peaceChestID = config.get("Armor IDs", "Peace Chest ID", 1009).getInt();
		peaceLeggingsID = config.get("Armor IDs", "Peace Leggings ID", 1010).getInt();
		peaceBootsID = config.get("Armor IDs", "Peace Boots ID", 1011).getInt();
		
		config.save();
	}
	private void InitializeAssets()
	{
		// initialize our blocks and item stacks
		this.peaceBlock = new PeaceBlock(peaceBlockID, Material.rock);
		this.peaceOre = new PeaceOre(peaceOreID);
		
		// initialize our items
		this.peaceIngot = new PeaceIngot(peaceIngotID);
//		this.peaceAxe = new PeaceAxe(peaceAxeID);
//		this.peaceShovel = new PeaceShovel(peaceShovelID);
//		this.peacePickaxe = new PeacePickaxe(peacePickaxeID);
//		this.peaceHoe = new PeaceHoe(peaceHoeID);
//		this.peaceSword = new PeaceSword(peaceSwordID);
		
		// Initialize our armor
		peaceHelmet = new Armor(peaceHelmetID, peaceArmor, 0, 0, PeaceInfo.ARMOR.PEACE_TYPE);
		peaceChest = new Armor(peaceChestID, peaceArmor, 0, 1, PeaceInfo.ARMOR.PEACE_TYPE);
		peaceLeggings = new Armor(peaceLeggingsID, peaceArmor, 0, 2, PeaceInfo.ARMOR.PEACE_TYPE);
		peaceBoots = new Armor(peaceBootsID, peaceArmor, 0, 3, PeaceInfo.ARMOR.PEACE_TYPE);
	}
	
	private void SetupLanguageRegistry()
	{
		LanguageRegistry.addName(peaceBlock, PeaceInfo.BLOCKS.PEACE_BLOCK);
		LanguageRegistry.addName(peaceOre, PeaceInfo.BLOCKS.PEACE_ORE_BLOCK);
		LanguageRegistry.addName(peaceIngot, PeaceInfo.ITEMS.PEACE_INGOT);
		LanguageRegistry.addName(peaceHelmet, PeaceInfo.ARMOR.PEACE_HELMET);
		LanguageRegistry.addName(peaceChest, PeaceInfo.ARMOR.PEACE_CHEST);
		LanguageRegistry.addName(peaceLeggings, PeaceInfo.ARMOR.PEACE_LEGGINGS);
		LanguageRegistry.addName(peaceBoots, PeaceInfo.ARMOR.PEACE_BOOTS);		
        LanguageRegistry.addName(flaxSeeds, "Flax Seeds");
}
	
	private void MinecraftForgeSetup()
	{
		MinecraftForge.setBlockHarvestLevel(peaceBlock, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(peaceOre, "pickaxe", 3);
	}
	
	private void RegisterBlocks()
	{
		GameRegistry.registerBlock(peaceBlock, PeaceInfo.BLOCKS.PEACE_BLOCK_NAME);
   	    GameRegistry.registerBlock(peaceOre, PeaceInfo.BLOCKS.PEACE_ORE_BLOCK_NAME);
	}
	
	private void RegisterRecipes()
	{
        // char white wool into black
        GameRegistry.addSmelting(new ItemStack(Block.cloth, 2, 0).itemID, new ItemStack(Block.cloth, 1, 15), 0.2f);            //
        // make paint from milk and dye
        GameRegistry.addShapelessRecipe(new ItemStack(paintRed, 1, 15), new ItemStack(Item.bucketMilk), new ItemStack(Item.dyePowder, 1, 1));
        // melt bones into glue
        GameRegistry.addSmelting(new ItemStack(Item.bone).itemID, new ItemStack(Item.slimeBall), 0.1f);
        
        // peace ingot recipes
		GameRegistry.addSmelting(peaceOreID, new ItemStack(peaceIngot), 0.7f);
	
		GameRegistry.addRecipe(new ItemStack(peacePickaxe), 
				"xxx", " y ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick)
				);
		GameRegistry.addRecipe(new ItemStack(peaceAxe), 
				"xx ", "xy ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick)
				);
		GameRegistry.addRecipe(new ItemStack(peaceShovel), 
				" x ", " y ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick)
				);
		GameRegistry.addRecipe(new ItemStack(peaceHoe), 
				"xx ", " y ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick)
				);
		GameRegistry.addRecipe(new ItemStack(peaceSword), 
				" x ", " x ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick)
				);
		
		GameRegistry.addRecipe(new ItemStack(peaceBlock), 
				"xxx", "xxx", "xxx",
				'x', peaceIngot
				);
		
		GameRegistry.addRecipe(new ItemStack(peaceHelmet), 
				"xxx", "x x",
				'x', peaceIngot
				);
		
		GameRegistry.addRecipe(new ItemStack(peaceChest), 
				"x x", "xxx", "xxx",
				'x', peaceIngot
				);
		
		GameRegistry.addRecipe(new ItemStack(peaceLeggings), 
				"xxx", "x x", "x x",
				'x', peaceIngot
				);
		
		GameRegistry.addRecipe(new ItemStack(peaceBoots), 
				"   ", "x x", "x x",
				'x', peaceIngot
				);
	}


}
