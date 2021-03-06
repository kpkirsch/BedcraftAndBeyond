package zornco.bedcraftbeyond.frames.stone;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import zornco.bedcraftbeyond.frames.registry.FrameRegistry;
import zornco.bedcraftbeyond.core.ModContent;
import zornco.bedcraftbeyond.core.util.items.ItemHandlerGridHelper;
import zornco.bedcraftbeyond.core.util.items.ItemHelper;

import javax.annotation.Nullable;
import java.awt.*;

public class RecipeBedFrameStone implements IRecipe {

    @Nullable
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        InvWrapper invWrapped = new InvWrapper(inv);
        for(int y = 3; y < 9; y++) invWrapped.extractItem(y, 1, false);

        return new ItemStack(ModContent.Items.stoneBed, 1);
    }

    @Override
    public boolean matches(InventoryCrafting inv, World w) {
        if(inv.getWidth() < 3 || inv.getHeight() < 3) return false;
        InvWrapper wrapped = new InvWrapper(inv);
        IItemHandler stoneTest, slabTest;
        try {
	        slabTest = ItemHandlerGridHelper.getItemsWrapped(wrapped, new Dimension(3,3), new Rectangle(0,1, 3,1));
	        stoneTest = ItemHandlerGridHelper.getItemsWrapped(wrapped, new Dimension(3,3), new Rectangle(0,2, 3,1));
        }

        catch (IndexOutOfBoundsException iob){
            return false;
        }

        if(slabTest.getSlots() < 3 || stoneTest.getSlots() < 3) return false;
        if(!ItemHelper.areItemStacksEqual(slabTest)) return false;
        if(!ItemHelper.areItemStacksEqual(stoneTest)) return false;

        if(!FrameRegistry.isValidFrameMaterial(FrameRegistry.EnumFrameType.STONE, stoneTest.getStackInSlot(0)))
            return false;

        return true;
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Nullable
    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(ModContent.Items.stoneBed, 1);
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        return new ItemStack[0];
    }
}
