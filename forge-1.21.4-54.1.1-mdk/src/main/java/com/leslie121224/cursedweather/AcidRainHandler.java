package com.leslie121224.cursedweather.disaster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class AcidRainHandler {
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
        if (tickCounter < 20) return; // 每 1 秒觸發一次
        tickCounter = 0;

        for (Player player : level.players()) {
            if (level.isRainingAt(player.blockPosition())) {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 2, 0));
            }
        }
    }
}