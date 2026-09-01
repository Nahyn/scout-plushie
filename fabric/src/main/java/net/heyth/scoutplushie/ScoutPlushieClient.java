package net.heyth.scoutplushie;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.heyth.scoutplushie.block.ModBlocks;
import net.minecraft.client.renderer.RenderType;

public class ScoutPlushieClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BASE_SCOUT_PLUSHIE_BLOCK, RenderType.cutout());
	}
}
