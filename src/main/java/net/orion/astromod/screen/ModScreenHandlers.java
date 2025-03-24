package net.orion.astromod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.orion.astromod.AstroMod;
import net.orion.astromod.screen.custom.CelestialForgeScreenHandler;

public class ModScreenHandlers {

    public static final ScreenHandlerType<CelestialForgeScreenHandler> CELESTIAL_FORGE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(AstroMod.MOD_ID, "celestial_forge_screen_handler"),
                    new ExtendedScreenHandlerType<>(CelestialForgeScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        AstroMod.LOGGER.info("Registering Mod Screen Handlers");
    }
}
