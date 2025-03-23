package net.orion.astromod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.orion.astromod.AstroMod;
import net.orion.astromod.block.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup ASTROMOD_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AstroMod.MOD_ID, "astromod_item_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.LUNAR_STONE))
                    .displayName(Text.translatable("itemgroup.astromod_item_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CELESTIAL_INGOT);
                        entries.add(ModBlocks.LUNAR_STONE);
                    })
                    .build());

    public static final ItemGroup ASTROMOD_TROPHY_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AstroMod.MOD_ID, "astromod_trophy_item_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.PEDESTAL))
                    .displayName(Text.translatable("itemgroup.astromod_trophy_item_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.PEDESTAL);
                        entries.add(ModBlocks.CELESTIAL_PEDESTAL);
                        entries.add(ModItems.MOON);
                        entries.add(ModItems.MERCURY);
                        entries.add(ModItems.VENUS);
                    })
                    .build());

    public static void registerItemGroups() {
        AstroMod.LOGGER.info("Registering Mod Item Groups");
    }
}
