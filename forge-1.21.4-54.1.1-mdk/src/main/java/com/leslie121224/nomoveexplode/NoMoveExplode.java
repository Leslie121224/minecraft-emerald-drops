package com.leslie121224.nomoveexplode;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("nomoveexplode")
public class NoMoveExplode {
    public NoMoveExplode() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Initialization logic if needed
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Client-specific setup if needed
    }
}
