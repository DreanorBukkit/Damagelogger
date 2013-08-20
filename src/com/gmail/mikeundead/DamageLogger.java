package com.gmail.mikeundead;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.mikeundead.Config.ConfigHandler;
import com.gmail.mikeundead.Config.LoadedValues;
import com.gmail.mikeundead.Config.Options;
import com.gmail.mikeundead.Executors.DamageLoggerCmds;
import com.gmail.mikeundead.Listeners.EntityDamage;
import com.gmail.mikeundead.Listeners.EntityDeath;
import com.gmail.mikeundead.Listeners.EntityRegainHealth;
import com.gmail.mikeundead.Listeners.PlayerDeath;
import com.gmail.mikeundead.Listeners.PlayerJoin;

public class DamageLogger extends JavaPlugin
{
	public LoadedValues values;
	public HashMap<String, Options> playerOptions;

	@Override
	public void onEnable()
	{
		this.values = new ConfigHandler(this).LoadValues();
		this.playerOptions = new HashMap<String, Options>();

		this.getCommand("dl").setExecutor(new DamageLoggerCmds(this));

        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        this.getServer().getPluginManager().registerEvents(new EntityDeath(this), this);
        this.getServer().getPluginManager().registerEvents(new EntityDamage(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
        this.getServer().getPluginManager().registerEvents(new EntityRegainHealth(this), this);

        for(Player player : this.getServer().getOnlinePlayers())
        {
			this.playerOptions.put(player.getName(), new Options());
        }

		this.getLogger().info("DamageLogger has been enabled.");
	}

	@Override
	public void onDisable()
	{
		this.getLogger().info("DamageLogger has been disabled.");
	}
}
