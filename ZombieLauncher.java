package pir.mods.peace;

	import net.minecraft.creativetab.CreativeTabs;
	import net.minecraft.entity.EntityLivingBase;
	import net.minecraft.entity.monster.EntityZombie;
	import net.minecraft.entity.player.EntityPlayer;
	import net.minecraft.item.Item;
	import net.minecraft.item.ItemStack;
	
	public class ZombieLauncher extends GenericItem {
		
		public ZombieLauncher(int id) {
			super(id);
			setMaxStackSize(64);
			setCreativeTab(CreativeTabs.tabMisc);
			setUnlocalizedName("zombieLauncher");
			setTextureName(PeaceInfo.ID.toLowerCase() + ":zombielauncher");
		}
		@Override
		public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase target) {
			if(!target.worldObj.isRemote) {
				if(target instanceof EntityZombie) {
					target.motionY = 2.5;
				} else {
					player.addChatMessage("This item only works on Zombies!");
				}
			}
			return false;
		}
	}