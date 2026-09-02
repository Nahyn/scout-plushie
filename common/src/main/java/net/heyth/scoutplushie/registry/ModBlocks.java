package net.heyth.scoutplushie.registry;

import net.heyth.scoutplushie.Constants;
import net.heyth.scoutplushie.block.BasePlushieBlock;
import net.heyth.scoutplushie.block.LightPlushieBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.BiConsumer;

public class ModBlocks {
	public static final Block SCOUT_PLUSHIE_BASE_BLOCK = new BasePlushieBlock(BlockBehaviour.Properties.of());
	//public static final Block SCOUT_PLUSHIE_LAMP_BLOCK = new LightPlushieBlock(BlockBehaviour.Properties.of());

	static {
		Constants.LOG.info("DEFINED BLOCKS");
	}

	public static void register(BiConsumer<Block, ResourceLocation> consumer) {
		Constants.LOG.info("REGISTERING BLOCKS");

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
