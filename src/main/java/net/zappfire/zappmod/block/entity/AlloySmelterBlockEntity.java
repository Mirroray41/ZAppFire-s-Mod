package net.zappfire.zappmod.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zappfire.zappmod.block.custom.AlloySmelter;
import net.zappfire.zappmod.item.ModItems;
import net.zappfire.zappmod.item.inventory.ImplementedInventory;
import net.zappfire.zappmod.recipe.AlloySmelterRecipe;
import net.zappfire.zappmod.screen.AlloySmelterScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;



public class AlloySmelterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {


    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    // How many ticks it will take to craft the item (divide by twenty to get seconds count)
    // In our case this should be even divisible by 21 as that's our pixel count for our progress arrow
    private int maxProgress = 297;

    public AlloySmelterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_SMELTER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return AlloySmelterBlockEntity.this.progress;
                    case 1: return AlloySmelterBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: AlloySmelterBlockEntity.this.progress = value; break;
                    case 1: AlloySmelterBlockEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Alloy Smelter");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new AlloySmelterScreenHandler(syncId, inv, this,this.propertyDelegate);
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
        if(hasRecipe(entity)) {
            entity.progress++;
            if(entity.progress > entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(AlloySmelterBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<AlloySmelterRecipe> match = world.getRecipeManager()
                .getFirstMatch(AlloySmelterRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent()
                && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(AlloySmelterBlockEntity entity) {
        int fuel = 0;
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<AlloySmelterRecipe> match = world.getRecipeManager()
                .getFirstMatch(AlloySmelterRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {
            entity.removeStack(0,1);
            entity.removeStack(1,1);
            entity.removeStack(2,1);
            entity.removeStack(3,1);
            entity.setStack(4, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(4).getCount() + 1));
            entity.resetProgress();
        }
    }
    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(4).getItem() == output.getItem() || inventory.getStack(4).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(4).getMaxCount() > inventory.getStack(4).getCount();
    }
}