package net.heyth.scoutplushie.registry;

import net.heyth.scoutplushie.Constants;
import net.heyth.scoutplushie.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

public class ModTabs {
	public static final CreativeModeTab tab = Services.PLATFORM
			.getTabBuilder()
			.icon(() -> new ItemStack(ModBlocks.SCOUT_PLUSHIE_BASE_BLOCK))
			.title(Component.literal(Constants.MOD_NAME))
			.displayItems(((itemDisplayParameters, output) -> {
				output.accept(ModBlocks.SCOUT_PLUSHIE_BASE_BLOCK);
				output.accept(ModBlocks.SCOUT_PLUSHIE_LAMP_BLOCK);
			}))
			.build()
	;

	public static void register(BiConsumer<CreativeModeTab, ResourceLocation> consumer) {
		consumer.accept(tab, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "scout_plushie_tab"));
	}
}
