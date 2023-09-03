package net.jbdev.realweather;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.jbdev.realweather.events.ServerEndWorldTickHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealWeatherMod implements ModInitializer {
	public static final String MOD_ID = "real-weather-effects";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ServiceManager serviceManager = new ServiceManager(LOGGER);
    public static final ServerEndWorldTickHandler endWorldTickHandler = new ServerEndWorldTickHandler(serviceManager.playerService, serviceManager.weatherService, LOGGER);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Thanks to use the real weather effects mod");
        serviceManager.init();

        ServerTickEvents.END_WORLD_TICK.register(endWorldTickHandler::handleWorldTick);
	}
}