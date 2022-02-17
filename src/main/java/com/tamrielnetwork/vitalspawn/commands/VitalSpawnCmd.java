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

package com.tamrielnetwork.vitalspawn.commands;

import com.tamrielnetwork.vitalspawn.VitalSpawn;
import com.tamrielnetwork.vitalspawn.utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VitalSpawnCmd implements TabExecutor {

	private final VitalSpawn main = JavaPlugin.getPlugin(VitalSpawn.class);

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		// Check args length
		if (args.length == 0) {
			Utils.sendMessage(sender, "no-args");
			return true;
		}
		// Check arg 0
		switch (args[0]) {
			case "setspawn" -> setSpawn(sender, args);
			case "spawn" -> teleportSpawn(sender, args);
			default -> Utils.sendMessage(sender, "invalid-option");
		}
		return true;
	}

	private void setSpawn(CommandSender sender, String[] args) {
		if (args.length > 1) {
			Utils.sendMessage(sender, "invalid-option");
			return;
		}
		// Check if command sender is a player
		if (!(sender instanceof Player)) {
			Utils.sendMessage(sender, "player-only");
			return;
		}
		// Check perms
		if (!sender.hasPermission("vitalspawn.setspawn")) {
			Utils.sendMessage(sender, "no-perms");
			return;
		}
		main.getSpawnStorage().saveSpawn(sender);

		Utils.sendMessage(sender, "spawn-set");

	}

	private void teleportSpawn(CommandSender sender, String[] args) {
		if (args.length > 1) {
			Utils.sendMessage(sender, "invalid-option");
			return;
		}
		// Check if command sender is a player
		if (!(sender instanceof Player)) {
			Utils.sendMessage(sender, "player-only");
			return;
		}
		// Check perms
		if (!sender.hasPermission("vitalspawn.spawn")) {
			Utils.sendMessage(sender, "no-perms");
			return;
		}

		Location location = main.getSpawnStorage().getSpawn();
		if (location == null) {
			Utils.sendMessage(sender, "no-spawn");
			return;
		}
		((Player) sender).teleport(location);
		Utils.sendMessage(sender, "spawn-tp");

	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
		@Nullable List<String> tabComplete = new ArrayList<>();
		if (args.length == 1) {
			if (sender.hasPermission("vitalspawn.spawn")) {
				tabComplete.add("spawn");
			}
			if (sender.hasPermission("vitalspawn.setspawn")) {
				tabComplete.add("setspawn");
			}
		} else {
			tabComplete = null;
		}
		return tabComplete;
	}
}
