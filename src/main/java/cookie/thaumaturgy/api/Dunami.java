package cookie.thaumaturgy.api;

import java.util.ArrayList;
import java.util.List;

public class Dunami {
	// Unofficial plural (not actual greek) of Dunamis.
	public static final List<Dunamis> DUNAMI = new ArrayList<>();

	public static final Dunamis AIR = new DunamisBuilder("air")
		.setColor(-1)
		.setTexture(0)
		.build();

	public static final Dunamis EARTH = new DunamisBuilder("earth")
		.setColor(-1)
		.setTexture(0)
		.build();

	public static final Dunamis FIRE = new DunamisBuilder("fire")
		.setColor(-1)
		.setTexture(0)
		.build();

	public static final Dunamis WATER = new DunamisBuilder("water")
		.setColor(-1)
		.setTexture(0)
		.build();

	public static final Dunamis ORDER = new DunamisBuilder("order")
		.setColor(-1)
		.setTexture(0)
		.build();

	public static final Dunamis CHAOS = new DunamisBuilder("chaos")
		.setColor(-1)
		.setTexture(0)
		.build();

	public static void initializeDunami() {

	}
}
