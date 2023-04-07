package com.LeoTN.minecraft_tweaks.client;

import com.LeoTN.minecraft_tweaks.client.renderer.SuperElytraFeatureRenderer;
import com.LeoTN.minecraft_tweaks.client.renderer.model.ElytraModel;
import com.LeoTN.minecraft_tweaks.item.ModItems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SuperElytra implements ClientModInitializer {
    public static String MOD_ID = "customizableelytra";

    @Override
    public void onInitializeClient() {
        // registerModelPredicates();
        registerFeatureRendererEventHandlers();
        registerFeatureRenderers();
    }

    /*
     * private void registerModelPredicates() {
     * FabricModelPredicateProviderRegistry.register(ModItems.superElytra,
     * new Identifier(MOD_ID, "broken_elytra"),
     * ((stack, world, entity, seed) -> stack.getItem() == ModItems.superElytra
     * && CustomizableElytraItem.isUsable(stack) ? 0.0F : 1.0F));
     * }
     */

    private void registerFeatureRendererEventHandlers() {
        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register((entity) -> {
            ItemStack stack = SuperElytraFeatureRenderer.tryFindElytra(entity);
            return stack.getItem() != ModItems.superElytra;
        });
    }

    private void registerFeatureRenderers() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT
                .register((entityType, entityRenderer, registrationHelper, context) -> {
                    if (entityRenderer instanceof ArmorStandEntityRenderer
                            || entityRenderer instanceof PlayerEntityRenderer) {
                        registrationHelper.register(
                                new SuperElytraFeatureRenderer<>(entityRenderer, context.getModelLoader()));
                    }
                });
    }
}
