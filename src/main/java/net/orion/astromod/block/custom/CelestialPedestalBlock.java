package net.orion.astromod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.orion.astromod.block.entity.custom.CelestialPedestalBlockEntity;
import net.orion.astromod.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class CelestialPedestalBlock extends BlockWithEntity implements BlockEntityProvider{
    private static final VoxelShape SHAPE = CelestialPedestalBlock.createCuboidShape(1.5,0,1.5,14.5,13,14.5);
    public static final MapCodec<CelestialPedestalBlock> CODEC = CelestialPedestalBlock.createCodec(CelestialPedestalBlock::new);

    public CelestialPedestalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CelestialPedestalBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof CelestialPedestalBlockEntity) {
                ItemScatterer.spawn(world, pos, ((CelestialPedestalBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
            if(world.getBlockEntity(pos) instanceof CelestialPedestalBlockEntity celestialPedestalBlockEntity) {
                if(celestialPedestalBlockEntity.isEmpty() && !stack.isEmpty()) {
                    if (stack.isIn(ModTags.Items.CELESTIAL_OBJECTS)) {
                        celestialPedestalBlockEntity.setStack(0, stack.copyWithCount(1));
                        world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                        stack.decrement(1);
                        celestialPedestalBlockEntity.markDirty();
                        world.updateListeners(pos, state, state, 0);
                    }
                } else if(stack.isEmpty() && !player.isSneaking()) {
                    ItemStack stackOnPedestal = celestialPedestalBlockEntity.getStack(0);
                    player.setStackInHand(Hand.MAIN_HAND, stackOnPedestal);
                    world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                    celestialPedestalBlockEntity.clear();
                    celestialPedestalBlockEntity.markDirty();
                    world.updateListeners(pos, state, state, 0);
                }
            }
        return ItemActionResult.SUCCESS;
        }
}

