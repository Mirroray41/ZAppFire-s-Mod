package net.zappfire.zappmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Blocks;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.zappfire.zappmod.block.ModBlocks;
import net.zappfire.zappmod.item.ModItems;
import net.zappfire.zappmod.recipe.ModRecipes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Zappmod implements ModInitializer {

	public static final String MOD_ID = "zappmod";

	public static final Logger LOGGER = LogManager.getLogger("zappmod");

	public static final DefaultParticleType MIRROR_PARTICLE = FabricParticleTypes.simple();

	private static ConfiguredFeature<?, ?> OVERWORLD_SAPPHIRE_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
					ModBlocks.SAPPHIRE_ORE.getDefaultState(),
					5)); // vein size

	public static PlacedFeature OVERWORLD_SAPPHIRE_ORE_PLACED_FEATURE = OVERWORLD_SAPPHIRE_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(1), // number of veins per chunk
			SquarePlacementModifier.of(), // spreading horizontally
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(15))); // height

	private static ConfiguredFeature<?, ?> OVERWORLD_DEEPSLATE_SAPPHIRE_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
					ModBlocks.DEEPSLATE_SAPPHIRE_ORE.getDefaultState(),
					7)); // vein size

	public static PlacedFeature OVERWORLD_DEEPSLATE_SAPPHIRE_ORE_PLACED_FEATURE = OVERWORLD_DEEPSLATE_SAPPHIRE_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(1), // number of veins per chunk
			SquarePlacementModifier.of(), // spreading horizontally
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(-5))); // height

	private static ConfiguredFeature<?, ?> END_ETHERITE_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					new BlockMatchRuleTest(Blocks.END_STONE), // we use new BlockMatchRuleTest(Blocks.END_STONE) here
					ModBlocks.ETHERITE_ORE.getDefaultState(),
					5));

	public static PlacedFeature END_ETHERITE_ORE_PLACED_FEATURE = END_ETHERITE_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(2),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(20)));

	@Override
	public void onInitialize() {
		Registry.register(Registry.PARTICLE_TYPE, new Identifier("zappmod", "mirror_particle"), MIRROR_PARTICLE);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier("zappmod", "overworld_sapphire_ore"), OVERWORLD_SAPPHIRE_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("zappmod", "overworld_sapphire_ore"),
				OVERWORLD_SAPPHIRE_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier("zappmod", "overworld_sapphire_ore")));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier("zappmod", "overworld_deepslate_sapphire_ore"), OVERWORLD_DEEPSLATE_SAPPHIRE_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("zappmod", "overworld_deepslate_sapphire_ore"),
				OVERWORLD_DEEPSLATE_SAPPHIRE_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier("zappmod", "overworld_deepslate_sapphire_ore")));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier("zappmod", "end_etherite_ore"), END_ETHERITE_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("zappmod", "end_etherite_ore"),
				END_ETHERITE_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier("zappmod", "end_etherite_ore")));

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModRecipes.register();

		LOGGER.info("Zappmod init complete");
	}
}
