package com.PizzaParrot.DeathCost;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CommandHandler implements CommandExecutor 
{
	private final DeathCost plugin;
	public CommandHandler(final DeathCost pl)
	{
		this.plugin = pl;
	}
		
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args)
	{
		if (!(sender.hasPermission("deathcost.admin")))
		{
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			return true;
		}
		if (!(args.length == 1) || !(args[0].equalsIgnoreCase("reload"))) 
		{
			sender.sendMessage(ChatColor.GREEN + "/deathcost reload " + ChatColor.GRAY + "- reloads plugin configuration");
			return true;
		}
		plugin.reloadConfig();
		plugin.loadConfig();
		plugin.saveDefaultConfig();
		sender.sendMessage(ChatColor.GREEN + plugin.getName() + ChatColor.GRAY + " version " + ChatColor.WHITE + plugin.getDescription().getVersion() + ChatColor.GRAY + " has been reloaded.");
		
		if (!(sender instanceof ConsoleCommandSender)) 
		{
	    	Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + plugin.getName() + ChatColor.GRAY + " version " + ChatColor.WHITE + plugin.getDescription().getVersion() + ChatColor.GRAY + " has been reloaded.");
		}
		return true;
	}
}
