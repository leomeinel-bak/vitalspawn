package com.tamrielnetwork.vitalfly.listeners;

import com.tamrielnetwork.vitalfly.VitalFly;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.GameMode.SURVIVAL;

public class PlayerGamemodeChange implements Listener {

	private final VitalFly main = JavaPlugin.getPlugin(VitalFly.class);

	@EventHandler
	public void onGamemodeChange(PlayerGameModeChangeEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!event.getPlayer().hasPermission("vitalfly.fly") || !event.getPlayer().isOnline() || !event.getPlayer().hasPermission("vitalfly.fly.gamemodechange")) {
					return;
				}

				if (event.getNewGameMode() == SURVIVAL) {
					event.getPlayer().setAllowFlight(true);
					event.getPlayer().setFlying(true);
				}
			}
		}.runTaskLater(main, 1);
	}

}
