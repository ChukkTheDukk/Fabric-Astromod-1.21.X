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
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getStack(0);
        ModelTransformationMode modelTransformationMode = ModelTransformationMode.GUI;
        RotationAxis rotationAxis = RotationAxis.POSITIVE_Y;
        if((stack.isOf(ModItems.MOON))/**||(stack.isOf(ModItems.MERCURY))*/) {
            modelTransformationMode = ModelTransformationMode.GROUND;
        } /**else if(stack.isOf(ModItems.VENUS)) {
            modelTransformationMode = ModelTransformationMode.GROUND;
            rotationAxis = RotationAxis.NEGATIVE_Y;
        }*/ else if(stack.isOf(ModItems.MERCURY)) {//uranus
            modelTransformationMode = ModelTransformationMode.GROUND;
            rotationAxis = RotationAxis.POSITIVE_X;
        }
        matrices.push();
        matrices.translate(0.5f, 1.15f, 0.5f);
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.multiply(rotationAxis.rotationDegrees(entity.getRenderingRotation()));

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
