package net.orion.astromod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.orion.astromod.block.entity.ModBlockEntities;
import net.orion.astromod.block.entity.renderer.PedestalBlockEntityRenderer;

public class AstroModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_CE, PedestalBlockEntityRenderer::new);
    }
}
