package com.gmail.mikeundead.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.gmail.mikeundead.DamageLogger;

public class EntityDeath implements Listener
{
	private DamageLogger damageLogger;

	public EntityDeath(DamageLogger damageLogger)
	{
		this.damageLogger = damageLogger;
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event)
	{

	}
}