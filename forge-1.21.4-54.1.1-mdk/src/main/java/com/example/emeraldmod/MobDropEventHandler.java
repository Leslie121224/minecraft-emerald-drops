package com.example.emeraldmod;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;

import java.util.Random;

@Mod.EventBusSubscriber
public class MobDropEventHandler {
    private static final Random random = new Random();

    @SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event) {
        // 獲取死亡的生物
        LivingEntity entity = event.getEntity();

        // 確保生物是敵對生物（Monster）
        if (!(entity instanceof Monster)) {
            return;
        }

        // 檢查擊殺者是否為玩家
        if (event.getSource().getEntity() instanceof Player) {
            // 50% 掉落機率
            if (random.nextDouble() < 0.5) {
                // 獲取生物所在的世界
                if (entity.getCommandSenderWorld() instanceof ServerLevel) {
                    ServerLevel world = (ServerLevel) entity.getCommandSenderWorld();
                
                    // 在生物死亡的位置生成綠寶石
                    ItemEntity emeraldDrop = new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(),
                            new ItemStack(Items.EMERALD, 1));
                    world.addFreshEntity(emeraldDrop);
                }
            }
        }
    }
}
