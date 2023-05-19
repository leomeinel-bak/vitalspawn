/*
 * File: VitalSpawn.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalspawn;

import dev.meinel.leo.vitalspawn.commands.VitalSetSpawnCmd;
import dev.meinel.leo.vitalspawn.commands.VitalSpawnCmd;
import dev.meinel.leo.vitalspawn.files.Messages;
import dev.meinel.leo.vitalspawn.listeners.PlayerRespawn;
import dev.meinel.leo.vitalspawn.listeners.PlayerSpawn;
import dev.meinel.leo.vitalspawn.storage.SpawnStorage;
import dev.meinel.leo.vitalspawn.storage.SpawnStorageSql;
import dev.meinel.leo.vitalspawn.storage.SpawnStorageYaml;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VitalSpawn extends JavaPlugin {

    private SpawnStorage spawnStorage;
    private Messages messages;

    @Override
    public void onEnable() {
        registerListeners();
        registerCommands();
        saveDefaultConfig();
        setupStorage();
        messages = new Messages();
        Bukkit
                .getLogger()
                .info("VitalSpawn v" + this.getPluginMeta().getVersion() + " enabled");
        Bukkit.getLogger().info("Copyright (C) 2022 Leopold Meinel");
        Bukkit.getLogger().info("This program comes with ABSOLUTELY NO WARRANTY!");
        Bukkit
                .getLogger()
                .info(
                        "This is free software, and you are welcome to redistribute it under certain conditions.");
        Bukkit
                .getLogger()
                .info(
                        "See https://www.gnu.org/licenses/gpl-3.0-standalone.html for more details.");
    }

    @Override
    public void onDisable() {
        Bukkit
                .getLogger()
                .info("VitalSpawn v" + this.getPluginMeta().getVersion() + " disabled");
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

    private void registerCommands() {
        Objects
                .requireNonNull(getCommand("spawn"))
                .setExecutor(new VitalSpawnCmd());
        Objects
                .requireNonNull(getCommand("setspawn"))
                .setExecutor(new VitalSetSpawnCmd());
    }

    public Messages getMessages() {
        return messages;
    }

    public SpawnStorage getSpawnStorage() {
        return spawnStorage;
    }
}
