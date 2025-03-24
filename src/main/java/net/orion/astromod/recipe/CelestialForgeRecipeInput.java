package net.orion.astromod.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record CelestialForgeRecipeInput(ItemStack input, ItemStack input2) implements RecipeInput {


    @Override
    public ItemStack getStackInSlot(int slot) {
        if(slot == 1) {
            return input2;
        }
        return input;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
