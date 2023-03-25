package com.LeoTN.minecraft_tweaks.item;

import com.LeoTN.minecraft_tweaks.MinecraftTweaks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
//import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModItems {
    // superElytra Item
    public static final Item superElytra = registerItem("super_elytra",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {

        return Registry.register(Registries.ITEM, new Identifier(MinecraftTweaks.MOD_ID, name), item);
    }

    // Category might has to be changed in the future
    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.FUNCTIONAL, superElytra);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems() {
        MinecraftTweaks.LOGGER.info("Registering Mod Items for " + MinecraftTweaks.MOD_ID);
        addItemsToItemGroup();
    }
}
