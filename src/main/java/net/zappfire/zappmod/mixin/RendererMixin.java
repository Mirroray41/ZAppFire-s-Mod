package net.zappfire.zappmod.mixin;

import com.github.crimsondawn45.fabricshieldlib.initializers.FabricShieldLibClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.zappfire.zappmod.ModClient;
import net.zappfire.zappmod.item.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltinModelItemRenderer.class)
public class RendererMixin {
    private ShieldEntityModel modelSapphireShield;
    private static final SpriteIdentifier SAPPHIRE_SHIELD_BASE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("zappmod","entity/sapphire_shield_base"));
    private static final SpriteIdentifier SAPPHIRE_SHIELD_BASE_NO_PATTERN = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("zappmod","entity/sapphire_shield_base_nopattern"));

    private ShieldEntityModel modelEtheriteShield;
    private static final SpriteIdentifier ETHERITE_SHIELD_BASE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("zappmod","entity/etherite_shield_base"));
    private static final SpriteIdentifier ETHERITE_SHIELD_BASE_NO_PATTERN = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("zappmod","entity/etherite_shield_base_nopattern"));

    @Final
    @Shadow
    private EntityModelLoader entityModelLoader;

    @Inject(method = "reload", at = @At("HEAD"))
    private void setModelSapphireShield(CallbackInfo ci){
        modelSapphireShield = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModClient.SAPPHIRE_SHIELD_MODEL_LAYER));
    }

    @Inject(method = "reload", at = @At("HEAD"))
    private void setModelEtheriteShield(CallbackInfo ci){
        modelEtheriteShield = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModClient.ETHERITE_SHIELD_MODEL_LAYER));
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void mainRender(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, CallbackInfo ci) {
        if (stack.isOf(ModItems.SAPPHIRE_SHIELD)) {
            FabricShieldLibClient.renderBanner(stack, matrices, vertexConsumers, light, overlay, modelSapphireShield, SAPPHIRE_SHIELD_BASE, SAPPHIRE_SHIELD_BASE_NO_PATTERN);
        }
        if (stack.isOf(ModItems.ETHERITE_SHIELD)) {
            FabricShieldLibClient.renderBanner(stack, matrices, vertexConsumers, light, overlay, modelEtheriteShield, ETHERITE_SHIELD_BASE, ETHERITE_SHIELD_BASE_NO_PATTERN);
        }
    }
}