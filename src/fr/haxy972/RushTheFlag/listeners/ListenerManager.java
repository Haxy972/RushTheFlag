package fr.haxy972.RushTheFlag.listeners;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    private Plugin plugin;
    private PluginManager pluginManager;


    public ListenerManager(Plugin plugin){
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
    }


    public void registerEvent(){

        pluginManager.registerEvents(new OnPlayerJoin(), plugin);
        pluginManager.registerEvents(new TeamSelect(), plugin);



    }




}
