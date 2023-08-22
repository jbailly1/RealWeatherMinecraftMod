package net.jbdev.realweather.players;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerService implements IPlayerService {

    private final Map<UUID, ServerPlayerEntity> players = new HashMap<>();

    @Override
    public List<ServerPlayerEntity> getAll() {
        return players.values().stream().toList();
    }

    @Override
    public void init() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (!this.players.containsKey(handler.getPlayer().getUuid())) {
                this.players.put(handler.getPlayer().getUuid(), handler.getPlayer());
            }
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            this.players.remove(handler.getPlayer().getUuid());
        });
    }
}
