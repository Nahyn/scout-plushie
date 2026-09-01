package net.heyth.scoutplushie;

import net.heyth.scoutplushie.block.ModBlocks;
import net.heyth.scoutplushie.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ScoutPlushie {

    public static IEventBus eventBus;

    public ScoutPlushie(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
    }
}