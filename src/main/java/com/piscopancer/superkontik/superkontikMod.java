package com.piscopancer.superkontik;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** TODO LIST <br>
 * super kontik propaganda hat (2 speakers, blue color, playes advertisement soundtrack)
 *	hey
 */
@Mod(superkontikMod.MOD_ID)
public class superkontikMod {
	public static final String MOD_ID = "superkontik";
	
	//<editor-fold desc="ITEMS">
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
	public static final RegistryObject<Item> SUPER_KONTIK_ITEM = ITEMS.register("super_kontik", () -> new Item(new Item.Properties()));
	
	//</editor-fold>
	
	public superkontikMod() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(modEventBus);
		
		modEventBus.addListener(this::useCustomCreativeTab);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void useCustomCreativeTab(CreativeModeTabEvent.Register e) {
		e.registerCreativeModeTab(new ResourceLocation(MOD_ID, "mod_tab"), builder -> {
			builder.title(Component.translatable("item_group." + MOD_ID + ".mod_tab"))
							.icon(() -> new ItemStack(SUPER_KONTIK_ITEM.get()))
							.displayItems((flags, populator, hasPermissions) -> {
								populator.accept(SUPER_KONTIK_ITEM.get());
							});
		});
	}
}
