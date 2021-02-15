package com.github.thedeathlycow.gen;

import com.github.thedeathlycow.DataStructures;
import com.github.thedeathlycow.gen.structures.PlainsWell;
import com.github.thedeathlycow.gen.structures.PlainsWellGenerator;
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
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModStructures {

    public static final StructurePieceType PLAINS_WELL_PIECE = PlainsWellGenerator.PlainsWellPiece::new;
    private static final StructureFeature<DefaultFeatureConfig> PLAINS_WELL_STRUCTURE = new PlainsWell(DefaultFeatureConfig.CODEC);
    private static final ConfiguredStructureFeature<?, ?> PLAINS_WELL_CONFIGURED = PLAINS_WELL_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

    public static void registerStructures() {
        register("plains_well", PLAINS_WELL_PIECE, PLAINS_WELL_STRUCTURE);


        RegistryKey<ConfiguredStructureFeature<?, ?>> configuredPlainsWell = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN,
                new Identifier(DataStructures.MODID, "plains_well"));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, configuredPlainsWell.getValue(), PLAINS_WELL_CONFIGURED);

        BiomeModifications.addStructure(BiomeSelectors.all(), configuredPlainsWell);



    }

    private static void register(String name, StructurePieceType piece, StructureFeature<DefaultFeatureConfig> structure) {

        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(DataStructures.MODID, name), piece);

        FabricStructureBuilder.create(new Identifier(DataStructures.MODID, name), structure)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();


    }
}
