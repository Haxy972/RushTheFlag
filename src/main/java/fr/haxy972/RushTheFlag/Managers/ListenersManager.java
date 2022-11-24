package fr.haxy972.RushTheFlag.Managers;

import fr.haxy972.RushTheFlag.Listeners.*;
import fr.haxy972.RushTheFlag.Main;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {

    Main plugin;
    PluginManager pluginManager;
    public ListenersManager(Main main) {
        this.plugin = main;
        this.pluginManager = main.getServer().getPluginManager();
    }

    public void registerListeners(){
        pluginManager.registerEvents(new onJoinListener(), plugin);
        pluginManager.registerEvents(new onDropEvent(), plugin);
        pluginManager.registerEvents(new onInteractEvent(), plugin);
        pluginManager.registerEvents(new onSneak(), plugin);
        pluginManager.registerEvents(new onInventoryClick(), plugin);

    }
}
