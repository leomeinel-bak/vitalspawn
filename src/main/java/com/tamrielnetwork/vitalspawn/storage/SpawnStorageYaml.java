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
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalSpawn/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalspawn.storage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SpawnStorageYaml extends SpawnStorage {

	private final File spawnFile;
	private final FileConfiguration spawnConf;

	public SpawnStorageYaml() {

		spawnFile = new File(main.getDataFolder(), "spawn.yml");
		spawnConf = YamlConfiguration.loadConfiguration(spawnFile);
		save();
	}

	@Override
	public Location loadSpawn() {

		if (spawnConf.getString("spawn.world") == null) {
			Bukkit.getLogger().severe("VitalSpawn cannot find world in spawn.yml");
			return null;
		}
		World world = Bukkit.getWorld(Objects.requireNonNull(spawnConf.getString("spawn.world")));
		int x = spawnConf.getInt("spawn.x");
		int y = spawnConf.getInt("spawn.y");
		int z = spawnConf.getInt("spawn.z");
		int yaw = spawnConf.getInt("spawn.yaw");
		int pitch = spawnConf.getInt("spawn.pitch");

		return new Location(world, x, y, z, yaw, pitch);
	}

	@Override
	public void saveSpawn(@NotNull CommandSender sender) {

		clear();
		Player senderPlayer = (Player) sender;

		Location location = senderPlayer.getLocation();

		spawnConf.set("spawn.world", location.getWorld().getName());
		spawnConf.set("spawn.x", (int) location.getX());
		spawnConf.set("spawn.y", (int) location.getY());
		spawnConf.set("spawn.z", (int) location.getZ());
		spawnConf.set("spawn.yaw", (int) location.getYaw());
		spawnConf.set("spawn.pitch", (int) location.getPitch());

		save();
	}

	@Override
	public void clear() {

		for (String key : spawnConf.getKeys(false)) {
			spawnConf.set(key, null);
		}
		save();
	}

	public void save() {

		try {
			spawnConf.save(spawnFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}