package net.orion.astromod;

import net.fabricmc.api.ModInitializer;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.util.Identifier;
import net.orion.astromod.block.ModBlocks;
import net.orion.astromod.block.entity.ModBlockEntities;
import net.orion.astromod.item.ModItemGroups;
import net.orion.astromod.item.ModItems;
import net.orion.astromod.recipe.ModRecipes;
import net.orion.astromod.screen.ModScreenHandlers;
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
		ModScreenHandlers.registerScreenHandlers();

		ModRecipes.registerRecipes();

		CustomPortalBuilder.beginPortal()
				.frameBlock(ModBlocks.LUNAR_STONE)
				.lightWithItem(ModItems.CELESTIAL_INGOT)
				.destDimID(Identifier.of(AstroMod.MOD_ID, "moondim"))
				.tintColor(11184810)
				.registerPortal();
	}
}