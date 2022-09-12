/*
 * File: SpawnStorageYaml.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalspawn.storage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnStorageYaml extends SpawnStorage {

    private static final String IOEXCEPTION = "VitalSpawn encountered an IOException while executing task";
    private static final String SPAWN = "spawn.";
    private static final String WORLD = ".world";
    private final File spawnFile;
    private final FileConfiguration spawnConf;

    public SpawnStorageYaml() {
        spawnFile = new File(main.getDataFolder(), SPAWN + "yml");
        spawnConf = YamlConfiguration.loadConfiguration(spawnFile);
        save();
    }

    @Override
    public Location loadSpawn() {
        if (spawnConf.getString(SPAWN + WORLD) == null) {
            Bukkit
                    .getLogger()
                    .severe("VitalSpawn cannot find world in " + SPAWN + "yml");
            return null;
        }
        World world = Bukkit.getWorld(
                Objects.requireNonNull(spawnConf.getString(SPAWN + WORLD)));
        int x = spawnConf.getInt(SPAWN + "x");
        int y = spawnConf.getInt(SPAWN + "y");
        int z = spawnConf.getInt(SPAWN + "z");
        int yaw = spawnConf.getInt(SPAWN + "yaw");
        int pitch = spawnConf.getInt(SPAWN + "pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public void saveSpawn(@NotNull CommandSender sender) {
        clear();
        Player senderPlayer = (Player) sender;
        Location location = senderPlayer.getLocation();
        spawnConf.set(SPAWN + WORLD, location.getWorld().getName());
        spawnConf.set(SPAWN + "x", (int) location.getX());
        spawnConf.set(SPAWN + "y", (int) location.getY());
        spawnConf.set(SPAWN + "z", (int) location.getZ());
        spawnConf.set(SPAWN + "yaw", (int) location.getYaw());
        spawnConf.set(SPAWN + "pitch", (int) location.getPitch());
        save();
    }

    @Override
    protected void clear() {
        for (String key : spawnConf.getKeys(false)) {
            spawnConf.set(key, null);
        }
    }

    private void save() {
        try {
            spawnConf.save(spawnFile);
        } catch (IOException ignored) {
            Bukkit.getLogger().info(IOEXCEPTION);
        }
    }
}
