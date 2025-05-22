package com.leslie121224.cursedweather.disaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class DisasterManager {
    private static final DisasterType[] TYPES = DisasterType.values();
    private static DisasterType current = null;

    public static void sendWarning(ServerLevel level) {
        DisasterType next = getRandomDisaster();
        for (Player player : level.players()) {
            player.displayClientMessage(
                Component.literal("災難即將來襲！Disaster incoming!"),
                false
            );
        }
        current = next;
    }

    public static void triggerRandomDisaster(ServerLevel level) {
        if (current == null) current = getRandomDisaster();

        for (Player player : level.players()) {
            player.displayClientMessage(
                Component.literal(current.getDisplayName() + "來襲！" + current.getEnglishName() + " is here!"),
                false
            );
        }

        current.run(level);
        current = null;
    }

    private static DisasterType getRandomDisaster() {
        return TYPES[new Random().nextInt(TYPES.length)];
    }
}
