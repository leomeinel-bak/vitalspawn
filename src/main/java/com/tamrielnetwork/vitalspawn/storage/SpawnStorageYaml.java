/*
 * GBooster is a Spigot Plugin providing Global Boosters for Jobs McMMO and Minecraft.
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
 * along with this program. If not, see https://github.com/TamrielNetwork/GBooster/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalspawn.storage;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnStorageYaml extends SpawnStorage {

	private final File spawnFile;
	private final FileConfiguration spawnConf;

	public SpawnStorageYaml() {
		spawnFile = new File(main.getDataFolder(), "spawn.yml");
		spawnConf = YamlConfiguration.loadConfiguration(spawnFile);
		save();
	}

	@Override
	public Location getSpawn() {
		return spawnConf.getLocation("spawn");
	}

	@Override
	public void saveSpawn() {

		clear();

		Location location = main.getVitalSpawnCmd().getLocation(main.getVitalSpawnCmd().getCommandSender());

		spawnConf.set("spawn.world", location.getWorld());
		spawnConf.set("spawn.x", (int) location.getX());
		spawnConf.set("spawn.y", (int) location.getY());
		spawnConf.set("spawn.z", (int) location.getZ());
		spawnConf.set("spawn.pitch", (int) location.getPitch());
		spawnConf.set("spawn.yaw", (int) location.getYaw());

		save();
	}

	@Override
	public void clear() {
		for (String key : spawnConf.getKeys(false)) {
			spawnConf.set(key, null);
		}
	}

	public void save() {
		try {
			spawnConf.save(spawnFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}