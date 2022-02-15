package com.tamrielnetwork.vitalfly.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Material.AIR;


public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (!event.getPlayer().hasPermission("vitalfly.fly") || !event.getPlayer().hasPermission("vitalfly.fly.login")) {
			return;
		}

		if (isInAir(event)) {
			event.getPlayer().setAllowFlight(true);
			event.getPlayer().setFlying(true);
		}
	}

	private boolean isInAir(PlayerJoinEvent event) {
		Location location = event.getPlayer().getLocation();
		location.setY(location.getY() - 2);
		return event.getPlayer().getWorld().getBlockAt(location).getType() == AIR;
	}
}
