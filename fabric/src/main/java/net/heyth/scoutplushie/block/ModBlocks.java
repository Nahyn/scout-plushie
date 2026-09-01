package net.heyth.scoutplushie.block;

import net.heyth.scoutplushie.Constants;
import net.heyth.scoutplushie.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ModBlocks {
	public static final Block BASE_SCOUT_PLUSHIE_BLOCK = registerBlock(
		"scout_plushie_base",
			new BasePlushieBlock(BasePlushieBlock.PROPERTIES)
	);


	private static Block registerBlock(String name, Block block){
		Block toReturn = Registry.register(
			BuiltInRegistries.BLOCK,
			ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name),
			block
		);
		registerBlockItem(toReturn);

		return toReturn;
	}

	private static void registerBlockItem(Block block) {
		Registry.register(
				BuiltInRegistries.ITEM,
				BuiltInRegistries.BLOCK.getKey(block),
				new BlockItem(block, new Item.Properties())
		);
	}

}
