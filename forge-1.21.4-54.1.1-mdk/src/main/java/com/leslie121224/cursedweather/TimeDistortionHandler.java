package com.leslie121224.cursedweather.disaster;

import net.minecraft.server.level.ServerLevel;

public class TimeDistortionHandler {
    private static boolean active = false;
    private static int tickCounter = 0;

    public static void enable() {
        active = true;
        tickCounter = 0;
    }

    public static void disable() {
        active = false;
    }

    public static void tick(ServerLevel level) {
        if (!active) return;

        tickCounter++;

        if (tickCounter >= 20) { // 每秒執行一次
            tickCounter = 0;

            long time = level.getDayTime();
            long jump = (level.random.nextBoolean()) ? 13000 : 0; // 夜 or 早
            level.setDayTime(time + jump);

            // Optional：你可以在這裡放粒子或閃光效果
        }
    }
}
