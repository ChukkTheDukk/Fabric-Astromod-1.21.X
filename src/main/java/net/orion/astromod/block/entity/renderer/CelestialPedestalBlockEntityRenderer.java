package net.orion.astromod.block.entity.renderer;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.orion.astromod.block.entity.custom.CelestialPedestalBlockEntity;
import net.orion.astromod.block.entity.custom.PedestalBlockEntity;
import net.orion.astromod.item.ModItems;

public class CelestialPedestalBlockEntityRenderer implements BlockEntityRenderer<CelestialPedestalBlockEntity> {
    public CelestialPedestalBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(CelestialPedestalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        float degreePerTick = 0f;
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getStack(0);
        ModelTransformationMode modelTransformationMode = ModelTransformationMode.GROUND;
        RotationAxis rotationAxis = RotationAxis.POSITIVE_Y;
        if(stack.isOf(ModItems.MOON)) {
            degreePerTick = 0.3f;
        } else if(stack.isOf(ModItems.MERCURY)) {
            degreePerTick = 0.2f;
        } else if(stack.isOf(ModItems.VENUS)) {
            rotationAxis = RotationAxis.NEGATIVE_Y;
            degreePerTick = 0.1f;
        } else if(stack.isOf(ModItems.EARTH)) {
            degreePerTick = 0.5f;
        } else if(stack.isOf(ModItems.MARS)) {
            degreePerTick = 0.4f;
        } else if(stack.isOf(ModItems.JUPITER)) {
            degreePerTick = 1.1f;
        } else if(stack.isOf(ModItems.SATURN)) {
            degreePerTick = 1.0f;
        } else if(stack.isOf(ModItems.URANUS)) {
            rotationAxis = RotationAxis.POSITIVE_X;
            degreePerTick = 0.7f;
        } else if(stack.isOf(ModItems.NEPTUNE)) {
            degreePerTick = 0.8f;
        } else if(stack.isOf(ModItems.SUN)) {
            degreePerTick = 0.3f;
        }
        matrices.push();
        matrices.translate(0.5f, 1.15f, 0.5f);
        matrices.scale(1.5f, 1.5f, 1.5f);
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
