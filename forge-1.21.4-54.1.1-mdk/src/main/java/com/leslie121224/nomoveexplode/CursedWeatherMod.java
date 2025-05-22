package com.leslie121224.cursedweather;

import com.leslie121224.cursedweather.event.TickEventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.MinecraftForge;

@Mod("cursedweather")
public class CursedWeatherMod {
    public static final String MODID = "cursedweather";

    public CursedWeatherMod() {
        MinecraftForge.EVENT_BUS.register(new TickEventHandler());
    }
}
