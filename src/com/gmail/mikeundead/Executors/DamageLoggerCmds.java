package com.gmail.mikeundead.Executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.DamageLogger;
import com.gmail.mikeundead.Config.Options;

public class DamageLoggerCmds implements CommandExecutor
{
	private DamageLogger damageLogger;

	public DamageLoggerCmds(DamageLogger damageLogger)
	{
		this.damageLogger = damageLogger;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
	{
		Player player = null;
		if (sender instanceof Player)
		{
			player = (Player) sender;
	    }
		else
		{
			return true;
		}

		if (args.length == 0)
		{
			return this.HandleGeneralHelp(player);
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("enable") && player.hasPermission("damagemeter.enable"))
		{
			return this.HandleEnableCmd(player);
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("disable"))
		{
			return this.HandleDisableCmd(player);
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("status"))
		{
			return this.HandleStatusCmd(player);
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("toggle"))
		{
			return this.HandleToggleHelp(player);
		}
		if(args.length == 2 && args[0].equalsIgnoreCase("toggle") && args[1].equalsIgnoreCase("damagedone"))
		{
			return this.HandleToggleDamageDone(player);
		}
		if(args.length == 2 && args[0].equalsIgnoreCase("toggle") && args[1].equalsIgnoreCase("damagetaken"))
		{
			return this.HandleToggleDamageTaken(player);
		}
		if(args.length == 2 && args[0].equalsIgnoreCase("toggle") && args[1].equalsIgnoreCase("dotdamagedone"))
		{
			return this.HandleToggleDotDamageDone(player);
		}
		if(args.length == 2 && args[0].equalsIgnoreCase("toggle") && args[1].equalsIgnoreCase("dotdamagetaken"))
		{
			return this.HandleToggleDotDamageTaken(player);
		}
		if(args.length == 2 && args[0].equalsIgnoreCase("toggle") && args[1].equalsIgnoreCase("healingdone"))
		{
			return this.HandleToggleHealingDone(player);
		}
		if(args.length == 2 && args[0].equalsIgnoreCase("toggle") && args[1].equalsIgnoreCase("healingreceived"))
		{
			return this.HandleToggleHealingReceived(player);
		}
		if(args.length == 2 && args[0].equalsIgnoreCase("toggle") && args[1].equalsIgnoreCase("deaths"))
		{
			return this.HandleToggleDeaths(player);
		}

		player.sendMessage(ChatColor.RED + "Wrong usage try /dl for help");

		return true;
	}

	private boolean HandleToggleDeaths(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		options.setDeaths();

		if(options.getDeaths())
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Deaths Enabled.");
		}
		else
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Damage Disabled.");
		}

		return true;
	}

	private boolean HandleToggleHealingReceived(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		options.setHealingReceived();

		if(options.getHealingReceived())
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Healing Received Enabled.");
		}
		else
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Healing Received Disabled.");
		}

		return true;
	}

	private boolean HandleToggleHealingDone(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		options.setHealingDone();

		if(options.getHealingDone())
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Healing Done Enabled.");
		}
		else
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Healing Done Disabled.");
		}

		return true;
	}

	private boolean HandleToggleDotDamageTaken(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		options.setDotDamageTaken();

		if(options.getDotDamageTaken())
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Dot Damage Taken Enabled.");
		}
		else
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Dot Damage Taken Disabled.");
		}

		return true;
	}

	private boolean HandleToggleDotDamageDone(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		options.setDotDamageDone();

		if(options.getDotDamageDone())
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Dot Damage Done Enabled.");
		}
		else
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Dot Damage Done Disabled.");
		}

		return true;
	}

	private boolean HandleToggleDamageTaken(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		options.setDamageTaken();

		if(options.getDamageTaken())
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Damage Taken Enabled.");
		}
		else
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Damage Taken Disabled.");
		}

		return true;
	}

	private boolean HandleToggleDamageDone(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		options.setDamageDone();

		if(options.getDamageDone())
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Damage Done Enabled.");
		}
		else
		{
			player.sendMessage(ChatColor.GREEN + "Logging for Damage Done Disabled.");
		}

		return true;
	}

	private boolean HandleEnableCmd(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		options.setEnabled(true);

		player.sendMessage(ChatColor.GREEN + "Logging Enabled.");

		return true;
	}

	private boolean HandleDisableCmd(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		options.setEnabled(false);
		player.sendMessage(ChatColor.GREEN + "Logging Disabled.");
		return true;
	}

	private boolean HandleStatusCmd(Player player)
	{
		Options options = this.damageLogger.playerOptions.get(player.getName());
		if(options.getEnabled())
		{
			player.sendMessage("Status: " + ChatColor.GREEN + "Enabled");
		}
		else
		{
			player.sendMessage("Status: " + ChatColor.RED + "Disabled");
		}

		player.sendMessage(String.format("Logging Options (%s, %s)", ChatColor.GREEN + "Green = Active", ChatColor.RED + "Red = Inactive"));

		if(options.getDamageDone())
		{
			player.sendMessage(ChatColor.GREEN + "DamageDone");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "DamageDone");
		}

		if(options.getDamageTaken())
		{
			player.sendMessage(ChatColor.GREEN + "DamageTaken");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "DamageTaken");
		}

		if(options.getDotDamageDone())
		{
			player.sendMessage(ChatColor.GREEN + "DotDamageDone");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "DotDamageDone");
		}

		if(options.getDotDamageTaken())
		{
			player.sendMessage(ChatColor.GREEN + "DotDamageReceived");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "DotDamageReceived");
		}

		if(options.getHealingDone())
		{
			player.sendMessage(ChatColor.GREEN + "HealingDone");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "HealingDone");
		}

		if(options.getHealingReceived())
		{
			player.sendMessage(ChatColor.GREEN + "HealingReceived");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "HealingReceived");
		}

		if(options.getDeaths())
		{
			player.sendMessage(ChatColor.GREEN + "Deaths");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "Deaths");
		}

		return true;
	}

	private boolean HandleToggleHelp(Player player)
	{
		player.sendMessage(ChatColor.YELLOW + "DamageLogger - Toggle Commands");
		player.sendMessage(ChatColor.AQUA + "/dl toggle"+ ChatColor.WHITE + " - Displays this help.");
		player.sendMessage(ChatColor.AQUA + "/dl toggle damagedone" + ChatColor.WHITE + " - Toggles Logging for DamgageDone");
		player.sendMessage(ChatColor.AQUA + "/dl toggle damagetaken" + ChatColor.WHITE + " - Toggles Logging for DamageTaken");
		player.sendMessage(ChatColor.AQUA + "/dl toggle dotdamagedone" + ChatColor.WHITE + " - Toggles Logging for DotDamageDone");
		player.sendMessage(ChatColor.AQUA + "/dl toggle dotdamagetaken" + ChatColor.WHITE + " - Toggles Logging for DotDamageTaken");
		player.sendMessage(ChatColor.AQUA + "/dl toggle healingdone" + ChatColor.WHITE + " - Toggles Logging for HealingDone");
		player.sendMessage(ChatColor.AQUA + "/dl toggle healingreceived" + ChatColor.WHITE + " - Toggles Logging for HealingReceived");
		player.sendMessage(ChatColor.AQUA + "/dl toggle deaths" + ChatColor.WHITE + " - Toggles Logging for Deaths");

		return true;
	}

	private boolean HandleGeneralHelp(Player player)
	{
		player.sendMessage(ChatColor.YELLOW + "DamageLogger - General Commands");
		player.sendMessage(ChatColor.AQUA + "/dl"+ ChatColor.WHITE + " - Displays this help.");
		player.sendMessage(ChatColor.AQUA + "/dl enable" + ChatColor.WHITE + " - Enables the DamageLogger for you.");
		player.sendMessage(ChatColor.AQUA + "/dl disable" + ChatColor.WHITE + " - Disables the DamageLogger for you.");
		player.sendMessage(ChatColor.AQUA + "/dl toggle" + ChatColor.WHITE + " - Toggles options for specific Damage Logging.");
		player.sendMessage(ChatColor.AQUA + "/dl status" + ChatColor.WHITE + " -  Displays the current Logging state.");

		return true;
	}
}