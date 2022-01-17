package net.zappfire.zappmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.zappfire.zappmod.Zappmod;

public class ModItemGroup {

    public static final ItemGroup ZAPPMOD = FabricItemGroupBuilder.build(new Identifier(Zappmod.MOD_ID, "zappmod"),
            () -> new ItemStack(ModItems.SAPPHIRE));
}
