package net.orion.astromod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.orion.astromod.block.entity.ModBlockEntities;
import net.orion.astromod.block.entity.renderer.CelestialPedestalBlockEntityRenderer;
import net.orion.astromod.block.entity.renderer.PedestalBlockEntityRenderer;
import net.orion.astromod.screen.ModScreenHandlers;
import net.orion.astromod.screen.custom.CelestialForgeScreen;

public class AstroModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_CE, CelestialPedestalBlockEntityRenderer::new);

        HandledScreens.register(ModScreenHandlers.CELESTIAL_FORGE_SCREEN_HANDLER, CelestialForgeScreen::new);
    }
}
