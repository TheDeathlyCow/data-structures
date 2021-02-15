package com.github.thedeathlycow;

import com.github.thedeathlycow.gen.ModStructures;
import net.fabricmc.api.ModInitializer;

public class DataStructures implements ModInitializer {

    public static final String MODID = "structures";

    @Override
    public void onInitialize() {

        ModStructures.registerStructures();

    }


}
