package com.LeoTN.minecraft_tweaks.item;

import com.LeoTN.minecraft_tweaks.MinecraftTweaks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup MOD_ITEMS;

    public static void registerItemGroups() {
        MOD_ITEMS = FabricItemGroup.builder(new Identifier(MinecraftTweaks.MOD_ID, "mod_items"))
                .displayName(Text.translatable("itemgroup.mod_items"))
                .icon(() -> new ItemStack(ModItems.superElytra)).build();
    }
}
