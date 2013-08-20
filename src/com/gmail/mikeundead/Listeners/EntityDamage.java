package com.gmail.mikeundead.Listeners;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.gmail.mikeundead.DamageLogger;
import com.gmail.mikeundead.Config.Options;

public class EntityDamage implements Listener
{
	private DamageLogger damageLogger;
	private HashMap<UUID, String> lastAttackedEntity;

	public EntityDamage(DamageLogger damageLogger)
	{
		this.damageLogger = damageLogger;
		this.lastAttackedEntity = new HashMap<UUID, String>();
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();

			this.HandleDamageTakenLogging(event, player);
		}

		if(event.getDamager() instanceof Player)
		{
			Player player = (Player) event.getDamager();

			this.HandleDamageDoneLogging(event, player);
		}

		if(event.getDamager().getType() == EntityType.ARROW)
		{
			Arrow arrow = (Arrow) event.getDamager();

			if(arrow.getShooter() instanceof Player)
			{
				Player player = (Player) arrow.getShooter();
				this.lastAttackedEntity.put(event.getEntity().getUniqueId(), player.getName());

				this.HandleDamageDoneLogging(event, player);
			}
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event)
	{
		if(event.getCause() == DamageCause.POISON || event.getCause() == DamageCause.FIRE_TICK)
		{
			if(this.lastAttackedEntity.containsKey(event.getEntity().getUniqueId()))
			{
				Player player = this.damageLogger.getServer().getPlayer(this.lastAttackedEntity.get(event.getEntity().getUniqueId()));

				if(player != null)
				{
					this.HandleDotDamageDoneLogging(event, player);
				}

				this.lastAttackedEntity.remove(event.getEntity().getUniqueId());
			}

			if(event.getEntity() instanceof Player)
			{
				Player player = (Player) event.getEntity();
				this.HandleDotDamageTakenLogging(event, player);
			}
		}
	}

	private void HandleDotDamageTakenLogging(EntityDamageEvent event, Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		if(options.getDotDamageTaken())
		{
			String message = this.CreateDotDamageTakenMessage(event, player);

			player.sendMessage(message);
		}
	}

	private String CreateDotDamageTakenMessage(EntityDamageEvent event, Player player)
	{
		String message = this.damageLogger.values.getDotDamageDone();

		int health = player.getHealth() - event.getDamage();
		float percent = (((float) health / (float) player.getMaxHealth()) * 100);

		String a = message.replace("%timestamp%", this.CreateTimestamp());
		String b = a.replace("%health%", Integer.toString(health));
		String c = b.replace("%percent%", Float.toString(percent));
		String d = c.replace("%dotDamageTaken%", Integer.toString(event.getDamage()));
		String e = d.replace("%dotDamageCause%", this.CreateDamageCauseMessage(event.getCause()));

		return e;
	}

	private void HandleDotDamageDoneLogging(EntityDamageEvent event, Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		if(options.getDotDamageDone())
		{
			String message = this.CreateDotDamageDoneMessage(event);

			player.sendMessage(message);
		}
	}

	private String CreateDotDamageDoneMessage(EntityDamageEvent event)
	{
		String message = this.damageLogger.values.getDotDamageDone();

		int enemyHealth = 0;
		float percent = 0;
		String enemyName;

		if(event.getEntity() instanceof Player)
		{
			Player enemy = (Player) event.getEntity();
			enemyName = enemy.getName();
			enemyHealth = enemy.getHealth() - event.getDamage();
			percent = (((float) enemyHealth / (float) enemy.getMaxHealth()) * 100);
		}
		else
		{
			LivingEntity entity = (LivingEntity) event.getEntity();
			enemyName = event.getEntityType().name();
			enemyHealth = entity.getHealth() - event.getDamage();
			percent = (((float) enemyHealth / (float) entity.getMaxHealth()) * 100);
		}

		String a = message.replace("%timestamp%", this.CreateTimestamp());
		String b = a.replace("%health%", Integer.toString(enemyHealth));
		String c = b.replace("%percent%", Float.toString(percent));
		String d = c.replace("%enemyName%", enemyName);
		String e = d.replace("%dotDamageDone%", Integer.toString(event.getDamage()));
		String f = e.replace("%dotDamageCause%", this.CreateDamageCauseMessage(event.getCause()));

		return f;
	}

	private void HandleDamageTakenLogging(EntityDamageByEntityEvent event, Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		if(options.getDamageTaken())
		{
			String message = this.CreateDamageTakenMessage(event, player);

			player.sendMessage(message);
		}
	}

	private String CreateDamageTakenMessage(EntityDamageByEntityEvent event, Player player)
	{
		String message = this.damageLogger.values.getDamageTaken();

		int health = player.getHealth() - event.getDamage();
		float percent = (((float) health / (float) player.getMaxHealth()) * 100);
		String isCrit = "hit";
		String enemyName;

		if(event.getDamager() instanceof Player)
		{
			Player enemy = (Player) event.getDamager();
			enemyName = enemy.getName();
		}
		else
		{
			enemyName = event.getDamager().getType().getName();
		}

		String a = message.replace("%timestamp%", this.CreateTimestamp());
		String b = a.replace("%health%", Integer.toString(health));
		String c = b.replace("%percent%", Float.toString(percent));
		String d = c.replace("%isCrit%", isCrit);
		String e = d.replace("%enemyName%", enemyName);
		String f = e.replace("%damageTaken%", Integer.toString(event.getDamage()));

		return f;
	}

	private void HandleDamageDoneLogging(EntityDamageByEntityEvent event, Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		if(options.getDamageDone())
		{
			String message = this.CreateDamageDoneMessage(event);

			player.sendMessage(message);
		}
	}

	private String CreateDamageCauseMessage(DamageCause cause)
	{
		if(cause == DamageCause.FIRE_TICK)
		{
			return "Fire";
		}
		else
		{
			return "Poison";
		}
	}

	private String CreateDamageDoneMessage(EntityDamageByEntityEvent event)
	{
		String message = this.damageLogger.values.getDamageDone();

		int enemyHealth = 0;
		String enemyName;

		float percent = 0;
		String isCrit = "hit";

		if(event.getEntityType() == EntityType.PLAYER)
		{
			Player otherPlayer = (Player) event.getEntity();
			enemyName = otherPlayer.getName();
			enemyHealth = otherPlayer.getHealth() - event.getDamage();

			percent = (((float) enemyHealth / (float) otherPlayer.getMaxHealth()) * 100);
		}
		else
		{
			LivingEntity entity = (LivingEntity) event.getEntity();
			enemyHealth = entity.getHealth() - event.getDamage();
			enemyName = event.getEntityType().name();

			percent = (((float) enemyHealth / (float) entity.getMaxHealth()) * 100);
		}

		String a = message.replace("%timestamp%", this.CreateTimestamp());
		String b = a.replace("%health%", Integer.toString(enemyHealth));
		String c = b.replace("%percent%", Float.toString(percent));
		String d = c.replace("%isCrit%", isCrit);
		String e = d.replace("%enemyName%", enemyName);
		String f = e.replace("%damageDone%", Integer.toString(event.getDamage()));

		return f;
	}

	private String CreateTimestamp()
	{
		SimpleDateFormat date_format = new SimpleDateFormat("HH:mm:ss");
	    Date resultdate = new Date(System.currentTimeMillis());
	    String timestamp = date_format.format(resultdate);

	    return timestamp;
	}
}