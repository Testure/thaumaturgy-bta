package cookie.thaumaturgy;

import cookie.thaumaturgy.api.Aspect;
import cookie.thaumaturgy.block.ThaumBlocks;
import cookie.thaumaturgy.item.ThaumItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;

import java.util.ArrayList;
import java.util.List;


public class Thaumaturgy implements ModInitializer, GameStartEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "thaumaturgy";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final List<Aspect> ASPECTS = new ArrayList<>();

    @Override
    public void onInitialize() {
		ASPECTS.add(new Aspect("air", -1, 0));
		ASPECTS.add(new Aspect("water", -1, 0));
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
		SoundHelper.Client.addSound(MOD_ID, "wand1.wav");
		SoundHelper.Client.addSound(MOD_ID, "wand2.wav");
		SoundHelper.Client.addSound(MOD_ID, "wand3.wav");
	}

	@Override
	public void afterClientStart() {

	}
}
