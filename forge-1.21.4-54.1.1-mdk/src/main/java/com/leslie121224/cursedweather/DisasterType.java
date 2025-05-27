package com.leslie121224.cursedweather.disaster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.SmallFireball;

public enum DisasterType {
    SKY_COLLAPSE("天空崩潰", "SKY COLLAPSE") {
        @Override
        public void run(ServerLevel level) {
            level.setWeatherParameters(0, 1200, true, true);
            level.setDayTime(13000); // 切夜晚
        }
    },

    ACID_RAIN("酸雨", "ACID RAIN") {
        @Override
        public void run(ServerLevel level) {
            level.setWeatherParameters(0, 1200, true, false);
            level.setRainLevel(1.0F); // isRainingAt 成立
            AcidRainHandler.enable();
        }
    },

    NETHERFALL("地獄降臨", "NETHERFALL") {
        @Override
        public void run(ServerLevel level) {
            level.setWeatherParameters(0, 1200, false, true); // 打雷但不下雨
            NetherfallHandler.enable();
        }
    },

    TIME_DISTORTION("時間亂流", "TIME DISTORTION") {
        @Override
        public void run(ServerLevel level) {
            TimeDistortionHandler.enable();
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
