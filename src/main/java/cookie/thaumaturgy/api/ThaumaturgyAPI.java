package cookie.thaumaturgy.api;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ThaumaturgyAPI {
	public static final Map<Dunamis, String> PARTICLE_MAP = new HashMap<>();

	static {
		PARTICLE_MAP.put(Dunami.AIR, "bubble");
		PARTICLE_MAP.put(Dunami.WATER, "splash");
		PARTICLE_MAP.put(Dunami.FIRE, "flame");
		PARTICLE_MAP.put(Dunami.EARTH, "explode");
		PARTICLE_MAP.put(Dunami.ORDER, "bigsmoke");
		PARTICLE_MAP.put(Dunami.CHAOS, "portal");
	}

	public static String getParticleForDunamis(Dunamis dunamis) {
		return PARTICLE_MAP.get(dunamis) != null ? PARTICLE_MAP.get(dunamis) : "portal";
	}

	public static ItemDunamisContainer getItemDunamisContainer(ItemStack stack) {
		if (!itemStackContainsDunamis(stack)) throw new NullPointerException("Attempt to get dunamis container for item that doesn't have one!");
		return new ItemDunamisContainer(stack);
	}

	public static boolean itemStackContainsDunamis(ItemStack stack, boolean checkAmount) {
		CompoundTag tag = stack.getData();
		if (tag.containsKey("dunami")) {
			if (checkAmount) return !getItemDunamisContainer(stack).isEmpty();
			return true;
		}
		return false;
	}

	public static boolean itemStackContainsDunamis(ItemStack stack) {
		return itemStackContainsDunamis(stack, false);
	}
}
