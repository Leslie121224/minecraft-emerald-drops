package com.leslie121224.cursedweather.event;

import com.leslie121224.cursedweather.disaster.DisasterManager;
import com.leslie121224.cursedweather.disaster.AcidRainHandler;
import com.leslie121224.cursedweather.disaster.TimeDistortionHandler;
import com.leslie121224.cursedweather.disaster.NetherfallHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TickEventHandler {
    private static final int DISASTER_INTERVAL = 20 * 6 * 5; // 每 5 分鐘觸發災難 // test
    private static final int WARNING_OFFSET = DISASTER_INTERVAL - 20 * 15; // 15 秒前警告

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

            AcidRainHandler.tick(level);
            TimeDistortionHandler.tick(level);
            NetherfallHandler.tick(level);
        }
    }
}
