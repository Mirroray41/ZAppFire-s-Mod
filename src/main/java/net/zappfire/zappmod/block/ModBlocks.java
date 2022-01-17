package net.zappfire.zappmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.zappfire.zappmod.Zappmod;
import net.zappfire.zappmod.block.custom.SoulsoilPreheater;
import net.zappfire.zappmod.item.ModItemGroup;

public class ModBlocks {

    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool()));

    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(5.0f).requiresTool()));

    public static final Block SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            new Block(FabricBlockSettings.of(Material.STONE ).strength(4.0f).requiresTool()));

    public static final Block ETHERITE_ORE = registerBlock("etherite_ore",
            new Block(FabricBlockSettings.of(Material.STONE ).strength(6.0f).requiresTool()));

    public static final Block RAW_ETHERITE_BLOCK = registerBlock("raw_etherite_block",
            new Block(FabricBlockSettings.of(Material.STONE ).strength(7.0f).requiresTool()));

    public static final Block ETHERITE_BLOCK = registerBlock("etherite_block",
            new Block(FabricBlockSettings.of(Material.STONE ).strength(7.0f).requiresTool()));

    public static final Block SOULSOIL_PREHEATER = registerBlock("soulsoil_preheater",
            new SoulsoilPreheater(FabricBlockSettings.of(Material.STONE ).strength(3.0f).requiresTool().luminance(14).nonOpaque()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(Zappmod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(Zappmod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for" + Zappmod.MOD_ID);

    }

}
