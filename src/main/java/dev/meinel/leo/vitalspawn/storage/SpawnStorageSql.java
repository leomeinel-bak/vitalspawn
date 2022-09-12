/*
 * File: SpawnStorageSql.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalspawn.storage;

import dev.meinel.leo.vitalspawn.storage.mysql.SqlManager;
import dev.meinel.leo.vitalspawn.utils.storage.Sql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnStorageSql extends SpawnStorage {

    private static final String SQLEXCEPTION = "VitalSpawn encountered an SQLException while executing task";

    public SpawnStorageSql() {
        new SqlManager();
    }

    @Override
    public Location loadSpawn() {
        World world = null;
        int x = 0;
        int y = 0;
        int z = 0;
        int yaw = 0;
        int pitch = 0;
        try (
                PreparedStatement selectStatement = SqlManager
                        .getConnection()
                        .prepareStatement("SELECT * FROM " + Sql.getPrefix() + "Spawn")) {
            try (ResultSet rs = selectStatement.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString(1) == null) {
                        Bukkit
                                .getLogger()
                                .severe("VitalSpawn cannot find world in database");
                        continue;
                    }
                    world = Bukkit.getWorld(Objects.requireNonNull(rs.getString(1)));
                    x = rs.getInt(2);
                    y = rs.getInt(3);
                    z = rs.getInt(4);
                    yaw = rs.getInt(5);
                    pitch = rs.getInt(6);
                }
            }
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
            return null;
        }
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public void saveSpawn(@NotNull CommandSender sender) {
        clear();
        Player senderPlayer = (Player) sender;
        Location location = senderPlayer.getLocation();
        try (
                PreparedStatement insertStatement = SqlManager
                        .getConnection()
                        .prepareStatement(
                                "INSERT INTO " +
                                        Sql.getPrefix() +
                                        "Spawn (`World`, `X`, `Y`, `Z`, `Yaw`, `Pitch`) VALUES (?, ?, ?, ?, ?, ?)")) {
            insertStatement.setString(1, location.getWorld().getName());
            insertStatement.setInt(2, (int) location.getX());
            insertStatement.setInt(3, (int) location.getY());
            insertStatement.setInt(4, (int) location.getZ());
            insertStatement.setInt(5, (int) location.getYaw());
            insertStatement.setInt(6, (int) location.getPitch());
            insertStatement.executeUpdate();
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
        }
    }

    @Override
    protected void clear() {
        try (
                PreparedStatement truncateStatement = SqlManager
                        .getConnection()
                        .prepareStatement("TRUNCATE TABLE " + Sql.getPrefix() + "Spawn")) {
            truncateStatement.executeUpdate();
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
        }
    }
}
