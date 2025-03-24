package net.orion.astromod.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.orion.astromod.AstroMod;

public class ModRecipes {
    public static final RecipeSerializer<CelestialForgeRecipe> CELESTIAL_FORGE_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(AstroMod.MOD_ID, "celestial_forge"),
                    new CelestialForgeRecipe.Serializer());
    public static final RecipeType<CelestialForgeRecipe> CELESTIAL_FORGE_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(AstroMod.MOD_ID, "celestial_forge"), new RecipeType<CelestialForgeRecipe>() {
                @Override
                public String toString() {
                    return "celestial_forge";
                }});

    public static void registerRecipes() {
        AstroMod.LOGGER.info("Registering Custom Recipes");
    }
}
