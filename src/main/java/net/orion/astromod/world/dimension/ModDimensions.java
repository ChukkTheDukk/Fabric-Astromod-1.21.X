package net.orion.astromod.world.dimension;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.orion.astromod.AstroMod;
import net.orion.astromod.util.ModTags;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> MOONDIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(AstroMod.MOD_ID, "moondim"));
    public static final RegistryKey<World> MOONDIM_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(AstroMod.MOD_ID, "moondim"));
    public static final RegistryKey<DimensionType> MOONDIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(AstroMod.MOD_ID, "moondim_type"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(MOONDIM_TYPE, new DimensionType(
                OptionalLong.empty(), //FIXED time
                false, //hasSkyLight
                false, //hasCeiling
                false, //ultraWarm
                true, //natural
                1.0, //coordinateScale
                true, //bedWorks
                true, //respawnAnchorWorks
                -64, //minY
                384, //height
                384, //logicalHeight
                ModTags.Blocks.NO_BURN, //infiniburn - CHANGE, NO OXYGEN
                DimensionTypes.THE_END_ID, //effectsLocation
                1.0f, //ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));

    }
}
