package cookie.thaumaturgy;

import cookie.thaumaturgy.api.Dunami;
import cookie.thaumaturgy.api.Dunamis;
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

    @Override
    public void onInitialize() {
		Dunami.initializeDunami();

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
