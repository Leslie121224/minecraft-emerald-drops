package com.leslie121224.cursedweather.disaster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;

public class NetherfallHandler {
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

        if (tickCounter >= 20) { // 每秒觸發一次
            tickCounter = 0;

            for (Player player : level.players()) {
                double px = player.getX();
                double py = player.getY();
                double pz = player.getZ();

                int radius = 32; // 半徑32 = 直徑64
                int count = 40; // 每秒掉 40 顆火球

                for (int i = 0; i < count; i++) {
                    double x = px + (level.random.nextDouble() - 0.5) * 2 * radius;
                    double z = pz + (level.random.nextDouble() - 0.5) * 2 * radius;
                    double y = py + 20 + level.random.nextInt(10);

                    SmallFireball fireball = new SmallFireball(EntityType.SMALL_FIREBALL, level);
                    fireball.setPos(x, y, z);
                    fireball.setDeltaMovement(0, -0.3, 0);
                    fireball.setOwner(player);
                    level.addFreshEntity(fireball);
                }
            }
        }
    }
}
