/*
 * VitalSpawn is a Spigot Plugin that lets you set a spawn point.
 * Copyright © 2022 Leopold Meinel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalSpawn/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalspawn.listeners;

import com.tamrielnetwork.vitalspawn.VitalSpawn;
import com.tamrielnetwork.vitalspawn.utils.Chat;
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
		if (!main.getConfig().getBoolean("spawn-on-respawn") && player.hasPermission("vitalspawn.onrespawn")) {
			return;
		}

		Location location = main.getSpawnStorage().getSpawn();
		if (location == null) {
			Chat.sendMessage(player, "no-spawn");
			return;
		}
		event.setRespawnLocation(main.getSpawnStorage().getSpawn());
	}
}
