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

package com.tamrielnetwork.vitalspawn;

import com.tamrielnetwork.vitalspawn.commands.VitalSpawnCmd;
import com.tamrielnetwork.vitalspawn.files.Messages;
import com.tamrielnetwork.vitalspawn.listeners.PlayerSpawn;
import com.tamrielnetwork.vitalspawn.listeners.PlayerRespawn;
import com.tamrielnetwork.vitalspawn.storage.SpawnStorage;
import com.tamrielnetwork.vitalspawn.storage.SpawnStorageSql;
import com.tamrielnetwork.vitalspawn.storage.SpawnStorageYaml;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VitalSpawn extends JavaPlugin {

	private SpawnStorage spawnStorage;
	private Messages messages;
	private String prefix;

	@Override
	public void onEnable() {

		registerListeners();

		Objects.requireNonNull(getCommand("vitalspawn")).setExecutor(new VitalSpawnCmd());

		saveDefaultConfig();

		setupStorage();

		messages = new Messages();
		prefix = getConfig().getString("mysql.prefix");

		Bukkit.getLogger().info("VitalSpawn v" + this.getDescription().getVersion() + " enabled");
		Bukkit.getLogger().info("Copyright (C) 2022 Leopold Meinel");
		Bukkit.getLogger().info("This program comes with ABSOLUTELY NO WARRANTY!");
		Bukkit.getLogger().info("This is free software, and you are welcome to redistribute it under certain conditions.");
		Bukkit.getLogger().info("See https://github.com/TamrielNetwork/VitalSpawn/blob/main/LICENSE for more details.");
	}

	@Override
	public void onDisable() {
		Bukkit.getLogger().info("VitalSpawn v" + this.getDescription().getVersion() + " disabled");
	}

	private void setupStorage() {
		String storageSystem = getConfig().getString("storage-system");

		if (Objects.requireNonNull(storageSystem).equalsIgnoreCase("mysql")) {
			this.spawnStorage = new SpawnStorageSql();
		} else {
			this.spawnStorage = new SpawnStorageYaml();
		}
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerSpawn(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
	}

	public Messages getMessages() {
		return messages;
	}

	public String getPrefix() {
		return prefix;
	}

	public SpawnStorage getSpawnStorage() {
		return spawnStorage;
	}

}

