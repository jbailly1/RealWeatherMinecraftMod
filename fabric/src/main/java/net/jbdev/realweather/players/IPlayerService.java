package net.jbdev.realweather.players;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public interface IPlayerService {
    List<ServerPlayerEntity> getAll();
    void init();
}
