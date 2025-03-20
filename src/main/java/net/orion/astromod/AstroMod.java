package net.orion.astromod;

import net.fabricmc.api.ModInitializer;

import net.orion.astromod.block.ModBlocks;
import net.orion.astromod.block.entity.ModBlockEntities;
import net.orion.astromod.item.ModItemGroups;
import net.orion.astromod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AstroMod implements ModInitializer {
	public static final String MOD_ID = "astromod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerBlockEntities();
	}
}