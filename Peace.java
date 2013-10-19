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
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
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
	int flaxCropID;
	
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
	
	// item IDs
	static int flaxSeedID;
	static int flaxFibreID;
	static int flaxBreadID;
	static int linseedOilID;
	static int paintRedID;
	
	// blocks
	public static Block peaceBlock;
	public static Block peaceOre;
	public static Block flaxCrop;
	
	// items
	public static Item peaceIngot;
	public static Item flaxSeeds;
    public static Item flaxFibre;
    public static Item flaxBread;

    public static Item linseedOil;
	public static Item paintRed;

	// armor
	public static Item peaceHelmet;
	public static Item peaceChest;
	public static Item peaceLeggings;
	public static Item peaceBoots;
	
	public static EnumArmorMaterial peaceArmor = 
			EnumHelper.addArmorMaterial(PeaceInfo.ARMOR.PEACE_MATERIAL, 21,	new int[] { 4, 10, 4, 3 }, 5);	
	
	// tools
	public static Item peaceAxe;
	public static Item peaceShovel;
	public static Item peacePickaxe;
	public static Item peaceHoe;
	public static Item peaceSword;
	
	/**
	 * material parameters:
	 * 1. int - level of material the tool can harvest
	 * 2. int - number of uses before tool breaks
	 * 3. float - strength of this material against blocks for which it is effective 
	 * 4. float - damage versus entities
	 * 5. int - enchantability factor
	 * 
	 * references:
	 * 
     * WOOD(0, 59, 2.0F, 0.0F, 15),
     * STONE(1, 131, 4.0F, 1.0F, 5),
     * IRON(2, 250, 6.0F, 2.0F, 14),
     * DIAMOND(3, 1561, 8.0F, 3.0F, 10),
     * GOLD(0, 32, 12.0F, 0.0F, 22);
    */	
	public static EnumToolMaterial peaceTools = 
			EnumHelper.addToolMaterial(PeaceInfo.TOOLS.PEACE_TYPE, 3, 3, 15F, 2, 22);
    
	// this mod's instance for forge
	@Instance(PeaceInfo.ID)
    public static Peace instance;
   
	// telling forge what main classes to use to access functions on the client or server side
    @SidedProxy(clientSide=PeaceInfo.CLIENTPROXY + "ClientProxy", serverSide=PeaceInfo.COMMONPROXY + "CommonProxy")
    public static CommonProxy proxy;
   
    // before we do anything else, initialize stuff
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
	// EventManager manager = new EventManager();

	@EventHandler
	public void load(FMLInitializationEvent event) 
	{
	//	MinecraftForge.EVENT_BUS.register(manager);

		//
		// peace ore generation
		//
		//  GameRegistry.registerWorldGenerator(new WorldGeneratorPeace());
     }
            
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    }
    
	private void SetupConfiguration(File configFile)
	{
		/*
		 *  we keep these values in a file because forge will not retain the same
		 *  item IDs as we define, since it needs to keep all mods compatible and
		 *  avoid potential conflicts.  we need therefore to keep track of them
		 *  ourselves.
		 */
		Configuration config = new Configuration(configFile);
		
		try
		{
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
			
			flaxSeedID = config.get("Item IDs", "Flax Seed ID", 1012).getInt();
			flaxFibreID = config.get("Item IDs", "Flax Fibre ID", 1013).getInt();
			flaxBreadID = config.get("Item IDs", "Flax Bread ID", 1014).getInt();
			flaxCropID = config.get("Item IDs", "Flax Crop ID", 1015).getInt();
			linseedOilID = config.get("Item IDs", "Linseed Oil ID", 1016).getInt();
			paintRedID = config.get("Item IDs", "Red Paint ID", 1017).getInt();

		}
		catch(Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Mod " + PeaceInfo.NAME + "has a problem loading its config file");
		}
		finally 
		{
			config.save();
		}
	}

	private void InitializeAssets()
	{
		// initialize blocks and item stacks
		this.peaceBlock = new PeaceBlock(peaceBlockID, Material.rock);
		this.peaceOre = new PeaceOre(peaceOreID);
		this.flaxCrop = new FlaxCrop(flaxCropID);
		this.paintRed = new Item(paintRedID)
			.setMaxStackSize(1)
			.setCreativeTab(CreativeTabs.tabMisc)
			.setUnlocalizedName("paintRed")
			.setTextureName(PeaceInfo.ID + ":bucket_paint_red");
		
		// initialize items
		this.peaceIngot = new PeaceIngot(peaceIngotID);
		this.flaxSeeds = new ItemSeeds(flaxSeedID, flaxCropID, Block.tilledField.blockID)
			.setUnlocalizedName("flaxSeeds")    
			.setTextureName(PeaceInfo.ID + ":seeds_flax");
    	this.flaxFibre = new Item(flaxFibreID)    
			.setUnlocalizedName("flaxFibre")    
			.setTextureName(PeaceInfo.ID + ":flax_fibre");
    	this.linseedOil = new Item(linseedOilID)
			.setUnlocalizedName("linseedOil")    
			.setTextureName(PeaceInfo.ID + ":linseed_oil");
    	// ItemFood params:  item use duration, heal amount, saturation modifier (???), is this wolf's favourite food
    	this.flaxBread = (new ItemFood(41, 7, 0.6F, false))
			.setUnlocalizedName("flaxBread")    
			.setTextureName(PeaceInfo.ID + ":flax_bread");

		// initialize tools
		this.peaceAxe = new PeaceAxe(peaceAxeID, peaceTools);
		this.peaceShovel = new PeaceShovel(peaceShovelID, peaceTools);
		this.peacePickaxe = new PeacePickaxe(peacePickaxeID, peaceTools);
		this.peaceHoe = new PeaceHoe(peaceHoeID, peaceTools);
		this.peaceSword = new PeaceSword(peaceSwordID, peaceTools);
		
		// initialize our armor
		peaceHelmet = new PeaceArmor(peaceHelmetID, peaceArmor, 0, 0, PeaceInfo.ARMOR.PEACE_TYPE);
		peaceChest = new PeaceArmor(peaceChestID, peaceArmor, 0, 1, PeaceInfo.ARMOR.PEACE_TYPE);
		peaceLeggings = new PeaceArmor(peaceLeggingsID, peaceArmor, 0, 2, PeaceInfo.ARMOR.PEACE_TYPE);
		peaceBoots = new PeaceArmor(peaceBootsID, peaceArmor, 0, 3, PeaceInfo.ARMOR.PEACE_TYPE);
		
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
		LanguageRegistry.addName(peaceAxe, PeaceInfo.TOOLS.PEACE_AXE);
		LanguageRegistry.addName(peaceShovel, PeaceInfo.TOOLS.PEACE_SHOVEL);
		LanguageRegistry.addName(peacePickaxe, PeaceInfo.TOOLS.PEACE_PICKAXE);
		LanguageRegistry.addName(peaceHoe, PeaceInfo.TOOLS.PEACE_HOE);
		LanguageRegistry.addName(peaceSword, PeaceInfo.TOOLS.PEACE_SWORD);
        LanguageRegistry.addName(flaxSeeds, "Flax Seeds");
        LanguageRegistry.addName(flaxFibre, "Flax Fibre");
        LanguageRegistry.addName(flaxBread, "Flax Bread");
        LanguageRegistry.addName(linseedOil, "Linseed Oil");
        LanguageRegistry.addName(paintRed, "Red Paint");
	}
	
	private void MinecraftForgeSetup()
	{
		MinecraftForge.setBlockHarvestLevel(peaceBlock, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(peaceOre, "pickaxe", 3);
        MinecraftForge.addGrassSeed(new ItemStack(flaxSeeds), 10);
}
	
	private void RegisterBlocks()
	{
		GameRegistry.registerBlock(peaceBlock, PeaceInfo.BLOCKS.PEACE_BLOCK_NAME);
   	    GameRegistry.registerBlock(peaceOre, PeaceInfo.BLOCKS.PEACE_ORE_BLOCK_NAME);
        GameRegistry.registerBlock(flaxCrop, PeaceInfo.BLOCKS.FLAX_CROP_NAME);
	}
	
	private void RegisterRecipes()
	{
        // char white wool into black
        GameRegistry.addSmelting(new ItemStack(Block.cloth, 2, 0).itemID, new ItemStack(Block.cloth, 1, 15), 0.2f);            //
        // melt bones into glue
        GameRegistry.addSmelting(new ItemStack(Item.bone).itemID, new ItemStack(Item.slimeBall), 0.1f);
        
        // peace ingot recipes
		GameRegistry.addSmelting(peaceOreID, new ItemStack(peaceIngot), 0.7f);
		GameRegistry.addRecipe(new ItemStack(peacePickaxe), 
				"xxx", " y ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick) );
		GameRegistry.addRecipe(new ItemStack(peaceAxe), 
				"xx ", "xy ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick) );
		GameRegistry.addRecipe(new ItemStack(peaceShovel), 
				" x ", " y ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick) );
		GameRegistry.addRecipe(new ItemStack(peaceHoe), 
				"xx ", " y ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick) );
		GameRegistry.addRecipe(new ItemStack(peaceSword), 
				" x ", " x ", " y ",
				'x', peaceIngot,
				'y', new ItemStack(Item.stick) );
		GameRegistry.addRecipe(new ItemStack(peaceBlock), 
				"xxx", "xxx", "xxx",
				'x', peaceIngot );
		// make special peace armor pieces
		GameRegistry.addRecipe(new ItemStack(peaceHelmet), 
				"xxx", "x x",
				'x', peaceIngot );
		GameRegistry.addRecipe(new ItemStack(peaceChest), 
				"x x", "xxx", "xxx",
				'x', peaceIngot );
		GameRegistry.addRecipe(new ItemStack(peaceLeggings), 
				"xxx", "x x", "x x",
				'x', peaceIngot );
		GameRegistry.addRecipe(new ItemStack(peaceBoots), 
				"   ", "x x", "x x",
				'x', peaceIngot );
		// flax seeds get pressed into linseed oil
		GameRegistry.addRecipe(new ItemStack(linseedOil), 
				"xx", "xx",
				'x', flaxSeeds );
        // make paint from milk, linseed oil, and dye
	    GameRegistry.addShapelessRecipe(new ItemStack(paintRed, 1, 15), 
	    		new ItemStack(Item.bucketMilk),
	    		new ItemStack(linseedOil),
	    		new ItemStack(Item.dyePowder, 1, 1));
		// a heartier bread (restores more than wheat bread)
	    GameRegistry.addRecipe(new ItemStack(flaxBread), 
				"   ", "   ", "xyx",
				'x', new ItemStack(Item.wheat),
				'y', flaxSeeds );

	}

}

