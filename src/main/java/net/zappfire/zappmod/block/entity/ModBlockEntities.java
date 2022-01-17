package net.zappfire.zappmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.zappfire.zappmod.Zappmod;
import net.zappfire.zappmod.block.ModBlocks;


public class ModBlockEntities {
    public static BlockEntityType<AlloySmelterBlockEntity> ALLOY_SMELTER_BLOCK_ENTITY =
            Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Zappmod.MOD_ID, "alloy_smelter"),
                    FabricBlockEntityTypeBuilder.create(AlloySmelterBlockEntity::new,
                            new Block[]{ModBlocks.ALLOY_SMELTER}).build(null));
}
