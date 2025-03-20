package net.orion.astromod.block.entity.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.orion.astromod.block.entity.custom.PedestalBlockEntity;
import net.orion.astromod.item.ModItems;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    public PedestalBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(PedestalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        boolean celestial = false;
        float degreePerTick = 0.5f;
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getStack(0);
        ModelTransformationMode modelTransformationMode = ModelTransformationMode.GUI;
        RotationAxis rotationAxis = RotationAxis.POSITIVE_Y;
        if((stack.isOf(ModItems.MOON))||(stack.isOf(ModItems.MERCURY))) {
            modelTransformationMode = ModelTransformationMode.GROUND;
            celestial = true;
            if(stack.isOf(ModItems.MOON)) {
                degreePerTick = 0.3f;
            } else if(stack.isOf(ModItems.MERCURY)) {
                degreePerTick = 0.2f;
            }
        } else if(stack.isOf(ModItems.VENUS)) {
            modelTransformationMode = ModelTransformationMode.GROUND;
            rotationAxis = RotationAxis.NEGATIVE_Y;
            celestial = true;
            degreePerTick = 0.1f;
        }/** else if(stack.isOf(ModItems.URANUS)) {
            modelTransformationMode = ModelTransformationMode.GROUND;
            rotationAxis = RotationAxis.POSITIVE_X;
            celestial = true;
        }*/
        matrices.push();
        matrices.translate(0.5f, 1.15f, 0.5f);
        if (!celestial) {
            matrices.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrices.scale(1f, 1f, 1f);
        }
        matrices.multiply(rotationAxis.rotationDegrees(entity.getRenderingRotation(degreePerTick)), 0f, 0.1875f, 0f);//replace 0.1875f with either 0.3125f or 0.40625f

        itemRenderer.renderItem(stack, modelTransformationMode, getLightLevel(entity.getWorld(),
                entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
