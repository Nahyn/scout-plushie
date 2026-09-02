package net.heyth.scoutplushie;

import net.fabricmc.api.ModInitializer;
import net.heyth.scoutplushie.registry.ModBlocks;
import net.heyth.scoutplushie.registry.ModItems;
import net.heyth.scoutplushie.registry.ModTabs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ScoutPlushie implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();

        bind(BuiltInRegistries.BLOCK, ModBlocks::register);
        bind(BuiltInRegistries.ITEM, ModItems::register);
        bind(BuiltInRegistries.CREATIVE_MODE_TAB, ModTabs::register);
    }

    // Adapted from <a href="https://github.com.VazkiiMods/Botania"></a>
    private static <T> void bind(Registry<T> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        source.accept((t, resourceLocation) -> Registry.register(registry, resourceLocation, t));
    }
}
