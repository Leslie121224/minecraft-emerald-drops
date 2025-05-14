package com.leslie121224.nomoveexplode;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class MovementTracker {
    private static final Map<UUID, Vec3> lastPositions = new HashMap<>();
    private static final Map<UUID, Integer> stationaryTicks = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide) return;

        ServerPlayer player = (ServerPlayer) event.player;
        UUID uuid = player.getUUID();
        Vec3 currentPos = player.position();

        Vec3 lastPos = lastPositions.getOrDefault(uuid, currentPos);
        int ticks = stationaryTicks.getOrDefault(uuid, 0);

        boolean moved = !currentPos.equals(lastPos);

        if (moved) {
            stationaryTicks.put(uuid, 0);
        } else {
            ticks++;
            if (ticks >= 20) {
                player.hurt(player.damageSources().magic(), Float.MAX_VALUE);
                player.level().explode(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    4.0F,
                    false,
                    Level.ExplosionInteraction.MOB
                );
                ticks = 0;
            }
            stationaryTicks.put(uuid, ticks);
        }

        lastPositions.put(uuid, currentPos);
    }
}
