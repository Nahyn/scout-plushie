package net.heyth.scoutplushie.registry;

import net.heyth.scoutplushie.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

public class ModItems {
	public static final Item SCOUT_PLUSHIE_BASE_BLOCK = new BlockItem(ModBlocks.SCOUT_PLUSHIE_BASE_BLOCK, new Item.Properties());
	//public static final Item SCOUT_PLUSHIE_LAMP_BLOCK = new BlockItem(ModBlocks.SCOUT_PLUSHIE_LAMP_BLOCK, new Item.Properties());

	static {
		Constants.LOG.info("DEFINED ITEMS");
	}
	public static void register(BiConsumer<Item, ResourceLocation> consumer) {
		Constants.LOG.info("REGISTERING ITEMS");

		consumer.accept(
			SCOUT_PLUSHIE_BASE_BLOCK,
			ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "scout_plushie_base")
		);
		/*
		consumer.accept(
			SCOUT_PLUSHIE_LAMP_BLOCK,
			ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "scout_plushie_lamp")
		);
		 */
	}
}
