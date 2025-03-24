package net.orion.astromod.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.orion.astromod.AstroMod;
import net.orion.astromod.block.ModBlocks;
import net.orion.astromod.block.entity.custom.CelestialForgeBlockEntity;
import net.orion.astromod.block.entity.custom.CelestialPedestalBlockEntity;
import net.orion.astromod.block.entity.custom.PedestalBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AstroMod.MOD_ID, "pedestal_be"),
                    BlockEntityType.Builder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build(null));

    public static final BlockEntityType<CelestialPedestalBlockEntity> PEDESTAL_CE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AstroMod.MOD_ID, "pedestal_ce"),
                    BlockEntityType.Builder.create(CelestialPedestalBlockEntity::new, ModBlocks.CELESTIAL_PEDESTAL).build(null));

    public static final BlockEntityType<CelestialForgeBlockEntity> CELESTIAL_FORGE_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AstroMod.MOD_ID, "celestial_forge_ce"),
                    BlockEntityType.Builder.create(CelestialForgeBlockEntity::new, ModBlocks.CELESTIAL_FORGE).build(null));

    public static void registerBlockEntities() {
        AstroMod.LOGGER.info("Registering Mod Block Entities");
    }
}
