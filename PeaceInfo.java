package pir.mods.peace;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class PeaceInfo {

		public static final String ID = "peace";
		public static final String NAME = "Peaceful Hunger";
		public static final String VERSION = "0.1";
		public static final String CLIENTPROXY = "pir.mods.peace.client.";
		public static final String COMMONPROXY = "pir.mods.peace.";

		public static class ARMOR {
			public static final String PEACE_TYPE = "Peace";
			public static final String PEACE_MATERIAL = PEACE_TYPE + "Armor";
			
			public static final String PEACE_HELMET = PEACE_TYPE + " Helmet";
			public static final String PEACE_CHEST = PEACE_TYPE + " Chest";
			public static final String PEACE_LEGGINGS = PEACE_TYPE + " Leggings";
			public static final String PEACE_BOOTS = PEACE_TYPE + " Boots";
		}
			
		public static class BLOCKS {
			public static final String PEACE_BLOCK = "Peace Block";
			public static final String PEACE_BLOCK_NAME = "peaceBlock";
			public static final String PEACE_ORE_BLOCK = "Peace Ore";	
			public static final String PEACE_ORE_BLOCK_NAME = "peaceOre";		
		}
		
		public static class ITEMS {
			public static final String PEACE_INGOT = "Peace Ingot";
			public static final String PEACE_INGOT_NAME = "peaceIngot";		
		}

}