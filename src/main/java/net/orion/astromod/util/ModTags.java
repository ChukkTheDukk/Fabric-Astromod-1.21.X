package net.orion.astromod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.orion.astromod.AstroMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NO_BURN = createTag("no_burn");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(AstroMod.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> CELESTIAL_OBJECTS = createTag("celestial_objects");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(AstroMod.MOD_ID, name));
        }
    }
}
