package com.piscopancer.superkontik.items;

import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.checkerframework.common.returnsreceiver.qual.This;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.piscopancer.superkontik.superkontikMod.MOD_ID;

public class SuperKontikItem extends Item {
	
	public SuperKontikItem(Properties properties) {
		super(properties.durability(4)
			.food(new FoodProperties.Builder()
				.nutrition(4)
				.saturationMod(4)
				.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 1)
				.build()));
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		pTooltipComponents.add(Component.translatable("item." + MOD_ID + ".super_kontik_info").withStyle(ChatFormatting.AQUA));
		
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		stack.hurtAndBreak(1, entity, (p) -> {});
		
		Player player = (Player)entity;
		player.getFoodData().eat(stack.getItem(), stack, player);
		player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
		level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
		
		player.gameEvent(GameEvent.EAT);
		return stack;
	}
}
