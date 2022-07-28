/*
 * VitalSpawn is a Spigot Plugin that lets you set a spawn point.
 * Copyright © 2022 Leopold Meinel & contributors
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
 * along with this program. If not, see https://github.com/LeoMeinel/VitalSpawn/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalspawn.listeners;

import com.tamrielnetwork.vitalspawn.VitalSpawn;
import com.tamrielnetwork.vitalspawn.utils.commands.CmdSpec;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class PlayerSpawn
		implements Listener {

	private final VitalSpawn main = JavaPlugin.getPlugin(VitalSpawn.class);

	@EventHandler
	public void onPlayerSpawn(@NotNull PlayerSpawnLocationEvent event) {
		Player player = event.getPlayer();
		if (!main.getConfig()
		         .getBoolean("spawn-on-spawn") && !player.hasPermission("vitalspawn.onspawn")) {
			return;
		}
		Location location = main.getSpawnStorage()
		                        .loadSpawn();
		if (CmdSpec.isInvalidLocation(player, location)) {
			return;
		}
		event.setSpawnLocation(main.getSpawnStorage()
		                           .loadSpawn());
	}
}
