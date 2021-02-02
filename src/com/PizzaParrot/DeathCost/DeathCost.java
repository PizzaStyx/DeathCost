package com.PizzaParrot.DeathCost;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class DeathCost extends JavaPlugin 
{
    private Economy eco;
    double cost;
    boolean percent;
    boolean enabled;
    boolean msgtoggle;
    String message;

    @Override
    public void onEnable() 
    {
        if (!setupEconomy()) 
        {
            getLogger().severe("Could not detect an economy plugin - disabling plugin..");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        loadConfig();
        saveDefaultConfig();
        getCommand("deathcost").setExecutor(new CommandHandler(this));
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
    }

    void loadConfig()
    {
    	enabled = getConfig().getBoolean("enabled");
    	cost = getConfig().getDouble("death-cost");
    	percent = getConfig().getBoolean("is-percent");
    	msgtoggle = getConfig().getBoolean("enable-message");
    	message = getConfig().getString("message");
    }
    
    public boolean getEnabled()
    {
        return enabled;
    }
    
    public double getCost()
    {
    	return cost;
    }
    
    public boolean getPercent()
    {
    	return percent;
    }
    
    public boolean getMessageEnabled()
    {
    	return msgtoggle;
    }
    
    public String getMessage()
    {
    	return message;
    }
    
    public Economy getEco() 
    {
        return eco;
    }

    private boolean setupEconomy() 
    {
        if (getServer().getPluginManager().getPlugin("Vault") == null) 
        {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) 
        {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }
}