/*
 * File: SpawnStorage.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalspawn.storage;

import dev.meinel.leo.vitalspawn.VitalSpawn;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public abstract class SpawnStorage {

  protected final VitalSpawn main = JavaPlugin.getPlugin(VitalSpawn.class);

  public abstract Location loadSpawn();

  public abstract void saveSpawn(@NotNull CommandSender sender);

  protected abstract void clear();
}
