package net.heyth.scoutplushie;

import net.heyth.scoutplushie.registry.ModBlocks;
import net.heyth.scoutplushie.registry.ModItems;
import net.heyth.scoutplushie.registry.ModTabs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class ScoutPlushie {
    public static IEventBus eventBus;

    public ScoutPlushie(FMLJavaModLoadingContext context) {
        eventBus = context.getModEventBus();

        bind(Registries.BLOCK, ModBlocks::register);
        bind(Registries.ITEM, ModItems::register);
        bind(Registries.CREATIVE_MODE_TAB, ModTabs::register);

        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
    }

    // Adapted from <a href="https://github.com.VazkiiMods/Botania"></a>
    private static <T> void bind(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        eventBus.addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }
}