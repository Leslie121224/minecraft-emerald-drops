package com.leslie121224.nomoveexplode;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Randomizer {
    private static final Random random = new Random();

    private static final Item[] TOOLS = {
        // 劍類（全材質）
        Items.WOODEN_SWORD, Items.STONE_SWORD, Items.IRON_SWORD,
        Items.GOLDEN_SWORD, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD,
    
        // 斧頭（全材質）
        Items.WOODEN_AXE, Items.STONE_AXE, Items.IRON_AXE,
        Items.GOLDEN_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE,
    
        // 鏟子（全材質）
        Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL,
        Items.GOLDEN_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL,
    
        // 鎬子（全材質）
        Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE,
        Items.GOLDEN_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE,
    
        // 鋤頭（全材質）
        Items.WOODEN_HOE, Items.STONE_HOE, Items.IRON_HOE,
        Items.GOLDEN_HOE, Items.DIAMOND_HOE, Items.NETHERITE_HOE,
    
        // 遠程武器
        Items.BOW, Items.CROSSBOW, Items.TRIDENT,
    
        // 實用工具
        Items.SHIELD,
        Items.FLINT_AND_STEEL,
        Items.FISHING_ROD,
        Items.SHEARS,
        Items.COMPASS,
        Items.CLOCK,
        Items.LEAD,
        Items.SPYGLASS,
        Items.NAME_TAG,
    
        // 裝飾與搞笑道具
        Items.STICK,
        Items.BRUSH,
        Items.TOTEM_OF_UNDYING,
        Items.SADDLE,
        Items.CARROT_ON_A_STICK,
        Items.WARPED_FUNGUS_ON_A_STICK,
    
        // 新版本實用類（如 1.20+ 新增）
        Items.BUNDLE,
        Items.RECOVERY_COMPASS
    };
    

    public static void replaceInventory(ServerPlayer player) {
        // 清除裝備欄
        for (int i = 0; i < 4; i++) {
            player.getInventory().armor.set(i, ItemStack.EMPTY);
        }

        // 清除道具欄前五格
        for (int i = 0; i < 5; i++) {
            player.getInventory().items.set(i, ItemStack.EMPTY);
        }

        // 裝備盔甲
        for (int i = 0; i < 4; i++) {
            ItemStack armor = getRandomArmorForSlot(i);
            // maybeEnchant(armor);
            player.getInventory().armor.set(i, armor);
        }

        // 裝備工具
        for (int i = 0; i < 5; i++) {
            ItemStack tool = getRandomItem(TOOLS);
            // maybeEnchant(tool);
            player.getInventory().items.set(i, tool);
        }

        player.inventoryMenu.broadcastChanges(); // 更新畫面
    }

    private static ItemStack getRandomItem(Item[] pool) {
        return new ItemStack(pool[random.nextInt(pool.length)]);
    }

    // private static void maybeEnchant(ItemStack item) {
    //     if (random.nextFloat() < 0.2f) { // 20% 機率附魔
    //         Map<Enchantment, Integer> enchants = new HashMap<>();

    //         Enchantment unbreaking = BuiltInRegistries.ENCHANTMENTS.get(new ResourceLocation("minecraft", "unbreaking"));
    //         Enchantment knockback = BuiltInRegistries.ENCHANTMENTS.get(new ResourceLocation("minecraft", "knockback"));
    //         Enchantment bindingCurse = BuiltInRegistries.ENCHANTMENTS.get(new ResourceLocation("minecraft", "binding_curse"));

    //         if (unbreaking != null) enchants.put(unbreaking, random.nextInt(3) + 1);
    //         if (knockback != null && random.nextFloat() < 0.5f) enchants.put(knockback, 1);
    //         if (bindingCurse != null && random.nextFloat() < 0.3f) enchants.put(bindingCurse, 1);

    //         EnchantmentHelper.setEnchantments(enchants, item);
    //     }
    // }

    private static ItemStack getRandomArmorForSlot(int slot) {
        Item[] pool;

        switch (slot) {
            case 0: // feet
                pool = new Item[]{Items.LEATHER_BOOTS, Items.IRON_BOOTS, Items.GOLDEN_BOOTS, Items.DIAMOND_BOOTS, Items.NETHERITE_BOOTS};
                break;
            case 1: // legs
                pool = new Item[]{Items.LEATHER_LEGGINGS, Items.IRON_LEGGINGS, Items.GOLDEN_LEGGINGS, Items.DIAMOND_LEGGINGS, Items.NETHERITE_LEGGINGS};
                break;
            case 2: // chest
                pool = new Item[]{Items.LEATHER_CHESTPLATE, Items.IRON_CHESTPLATE, Items.GOLDEN_CHESTPLATE, Items.DIAMOND_CHESTPLATE, Items.NETHERITE_CHESTPLATE};
                break;
            case 3: // head
                pool = new Item[]{
                    Items.LEATHER_HELMET, Items.IRON_HELMET, Items.GOLDEN_HELMET, Items.DIAMOND_HELMET, Items.NETHERITE_HELMET,
                    Items.CARVED_PUMPKIN, Items.PLAYER_HEAD, Items.DRAGON_HEAD
                };
                break;
            default:
                return ItemStack.EMPTY;
        }

        return new ItemStack(pool[random.nextInt(pool.length)]);
    }
}
