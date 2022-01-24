package net.zappfire.zappmod.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import net.zappfire.zappmod.block.entity.AlloySmelterBlockEntity;
import net.zappfire.zappmod.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.block.CarvedPumpkinBlock.FACING;

public class AlloySmelter extends BlockWithEntity implements BlockEntityProvider {
    public AlloySmelter(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(ON, false));
    }

    public VoxelShape getPlacement(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockView view, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        switch(dir) {
            default:
                return VoxelShapes.fullCube();
        }
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }


    public static final BooleanProperty ON = BooleanProperty.of("on");


    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(ON);
        stateManager.add(Properties.HORIZONTAL_FACING);
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AlloySmelterBlockEntity) {
                ItemScatterer.spawn(world, pos, (AlloySmelterBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlloySmelterBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.ALLOY_SMELTER_BLOCK_ENTITY, AlloySmelterBlockEntity::tick);
    }
}