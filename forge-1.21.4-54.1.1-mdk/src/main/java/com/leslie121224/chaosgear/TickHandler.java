package com.leslie121224.chaosgear;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraft.server.level.ServerPlayer;

@Mod.EventBusSubscriber
public class TickHandler {
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        tickCounter++;
        if (tickCounter >= 300) { // 20 ticks * 15 seconds
            tickCounter = 0;

            for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                Randomizer.replaceInventory(player);
            }
        }
    }
}
