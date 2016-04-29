package zornco.bedcraftbeyond.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import zornco.bedcraftbeyond.util.PlankHelper;

public class CommonProxy implements IGuiHandler {

	public void registerRenderInformationInit () {
		// Nothing here as this is the server side proxy
	}

	public void registerModels() { }

	public void init() { }

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int X, int Y, int Z)
	{
		TileEntity te = world.getTileEntity(new BlockPos(X, Y, Z));
		/*if (te != null && te instanceof TileBedcraftChestBed)
		{
			TileBedcraftChestBed teccb = (TileBedcraftChestBed) te;
			// return new ContainerColoredChestBed(player.inventory, teccb);
		}
		else
		{
			return null;
		} */
		return null;
	}

	public World getClientWorld()
	{
		return null;
	}

	public int getAverageBlockColour(ItemStack stack2) {
		return -1;
	}
	public void compilePlanks() {
		PlankHelper.compilePlanks();
		PlankHelper.compilePlanksClient();
		/*BedCraftBeyond.logger.info("SPAM TIME!");
		for (Object block : Block.blockRegistry.getKeys()) {
			String blockname = block.toString();
			BedCraftBeyond.logger.info(blockname);
		}*/
	}
}
