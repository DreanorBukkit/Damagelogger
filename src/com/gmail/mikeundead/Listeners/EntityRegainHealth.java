package com.gmail.mikeundead.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import com.gmail.mikeundead.DamageLogger;

public class EntityRegainHealth implements Listener
{
	private DamageLogger damageLogger;

	public EntityRegainHealth(DamageLogger damageLogger)
	{
		this.damageLogger = damageLogger;
	}

	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event)
	{

	}
}