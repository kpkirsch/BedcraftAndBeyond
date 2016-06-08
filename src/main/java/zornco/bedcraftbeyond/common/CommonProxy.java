package zornco.bedcraftbeyond.common;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zornco.bedcraftbeyond.common.blocks.BcbBlocks;
import zornco.bedcraftbeyond.common.blocks.tiles.TileWoodenBed;
import zornco.bedcraftbeyond.util.PlankHelper;

public abstract class CommonProxy {

    public void registerModels() {
    }

    public void init() {
        BcbBlocks.registerTiles();
    }

    public abstract void compileFrames();
}
