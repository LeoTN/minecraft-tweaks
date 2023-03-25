package com.LeoTN.minecraft_tweaks;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.LeoTN.minecraft_tweaks.item.ModItems;

public class MinecraftTweaks implements ModInitializer {
    public static final String MOD_ID = "mctweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MinecraftTweaks.MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Minecraft Tweaks ready to load !");
        ModItems.registerModItems();
    }
}
