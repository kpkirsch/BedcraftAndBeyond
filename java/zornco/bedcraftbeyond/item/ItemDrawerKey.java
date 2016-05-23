package zornco.bedcraftbeyond.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zornco.bedcraftbeyond.BedCraftBeyond;

public class ItemDrawerKey extends Item implements IName {
	private final String name = "drawerkey";
	public ItemDrawerKey() {
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(BedCraftBeyond.MOD_ID + "_" + name);
		setCreativeTab(BedCraftBeyond.bedCraftBeyondTab);
		this.maxStackSize = 1;
	}
	public String getName()
	{
		return name;
	}
	public String toString()
	{
		return "item.tool";
	}
	/*@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon("bedcraftbeyond:drawerkey");
	}*/
}