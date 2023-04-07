package com.LeoTN.minecraft_tweaks.client.renderer;

import com.LeoTN.minecraft_tweaks.MinecraftTweaks;
import com.LeoTN.minecraft_tweaks.client.renderer.model.ElytraModel;
import com.LeoTN.minecraft_tweaks.item.ModItems;
import com.LeoTN.minecraft_tweaks.item.SuperElytra;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.List;

public class SuperElytraFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>>
        extends ElytraFeatureRenderer<T, M> {
    private final ElytraEntityModel<T> leftWing;
    private final ElytraEntityModel<T> rightWing;

    public SuperElytraFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext,
            EntityModelLoader loader) {
        super(featureRendererContext, loader);
        this.leftWing = new ElytraModel<>(loader.getModelPart(EntityModelLayers.ELYTRA), false);
        this.rightWing = new ElytraModel<>(loader.getModelPart(EntityModelLayers.ELYTRA), true);
    }

    public static boolean shouldRender(ItemStack stack, LivingEntity entity) {
        return stack.getItem() == ModItems.superElytra;
    }

    public static ItemStack getColytraSubItem(ItemStack stack) {
        NbtCompound colytraChestTag = stack.getSubNbt("colytra:ElytraUpgrade");
        if (colytraChestTag != null) {
            ItemStack elytraStack = ItemStack.fromNbt(colytraChestTag);
            if (elytraStack.getItem() == ModItems.superElytra) {
                return elytraStack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack tryFindElytra(LivingEntity entity) {
        ItemStack elytra = entity.getEquippedStack(EquipmentSlot.CHEST);
        if (shouldRender(elytra, entity)) {
            return elytra;
        }
        if (com.LeoTN.minecraft_tweaks.MinecraftTweaks.caleusLoaded) {
            elytra = getColytraSubItem(elytra);
            if (elytra != ItemStack.EMPTY) {
                return elytra;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light,
            T livingEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack elytra = tryFindElytra(livingEntity);
        if (elytra != ItemStack.EMPTY) {
            matrixStack.push();
            matrixStack.translate(0.0D, 0.0D, 0.125D);
        } else {
            List<ElytraEntityModel<T>> models = ImmutableList.of(leftWing, rightWing);
            for (ElytraEntityModel<T> model : models) {
                this.getContextModel().copyStateTo(model);
            }
            NbtCompound wingInfo = elytra.getSubNbt("WingInfo");
            Identifier leftWingTexture = getTextureWithCape(livingEntity, wingInfo.getCompound("left"), false);
            Identifier rightWingTexture = getTextureWithCape(livingEntity, wingInfo.getCompound("right"), false);

        }
        matrixStack.pop();
    }

    private Identifier getTextureWithCape(T livingEntity, NbtCompound customizationTag, boolean capeHidden) {
        Identifier elytraTexture = null;
        if (!capeHidden && livingEntity instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity) livingEntity;
            if (abstractclientplayerentity.canRenderElytraTexture()
                    && abstractclientplayerentity.getElytraTexture() != null) {
                elytraTexture = abstractclientplayerentity.getElytraTexture();
            } else if (abstractclientplayerentity.canRenderCapeTexture()
                    && abstractclientplayerentity.getCapeTexture() != null
                    && abstractclientplayerentity.isPartVisible(PlayerModelPart.CAPE)) {
                elytraTexture = abstractclientplayerentity.getCapeTexture();
            }
        }
        return elytraTexture;
    }
}
