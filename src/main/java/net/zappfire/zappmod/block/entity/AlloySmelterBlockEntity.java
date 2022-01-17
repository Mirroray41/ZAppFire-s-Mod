package net.zappfire.zappmod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zappfire.zappmod.block.custom.AlloySmelter;
import net.zappfire.zappmod.item.ModItems;
import net.zappfire.zappmod.item.inventory.ImplementedInventory;
import org.jetbrains.annotations.Nullable;

public class AlloySmelterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    public AlloySmelterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_SMELTER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Alloy Furnace");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return null;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        super.writeNbt(nbt);
    }
    public static void tick(World world, BlockPos pos, BlockState state, AlloySmelterBlockEntity entity) {
        if(hasRecipe(entity) && world.isThundering() && hasNotReachedStackLimit(entity)) {
            craftItem(entity);

            if(!world.isClient()) {
                EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, null, null, null, pos,
                        SpawnReason.TRIGGERED, true, true);
            }
        }
    }

    private static void craftItem(AlloySmelterBlockEntity entity) {
        entity.removeStack(0, 1);
        entity.removeStack(1, 1);

        entity.setStack(2, new ItemStack(ModItems.SAPPHIRE, entity.getStack(2).getCount() + 1));
    }

    private static boolean hasRecipe(LightningChannelerBlockEntity entity) {
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == ModItems.IRON_WOOL;
        boolean hasItemInSecondSlot = entity.getStack(1).getItem() == ModItems.PEPPER;

        return hasItemInFirstSlot && hasItemInSecondSlot;
    }

    private static boolean hasNotReachedStackLimit(LightningChannelerBlockEntity entity) {
        return entity.getStack(2).getCount() < entity.getStack(2).getMaxCount();
    }
}