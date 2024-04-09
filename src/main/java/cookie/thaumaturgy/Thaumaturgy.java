package cookie.thaumaturgy;

import cookie.thaumaturgy.api.Dunamis;
import cookie.thaumaturgy.api.DunamisBuilder;
import cookie.thaumaturgy.block.ThaumBlocks;
import cookie.thaumaturgy.item.ThaumItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;

import java.util.ArrayList;
import java.util.List;


public class Thaumaturgy implements ModInitializer, GameStartEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "thaumaturgy";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final List<Dunamis> DUNAMI = new ArrayList<>(); // Unofficial plural (not actual greek) of Dunamis.

    @Override
    public void onInitialize() {
		new DunamisBuilder("air")
			.setColor(-1)
			.setTexture(0)
			.build();

		new DunamisBuilder("earth")
			.setColor(-1)
			.setTexture(0)
			.build();

		new DunamisBuilder("fire")
			.setColor(-1)
			.setTexture(0)
			.build();

		new DunamisBuilder("water")
			.setColor(-1)
			.setTexture(0)
			.build();

		new DunamisBuilder("order")
			.setColor(-1)
			.setTexture(0)
			.build();

		new DunamisBuilder("chaos")
			.setColor(-1)
			.setTexture(0)
			.build();

		DUNAMI.forEach(System.out::println);

        LOGGER.info("Thaumaturgy has been initialized.");
    }

	@Override
	public void beforeGameStart() {
		ThaumItems.initializeItems();
		ThaumBlocks.initializeBlocks();
		ThaumBlocks.initializeTileEntities();
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void beforeClientStart() {

	}

	@Override
	public void afterClientStart() {

	}
}
