package com.gmail.mikeundead.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.mikeundead.DamageLogger;
import com.gmail.mikeundead.Config.Options;

public class PlayerJoin implements Listener
{
	private DamageLogger damageLogger;

	public PlayerJoin(DamageLogger damageLogger)
	{
		this.damageLogger = damageLogger;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if(!this.damageLogger.playerOptions.containsKey(event.getPlayer().getName()))
		{
			this.damageLogger.playerOptions.put(event.getPlayer().getName(), new Options());
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		this.damageLogger.playerOptions.remove(event.getPlayer().getName());
	}
}
