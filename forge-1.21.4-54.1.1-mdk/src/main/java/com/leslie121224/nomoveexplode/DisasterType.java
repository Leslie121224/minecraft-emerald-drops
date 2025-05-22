package com.leslie121224.cursedweather.disaster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;

public enum DisasterType {
    SKY_COLLAPSE("天空崩潰", "SKY COLLAPSE") {
        @Override
        public void run(ServerLevel level) {
            level.setWeatherParameters(0, 6000, true, true);
            level.setDayTime(13000); // 切夜晚
        }
    },

    ACID_RAIN("酸雨", "ACID RAIN") {
        @Override
        public void run(ServerLevel level) {
            level.setWeatherParameters(0, 6000, true, false); // 變成下雨，不打雷

            for (var player : level.players()) {
                if (level.isRainingAt(player.blockPosition())) {
                    player.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 5, 1));
                }
            }
        }
    },

    NETHERFALL("地獄降臨", "NETHERFALL") {
        @Override
        public void run(ServerLevel level) {
            for (var p : level.players()) {
                LargeFireball fireball = new LargeFireball(EntityType.FIREBALL, level);
                fireball.setPos(p.getX(), p.getY() + 10, p.getZ());
                fireball.setDeltaMovement(0, -0.5, 0); // 向下墜落
                fireball.setOwner(p);
                level.addFreshEntity(fireball);
            }
        }
    },

    TIME_DISTORTION("時間亂流", "TIME DISTORTION") {
        @Override
        public void run(ServerLevel level) {
            long time = level.getDayTime();
            level.setDayTime((time > 12000) ? 0 : 13000); // 切換日夜
        }
    };

    private final String zhName;
    private final String enName;

    DisasterType(String zh, String en) {
        this.zhName = zh;
        this.enName = en;
    }

    public String getDisplayName() {
        return zhName;
    }

    public String getEnglishName() {
        return enName;
    }

    public abstract void run(ServerLevel level);
}
