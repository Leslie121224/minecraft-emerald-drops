package com.example.emeraldmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod("emeraldmod") // 這裡的 modId 必須與 mods.toml 一致
public class EmeraldMod { // 這裡要改成 EmeraldMod
    public EmeraldMod() {
        System.out.println("Emerald Drop Mod Loaded!");
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        System.out.println("Client setup complete!");
    }
}
