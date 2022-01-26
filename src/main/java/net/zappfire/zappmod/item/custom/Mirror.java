package net.zappfire.zappmod.item.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class Mirror extends Item {
    public Mirror(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings);
    }

    static int coolDown = 0;
    static int minCoolDown = 0;

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
        coolDown--;
        user.sendSystemMessage(new LiteralText("" + coolDown), Util.NIL_UUID);
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 100);
        user.sendSystemMessage(new LiteralText("activated" + coolDown), Util.NIL_UUID);
        coolDown = 100;
        if(coolDown <= minCoolDown){
            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.AMBIENT, 0.5F, 0.0F);
            user.teleport(0,0,0);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            coolDown = 100;
            {
                if (!user.getAbilities().creativeMode) {
                    itemStack.damage(1,user,(player) -> player.sendToolBreakStatus(player.getActiveHand()));
                    user.getItemCooldownManager().set(this, 500);
                }
                else {
                    user.getItemCooldownManager().set(this, 50);
                }
            }
        }


        return TypedActionResult.success(itemStack, world.isClient());
    }
}
