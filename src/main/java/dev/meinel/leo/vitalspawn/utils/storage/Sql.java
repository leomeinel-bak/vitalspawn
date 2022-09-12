/*
 * File: Sql.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalspawn.utils.storage;

import dev.meinel.leo.vitalspawn.VitalSpawn;
import org.bukkit.plugin.java.JavaPlugin;

public class Sql {

  private static final VitalSpawn main = JavaPlugin.getPlugin(VitalSpawn.class);

  private Sql() {
    throw new IllegalStateException("Utility class");
  }

  public static String getPrefix() {
    return main.getConfig().getString("mysql.prefix");
  }
}
