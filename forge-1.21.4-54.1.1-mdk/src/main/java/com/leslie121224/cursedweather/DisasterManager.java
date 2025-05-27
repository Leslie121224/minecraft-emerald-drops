package com.leslie121224.cursedweather.disaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class DisasterManager {
    private static final DisasterType[] TYPES = DisasterType.values();
    private static DisasterType next = null;
    private static DisasterType current = null;

    public static void sendWarning(ServerLevel level) {
        next = getRandomDisaster();

        for (Player player : level.players()) {
            player.displayClientMessage(
                Component.literal("有什麼要來了... Something is incoming..."),
                false
            );
        }
    }

    public static void clearAllDisasters() {
        AcidRainHandler.disable();
        TimeDistortionHandler.disable();
        NetherfallHandler.disable();
    }

    public static void triggerRandomDisaster(ServerLevel level) {
        clearAllDisasters();

        current = getRandomDisaster();

        for (Player player : level.players()) {
            player.displayClientMessage(
                Component.literal(current.getDisplayName() + "來襲！" + current.getEnglishName() + " is here!"),
                false
            );
        }

        current.run(level);
    }

    private static DisasterType getRandomDisaster() {
        return TYPES[new Random().nextInt(TYPES.length)];
    }
}
