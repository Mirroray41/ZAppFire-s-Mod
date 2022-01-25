package net.zappfire.zappmod.item.custom;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Optional;

public class Mirror extends Item {
    public Mirror(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings);
    }
    double x;
    double y;
    double z;

    public Optional<Vec3d> findPlayerRespawnPosition(ServerWorld world, BlockPos pos, float float2, boolean bool, boolean bool2) {

        double xl = pos.getX();
        double yl = pos.getY();
        double zl = pos.getZ();
        x = xl;
        y = yl;
        z = zl;
        return null;
    }
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.PRISMARINE_SHARD);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.AMBIENT, 0.5F, 0.0F);
        user.getItemCooldownManager().set(this, 500);
        user.teleport(x,y,z);
        user.incrementStat(Stats.USED.getOrCreateStat(this));

 {
     if (!user.getAbilities().creativeMode) {
         itemStack.damage(1,user,(player) -> player.sendToolBreakStatus(player.getActiveHand()));

     }
        }






        return TypedActionResult.success(itemStack, world.isClient());

    }
}