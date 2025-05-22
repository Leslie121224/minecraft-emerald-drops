package com.leslie121224.cursedweather;

import com.leslie121224.cursedweather.event.TickEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("cursedweather")
public class CursedWeatherMod {
    public CursedWeatherMod() {
        System.out.println("[CursedWeather] Mod constructor running");
        MinecraftForge.EVENT_BUS.register(new TickEventHandler());
    }
}
