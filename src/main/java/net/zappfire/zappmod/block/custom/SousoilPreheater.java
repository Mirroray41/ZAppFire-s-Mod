package net.zappfire.zappmod.block.custom;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class SousoilPreheater extends Block {

    public SousoilPreheater(Settings settings) {
        super(settings);
    }
    public void OnSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
    {
        entity.damage(DamageSource.HOT_FLOOR, 1.0F);
    }
}
