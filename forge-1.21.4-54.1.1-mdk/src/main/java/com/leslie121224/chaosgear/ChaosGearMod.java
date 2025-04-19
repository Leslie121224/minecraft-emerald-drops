package com.leslie121224.chaosgear;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.MinecraftForge;

@Mod("chaosgear")
public class ChaosGearMod {
    public ChaosGearMod() {
        MinecraftForge.EVENT_BUS.register(new TickHandler());
    }
}
