package net.heyth.scoutplushie;

import net.heyth.scoutplushie.block.ModBlocks;
import net.heyth.scoutplushie.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ScoutPlushie {

    public static IEventBus eventBus;

    public ScoutPlushie(FMLJavaModLoadingContext context) {
        eventBus = context.getModEventBus();

        ModBlocks.register(eventBus);
        ModItems.register(eventBus);

        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
    }
}