package com.gmail.mikeundead.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.gmail.mikeundead.DamageLogger;

public class PlayerDeath implements Listener
{
	private DamageLogger damageLogger;

	public PlayerDeath(DamageLogger damageLogger)
	{
		this.damageLogger = damageLogger;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{

	}
}