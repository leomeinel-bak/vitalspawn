/*
 * File: PlayerRespawn.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalspawn.listeners;

import dev.meinel.leo.vitalspawn.VitalSpawn;
import dev.meinel.leo.vitalspawn.utils.commands.CmdSpec;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PlayerRespawn implements Listener {

    private final VitalSpawn main = JavaPlugin.getPlugin(VitalSpawn.class);

    @EventHandler
    public void onPlayerRespawn(@NotNull PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (!main.getConfig().getBoolean("spawn-on-respawn") &&
                !player.hasPermission("vitalspawn.onrespawn")) {
            return;
        }
        Location location = main.getSpawnStorage().loadSpawn();
        if (CmdSpec.isInvalidLocation(player, location)) {
            return;
        }
        event.setRespawnLocation(main.getSpawnStorage().loadSpawn());
    }
}
