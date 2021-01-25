package com.github.thedeathlycow;

import com.github.thedeathlycow.gen.ModStructures;
import com.github.thedeathlycow.gen.structures.PlainsWell;
import com.github.thedeathlycow.gen.structures.PlainsWellGenerator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class DataStructures implements ModInitializer {

    public static final StructurePieceType PLAINS_WELL_PIECE = PlainsWellGenerator.PlainsWellPiece::new;
    private static final StructureFeature<DefaultFeatureConfig> PLAINS_WELL_STRUCTURE = new PlainsWell(DefaultFeatureConfig.CODEC);
    private static final ConfiguredStructureFeature<?, ?> PLAINS_WELL_CONFIGURED = PLAINS_WELL_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

    @Override
    public void onInitialize() {
        registerStructures();
    }

    public static void registerStructures() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier("tutorial", "my_piece"), PLAINS_WELL_PIECE);
        FabricStructureBuilder.create(new Identifier("tutorial", "my_structure"), PLAINS_WELL_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();

        RegistryKey<ConfiguredStructureFeature<?, ?>> myConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN,
                new Identifier("tutorial", "my_structure"));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, myConfigured.getValue(), PLAINS_WELL_CONFIGURED);


        BiomeModifications.addStructure(BiomeSelectors.all(), myConfigured);
    }
}
