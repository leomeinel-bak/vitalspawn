/*
 * File: VitalSpawnCmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalspawn.commands;

import dev.meinel.leo.vitalspawn.VitalSpawn;
import dev.meinel.leo.vitalspawn.utils.commands.Cmd;
import dev.meinel.leo.vitalspawn.utils.commands.CmdSpec;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class VitalSpawnCmd implements CommandExecutor {

    private final VitalSpawn main = JavaPlugin.getPlugin(VitalSpawn.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String label, @NotNull String[] args) {
        if (!Cmd.isArgsLengthEqualTo(sender, args, 0)) {
            return false;
        }
        doSpawn(sender);
        return true;
    }

    private void doSpawn(@NotNull CommandSender sender) {
        Location location = main.getSpawnStorage().loadSpawn();
        if (CmdSpec.isInvalidCmd(sender, "vitalspawn.spawn", location)) {
            return;
        }
        CmdSpec.doDelay(sender, location);
    }
}
