package net.zappfire.zappmod.item.custom;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PrismarineMirror extends Item {

    public PrismarineMirror(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.playSound(SoundEvents.ENTITY_ENDER_DRAGON_DEATH, 70.0F, 1.0F);
        playerEntity.ClientPlayNetworkHandler.getActiveTotemOfUndying
        playerEntity.getItemCooldownManager().set(this, 50);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}