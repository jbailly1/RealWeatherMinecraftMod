package net.jbdev.realweather;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mojang.brigadier.arguments.BoolArgumentType;

import static net.minecraft.server.command.CommandManager.*;

public class RealWeatherMod implements ModInitializer {
	public static final String MOD_ID = "real-weather-effects";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final IRealWeatherEffects weatherEffects = new RealWeatherEffectsJson(new RealWeatherEffects());

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Thanks to use the real weather effects mod");
        //TODO load config json file.
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) ->
			dispatcher.register(
                literal(MOD_ID)
                    .then(literal("enable")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(
                            argument("enabled", BoolArgumentType.bool())
                                .executes(context -> {
                                    weatherEffects.enable(BoolArgumentType.getBool(context, "enabled"));
                                    context.getSource().sendMessage(Text.literal(String.format("Real Weather mod %s effect", weatherEffects.isEnabled() ? "enable" : "disabled")));
                                    return 1;
                                })
                        )
                    )
                    .then(literal("effect.wind")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(
                            argument("enabled", BoolArgumentType.bool())
                                .executes(context -> {
                                    weatherEffects.enableWind(BoolArgumentType.getBool(context, "enabled"));
                                    context.getSource().sendMessage(Text.literal(String.format("Real Weather wind effect %s", weatherEffects.isWindEnabled() ? "enable" : "disabled")));
                                    return 1;
                                })
                        )
                    )
                    .then(literal("effect.temp")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(
                            argument("enabled", BoolArgumentType.bool())
                                .executes(context -> {
                                    weatherEffects.enableTemperature(BoolArgumentType.getBool(context, "enabled"));
                                    context.getSource().sendMessage(Text.literal(String.format("Real Weather temperature effect %s", weatherEffects.isTemperatureEnabled() ? "enable" : "disabled")));
                                    return 1;
                                })
                        )
                    )
                    .then(literal("effect.all")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(
                            argument("enabled", BoolArgumentType.bool())
                                .executes(context -> {
                                    weatherEffects.enableAllEffects(BoolArgumentType.getBool(context, "enabled"));
                                    context.getSource().sendMessage(Text.literal(weatherEffects.info()));
                                    return 1;
                                })
                        )
                    )
                    .executes(context -> {
                        context.getSource().sendMessage(Text.literal(weatherEffects.info()));
                        return 1;
                    })
                )
		    )
        );
	}
}