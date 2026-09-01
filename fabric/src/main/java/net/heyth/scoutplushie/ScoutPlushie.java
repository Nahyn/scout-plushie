package net.heyth.scoutplushie;

import net.fabricmc.api.ModInitializer;
import net.heyth.scoutplushie.block.BasePlushieBlock;
import net.heyth.scoutplushie.block.ModBlocks;
import net.heyth.scoutplushie.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ScoutPlushie implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();

    }
}
