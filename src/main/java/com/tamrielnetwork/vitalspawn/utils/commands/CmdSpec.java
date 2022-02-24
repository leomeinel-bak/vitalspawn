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

package com.tamrielnetwork.vitalspawn.utils.commands;

import com.tamrielnetwork.vitalspawn.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CmdSpec {

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String perm, Location location) {

		if (Cmd.isInvalidSender(sender)) {
			return true;
		}
		if (Cmd.isNotPermitted(sender, perm)) {
			return true;
		}

		return isInvalidLocation(sender, location);
	}

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String perm) {

		return Cmd.isInvalidSender(sender) || Cmd.isNotPermitted(sender, perm);
	}

	public static boolean isInvalidLocation(@NotNull CommandSender sender, Location location) {

		if (location == null) {
			Bukkit.getLogger().severe("VitalSpawn cannot find spawnlocation in database");
			Chat.sendMessage(sender, "no-spawn");
			return true;
		}
		if (location.getWorld() == null) {
			Bukkit.getLogger().severe("VitalSpawn cannot find world in database");
			Chat.sendMessage(sender, "no-spawn");
			return true;
		}
		return false;
	}

}
