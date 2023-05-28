package com.songoda.epiclevels.listeners;

import com.songoda.epiclevels.EpicLevels;
import com.songoda.epiclevels.players.EPlayer;
import me.gatogamer.midnight.spigot.event.data.MidnightRequestDataEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * ask first, and give me the credits.
 * Arigato! n.n
 */
public class DataListeners implements Listener {
    private final EpicLevels epicLevels;

    public DataListeners(EpicLevels epicLevels) {
        this.epicLevels = epicLevels;
    }

    @EventHandler
    public void onData(MidnightRequestDataEvent dataEvent) {
        dataEvent.load(event -> {
            EPlayer ePlayer = epicLevels.getDataManager().getPlayerOrCreateSync(event.getUniqueId());
            ePlayer.setSaved(false);
            epicLevels.getPlayerManager().addPlayer(ePlayer);
        }, "epiclevels data load");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        EPlayer ePlayer = epicLevels.getPlayerManager().getPlayer(player);
        epicLevels.getPlayerManager().resetPlayer(player.getUniqueId());
        epicLevels.getDataManager().updatePlayer(ePlayer);
        ePlayer.setSaved(true);
    }
}