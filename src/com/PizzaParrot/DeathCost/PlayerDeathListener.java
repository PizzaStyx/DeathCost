package com.PizzaParrot.DeathCost;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.milkbowl.vault.economy.Economy;

public class PlayerDeathListener implements Listener 
{
    private final DeathCost plugin;
    public PlayerDeathListener(DeathCost pl) 
    {
        this.plugin = pl;
    }

    DecimalFormat df = new DecimalFormat("#.##");
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) 
    {
        Player player = event.getEntity();
        Economy eco = plugin.getEco();
        double cost = plugin.getCost();
        boolean enablePlugin = plugin.getEnabled();
        boolean enablePercent = plugin.getPercent();
        boolean enableMessage = plugin.getMessageEnabled();
        String message = plugin.getMessage();
        
        if (!enablePlugin) return;
        
        if (player == null) return;
        
        if (player.hasPermission("deathcost.exempt")) return;
            
        if (enablePercent)
        {
            double balance = eco.getBalance(player);
            cost = Math.min((balance / 100) * cost, balance);
        }
            
        eco.withdrawPlayer(player, cost);
        if (enableMessage)
        {
        	String trueMessage = message.replace("%amount%", df.format(cost));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', trueMessage));
        }
    }
}