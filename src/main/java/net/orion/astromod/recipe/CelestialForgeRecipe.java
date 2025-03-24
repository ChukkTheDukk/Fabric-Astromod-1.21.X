package net.orion.astromod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record CelestialForgeRecipe(Ingredient inputItem, Ingredient inputItem2, ItemStack output) implements Recipe<CelestialForgeRecipeInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        list.add(this.inputItem2);
        return list;
    }

    //read RecipeJSON files --> new CelestialForgeRecipe

    @Override
    public boolean matches(CelestialForgeRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }
        return inputItem.test(input.getStackInSlot(0)) && inputItem2.test(input.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(CelestialForgeRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CELESTIAL_FORGE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CELESTIAL_FORGE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<CelestialForgeRecipe> {
        public static final MapCodec<CelestialForgeRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(CelestialForgeRecipe::inputItem),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient2").forGetter(CelestialForgeRecipe::inputItem2),
                ItemStack.CODEC.fieldOf("result").forGetter(CelestialForgeRecipe::output)).apply(inst, CelestialForgeRecipe::new));
        public static final PacketCodec<RegistryByteBuf, CelestialForgeRecipe> STREAM_CODEC = PacketCodec.tuple(
                Ingredient.PACKET_CODEC, CelestialForgeRecipe::inputItem,
                Ingredient.PACKET_CODEC, CelestialForgeRecipe::inputItem2,
                ItemStack.PACKET_CODEC, CelestialForgeRecipe::output,
                CelestialForgeRecipe::new);

        @Override
        public MapCodec<CelestialForgeRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CelestialForgeRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
