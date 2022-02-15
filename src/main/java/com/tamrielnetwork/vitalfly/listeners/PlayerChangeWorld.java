package com.tamrielnetwork.vitalfly.listeners;

import com.tamrielnetwork.vitalfly.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorld implements Listener {
	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		if (!event.getPlayer().hasPermission("vitalfly.fly") || !event.getPlayer().hasPermission("vitalfly.fly.worldchange")) {
			return;
		}
		event.getPlayer().setAllowFlight(true);
		Utils.sendMessage(event.getPlayer(), "now-flying");

	}

}
