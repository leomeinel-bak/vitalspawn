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
import com.tamrielnetwork.vitalspawn.utils.Chat;
import com.tamrielnetwork.vitalspawn.utils.commands.Cmd;
import com.tamrielnetwork.vitalspawn.utils.commands.CmdSpec;
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

		if (Cmd.isArgsLengthNotEqualTo(sender, args, 1)) {
			return true;
		}

		switch (args[0]) {
			case "spawn" -> doSpawn(sender);
			case "setspawn" -> setSpawn(sender);
			default -> Chat.sendMessage(sender, "invalid-option");
		}
		return true;
	}

	private void doSpawn(@NotNull CommandSender sender) {
		Player senderPlayer = (Player) sender;
		Location location = main.getSpawnStorage().getSpawn();

		if (CmdSpec.isInvalidCmd(sender, "vitalspawn.spawn", location)) {
			return;
		}

		senderPlayer.teleport(location);
		Chat.sendMessage(sender, "spawn-tp");

	}

	private void setSpawn(@NotNull CommandSender sender) {

		if (CmdSpec.isInvalidCmd(sender, "vitalspawn.setspawn")) {
			return;
		}

		main.getSpawnStorage().saveSpawn(sender);
		Chat.sendMessage(sender, "spawn-set");

	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
		@Nullable List<String> tabComplete = new ArrayList<>();
		if (args.length != 1) {
			return null;
		}
		if (sender.hasPermission("vitalspawn.spawn")) {
			tabComplete.add("spawn");
		}
		if (sender.hasPermission("vitalspawn.setspawn")) {
			tabComplete.add("setspawn");
		}
		return tabComplete;
	}
}
