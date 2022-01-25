package net.zappfire.zappmod.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.zappfire.zappmod.Zappmod;
import net.zappfire.zappmod.item.custom.Mirror;
import net.zappfire.zappmod.item.custom.ModAxe;
import net.zappfire.zappmod.item.custom.ModHoe;
import net.zappfire.zappmod.item.custom.ModPickaxe;

public class ModItems {

    public static final Item PRISMARINE_MIRROR = registerItem("prismarine_mirror",
            new Mirror(ModToolMaterial.PRISMARINE_MIRROR, 0.0f,1.0f,
                    new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).group(ModItemGroup.ZAPPMOD).maxDamage(5)));

    public static final Item SAPPHIRE = registerItem("sapphire",
            new Item(new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));

    public static final Item SAPPHIRE_SWORD = registerItem("sapphire_sword",
            new SwordItem(ModToolMaterial.SAPPHIRE, 3, -2.4f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item SAPPHIRE_SHOVEL = registerItem("sapphire_shovel",
            new ShovelItem(ModToolMaterial.SAPPHIRE, -1, -3f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));

    public static final Item SAPPHIRE_AXE = registerItem("sapphire_axe",
            new ModAxe(ModToolMaterial.SAPPHIRE, 4, -3.3f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item SAPPHIRE_PICKAXE = registerItem("sapphire_pickaxe",
            new ModPickaxe(ModToolMaterial.SAPPHIRE, -1, -2.9f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item SAPPHIRE_HOE = registerItem("sapphire_hoe",
            new ModHoe(ModToolMaterial.SAPPHIRE, -2, 0f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));

    public static final Item SAPPHIRE_HELMET = registerItem("sapphire_helmet",
            new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item SAPPHIRE_CHESTPLATE = registerItem("sapphire_chestplate",
            new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item SAPPHIRE_LEGGINGS = registerItem("sapphire_leggings",
            new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item SAPPHIRE_BOOTS = registerItem("sapphire_boots",
            new ArmorItem(ModArmorMaterial.SAPPHIRE, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));

    public static final Item SAPPHIRE_SHIELD = registerItem("sapphire_shield",
            new FabricBannerShieldItem(new FabricItemSettings().maxDamage(1300)
                    .group(ModItemGroup.ZAPPMOD), 10, 4, ModItems.SAPPHIRE));

    public static final Item SMALL_ETHERITE_CHUNK = registerItem("small_etherite_chunk",
            new Item(new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item ETHERITE_CHUNK = registerItem("big_etherite_chunk",
            new Item(new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item ETHERITE_INGOT = registerItem("etherite_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item RAW_ETHERITE_INGOT = registerItem("raw_etherite_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));

    public static final Item FIERY_BINDER = registerItem("fiery_binder",
            new Item(new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));

    public static final Item ETHERITE_SWORD = registerItem("etherite_sword",
            new SwordItem(ModToolMaterial.ETHERITE, 3, -2.4f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item ETHERITE_SHOVEL = registerItem("etherite_shovel",
            new ShovelItem(ModToolMaterial.ETHERITE, -1, -3f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));

    public static final Item ETHERITE_AXE = registerItem("etherite_axe",
            new ModAxe(ModToolMaterial.ETHERITE, 4, -3.3f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item ETHERITE_PICKAXE = registerItem("etherite_pickaxe",
            new ModPickaxe(ModToolMaterial.ETHERITE, -1, -2.9f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item ETHERITE_HOE = registerItem("etherite_hoe",
            new ModHoe(ModToolMaterial.ETHERITE, -2, 0f,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));

    public static final Item ETHERITE_HELMET = registerItem("etherite_helmet",
            new ArmorItem(ModArmorMaterial.ETHERITE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item ETHERITE_CHESTPLATE = registerItem("etherite_chestplate",
            new ArmorItem(ModArmorMaterial.ETHERITE, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item ETHERITE_LEGGINGS = registerItem("etherite_leggings",
            new ArmorItem(ModArmorMaterial.ETHERITE, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));
    public static final Item ETHERITE_BOOTS = registerItem("etherite_boots",
            new ArmorItem(ModArmorMaterial.ETHERITE, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.ZAPPMOD)));

    public static final Item ETHERITE_SHIELD = registerItem("etherite_shield",
            new FabricBannerShieldItem(new FabricItemSettings().maxDamage(3151)
                    .group(ModItemGroup.ZAPPMOD), 8, 15, ModItems.ETHERITE_INGOT));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM,new Identifier(Zappmod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + Zappmod.MOD_ID);
    }
}
