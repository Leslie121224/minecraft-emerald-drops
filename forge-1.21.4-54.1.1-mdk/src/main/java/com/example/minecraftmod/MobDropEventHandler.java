package com.example.minecraftmod;

import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Mod.EventBusSubscriber(modid = "emeraldmod")
public class MobDropEventHandler {
    @SubscribeEvent
    public static void onMobDeath(LivingDropsEvent event) {
        if (event.getEntity() instanceof Monster) {
            event.getDrops().add(new ItemEntity(
                event.getEntity().getCommandSenderWorld(),
                event.getEntity().getX(), 
                event.getEntity().getY(), 
                event.getEntity().getZ(),
                new ItemStack(Items.EMERALD, 1)
            ));
        }
    }
}
