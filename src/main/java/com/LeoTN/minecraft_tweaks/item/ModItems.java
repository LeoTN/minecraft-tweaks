package com.LeoTN.minecraft_tweaks.item;

import com.LeoTN.minecraft_tweaks.MinecraftTweaks;

import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.render.entity.feature.IronGolemCrackFeatureRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
//import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModItems {
        // Place for ALL NEW ITEMS

        // compactElytra item
        public static final Item compactElytra = registerItem("compact_elytra",
                        new Item(new FabricItemSettings()));
        // superElytra item
        public static final Item superElytra = registerItem("super_elytra",
                        new Item(new FabricItemSettings()));

        // dragonShart item
        public static final Item dragonShard = registerItem("dragon_shard",
                        new Item(new FabricItemSettings()));

        // superElytraChestplate item (has to be added in future because fabric sucks at
        // this point)
        /*
         * public static final Item superElytraChestplate =
         * registerItem("super_elytra_chestplate",
         * new ArmorItem(ModArmorMaterials.dirt, EquipmentSlot.CHEST,
         * new FabricItemSettings()));
         */

        // ---------------------------------------------------------------------------------------------------------
        // Method to register a new item to minecraft
        private static Item registerItem(String name, Item item) {

                return Registry.register(Registries.ITEM, new Identifier(MinecraftTweaks.MOD_ID, name), item);
        }

        // Categories might have to be changed in the future
        // A method to run the addToItemGroup method for every item
        public static void addItemsToItemGroup() {
                addToItemGroup(ModItemGroup.MOD_ITEMS, compactElytra);
                addToItemGroup(ModItemGroup.MOD_ITEMS, dragonShard);
                addToItemGroup(ModItemGroup.MOD_ITEMS, superElytra);
                // addToItemGroup(ModItemGroup.MOD_ITEMS, superElytraArmor);
        }

        // Method to add an item to a desired item group
        private static void addToItemGroup(ItemGroup group, Item item) {
                ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        }

        // A simple log message to keep track of all the items which have been
        // initiliased so far
        public static void registerModItems() {
                MinecraftTweaks.LOGGER.info("Registering Mod Items for " + MinecraftTweaks.MOD_ID);
                addItemsToItemGroup();
        }
}
