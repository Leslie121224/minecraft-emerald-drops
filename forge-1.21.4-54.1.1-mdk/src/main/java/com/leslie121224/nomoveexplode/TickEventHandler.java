package com.leslie121224.cursedweather.event;

import com.leslie121224.cursedweather.disaster.DisasterManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TickEventHandler {
    private static final int DISASTER_INTERVAL = 6000; // 5分鐘
    private static final int WARNING_OFFSET = 300;     // 30秒前警告

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        for (ServerLevel level : event.getServer().getAllLevels()) {
            long time = level.getDayTime() % DISASTER_INTERVAL;

            if (time == WARNING_OFFSET) {
                DisasterManager.sendWarning(level);
            }

            if (time == 0) {
                DisasterManager.triggerRandomDisaster(level);
            }
        }
    }
}
