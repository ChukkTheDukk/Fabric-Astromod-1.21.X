package net.orion.astromod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.orion.astromod.AstroMod;

public class ModItems {

    public static final Item CELESTIAL_INGOT = registerItem("celestial_ingot", new Item(new Item.Settings()));
    public static final Item MOON = registerItem("moon", new Item(new Item.Settings().maxCount(1)));
    public static final Item MERCURY = registerItem("mercury", new Item(new Item.Settings().maxCount(1)));
    public static final Item VENUS = registerItem("venus", new Item(new Item.Settings().maxCount(1)));
    public static final Item EARTH = registerItem("earth", new Item(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AstroMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AstroMod.LOGGER.info("Registering Mod Items");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            //fabricItemGroupEntries.add(MOON);
            //fabricItemGroupEntries.add(MERCURY);
        });
    }
}
