package net.zappfire.zappmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;
import net.zappfire.zappmod.block.ModBlocks;
import net.zappfire.zappmod.screen.AlloySmelterScreen;
import net.zappfire.zappmod.screen.ModScreenHandlers;

public class ModClient implements ClientModInitializer {

    public static final EntityModelLayer SAPPHIRE_SHIELD_MODEL_LAYER = new EntityModelLayer(new Identifier("zappmod", "sapphire_shield"),"main");
    public static final EntityModelLayer ETHERITE_SHIELD_MODEL_LAYER = new EntityModelLayer(new Identifier("zappmod", "etherite_shield"),"main");

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(SAPPHIRE_SHIELD_MODEL_LAYER, ShieldEntityModel::getTexturedModelData);
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier("zappmod", "entity/sapphire_shield_base"));
            registry.register(new Identifier("zappmod", "entity/sapphire_shield_base_nopattern"));
        });
        EntityModelLayerRegistry.registerModelLayer(ETHERITE_SHIELD_MODEL_LAYER, ShieldEntityModel::getTexturedModelData);
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier("zappmod", "entity/etherite_shield_base"));
            registry.register(new Identifier("zappmod", "entity/etherite_shield_base_nopattern"));
        });
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOULSOIL_PREHEATER, RenderLayer.getTranslucent());

        ScreenRegistry.register(ModScreenHandlers.ALLOY_SMELTER_SCREEN_HANDLER, AlloySmelterScreen::new);
    }
}