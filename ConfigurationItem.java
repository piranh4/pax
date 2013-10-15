package pir.mods.peace;

public class ConfigurationItem {
	public static String Section = "";
	public static String Item = "";
	public static int DefaultId = 0;
	
	public ConfigurationItem(String section, String item, int defaultId)
	{
		this.Section = section;
		this.Item = item;
		this.DefaultId = defaultId;
	}
}
