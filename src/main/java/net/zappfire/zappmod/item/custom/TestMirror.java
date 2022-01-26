package net.zappfire.zappmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.zappfire.zappmod.mixin.ServerPlayerEntityRespawnMixin;

import java.util.Optional;

public class TestMirror extends Item {
    public TestMirror(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand, LivingEntity entity) {
        entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        MinecraftServer server = entity.getEntityWorld().getServer();
        ServerPlayerEntity player = (ServerPlayerEntity) entity;
        BlockPos blockPos = player.getSpawnPointPosition();
        float f = player.getSpawnAngle();
        boolean bl = false;
        ServerWorld serverWorld = server.getWorld(player.getSpawnPointDimension());
        Optional<Vec3d> optional2;
        if (serverWorld != null && blockPos != null) {
            optional2 = PlayerEntity.findRespawnPosition(serverWorld, blockPos, f, bl, true);
        } else {
            optional2 = Optional.empty();
        }
        ServerWorld serverWorld2 = serverWorld != null && optional2.isPresent() ? serverWorld
                : server.getOverworld();
        BlockPos spawnPos = serverWorld2.getSpawnPos();
        player.refreshPositionAndAngles((double) spawnPos.getX() + 0.5D, (double) (spawnPos.getY() + 1),
                (double) spawnPos.getZ() + 0.5D, serverWorld2.getSpawnAngle(), 0.0F);
        ((ServerPlayerEntityRespawnMixin) player).invokeMoveToSpawn(serverWorld2);

        if (optional2.isPresent()) {
            BlockState blockState = serverWorld2.getBlockState(blockPos);
            boolean bl3 = blockState.isOf(Blocks.RESPAWN_ANCHOR);
            Vec3d vec3d = (Vec3d) optional2.get();
            float h;
            if (!blockState.isIn(BlockTags.BEDS) && !bl3) {
                h = f;
            } else {
                Vec3d vec3d2 = Vec3d.ofBottomCenter(blockPos).subtract(vec3d).normalize();
                h = (float) MathHelper
                        .wrapDegrees(MathHelper.atan2(vec3d2.z, vec3d2.x) * 57.2957763671875D - 90.0D);
            }
            player.refreshPositionAndAngles(vec3d.x, vec3d.y, vec3d.z, h, 0.0F);
            player.setSpawnPoint(serverWorld2.getRegistryKey(), blockPos, f, bl, false);
        } else if (blockPos != null) {
            player.networkHandler
                    .sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.NO_RESPAWN_BLOCK, 0.0F));
        }
        while (!serverWorld2.isSpaceEmpty(player) && player.getY() < 256.0D) {
            player.updatePosition(player.getX(), player.getY() + 1.0D, player.getZ());
        }

        Vec3d teleportPosition = player.getPos();
        ServerWorld teleportWorld = serverWorld2;

        player.detach();
        player.setVelocity(0, 0, 0);
        player.fallDistance = 0;
        teleportWorld.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, new ChunkPos(new BlockPos(teleportPosition.x, teleportPosition.y, teleportPosition.z)), 1, entity.getId());

        if (entity.getEntityWorld() == serverWorld2) {
            player.teleport(player.getX(), player.getY(), player.getZ());
        } else {
            player.teleport(teleportWorld, player.getX(), player.getY(), player.getZ(), player.getYaw(1), 0);
        }
        return null;
    }
}
