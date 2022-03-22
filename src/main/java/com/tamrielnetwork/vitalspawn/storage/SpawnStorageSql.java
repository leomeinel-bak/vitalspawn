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

import com.tamrielnetwork.vitalspawn.storage.mysql.SqlManager;
import com.tamrielnetwork.vitalspawn.utils.storage.Sql;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SpawnStorageSql
		extends SpawnStorage {

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
		try (PreparedStatement selectStatement = SqlManager.getConnection()
		                                                   .prepareStatement(
				                                                   "SELECT * FROM " + Sql.getPrefix() + "Spawn")) {
			try (ResultSet rs = selectStatement.executeQuery()) {
				while (rs.next()) {
					if (rs.getString(1) == null) {
						Bukkit.getLogger()
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
		}
		catch (SQLException ignored) {
			Bukkit.getLogger()
			      .warning(SQLEXCEPTION);
			return null;
		}
		return new Location(world, x, y, z, yaw, pitch);
	}

	@Override
	public void saveSpawn(@NotNull CommandSender sender) {
		clear();
		Player senderPlayer = (Player) sender;
		Location location = senderPlayer.getLocation();
		try (PreparedStatement insertStatement = SqlManager.getConnection()
		                                                   .prepareStatement("INSERT INTO " + Sql.getPrefix()
		                                                                     + "Spawn (`World`, `X`, `Y`, `Z`, `Yaw`, `Pitch`) VALUES (?, ?, ?, ?, ?, ?)")) {
			insertStatement.setString(1, location.getWorld()
			                                     .getName());
			insertStatement.setInt(2, (int) location.getX());
			insertStatement.setInt(3, (int) location.getY());
			insertStatement.setInt(4, (int) location.getZ());
			insertStatement.setInt(5, (int) location.getYaw());
			insertStatement.setInt(6, (int) location.getPitch());
			insertStatement.executeUpdate();
		}
		catch (SQLException ignored) {
			Bukkit.getLogger()
			      .warning(SQLEXCEPTION);
		}
	}

	@Override
	protected void clear() {
		try (PreparedStatement truncateStatement = SqlManager.getConnection()
		                                                     .prepareStatement(
				                                                     "TRUNCATE TABLE " + Sql.getPrefix() + "Spawn")) {
			truncateStatement.executeUpdate();
		}
		catch (SQLException ignored) {
			Bukkit.getLogger()
			      .warning(SQLEXCEPTION);
		}
	}
}
