package net.heyth.scoutplushie.item;

import net.heyth.scoutplushie.Constants;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Constants.MOD_ID);

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
