package cookie.thaumaturgy;

import cookie.thaumaturgy.block.ThaumBlocks;
import cookie.thaumaturgy.item.ThaumItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;


public class Thaumaturgy implements ModInitializer, GameStartEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "thaumaturgy";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Thaumaturgy has been initialized.");
    }

	@Override
	public void beforeGameStart() {
		ThaumItems.initializeItems();
		ThaumBlocks.initializeTileEntities();
		ThaumBlocks.initializeBlocks();
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void beforeClientStart() {
		SoundHelper.Client.addSound(MOD_ID, "wand1.wav");
		SoundHelper.Client.addSound(MOD_ID, "wand2.wav");
		SoundHelper.Client.addSound(MOD_ID, "wand3.wav");
	}

	@Override
	public void afterClientStart() {

	}
}
