package fr.haxy972.RushTheFlag;

import fr.haxy972.RushTheFlag.Managers.CommandsManager;
import fr.haxy972.RushTheFlag.Managers.ListenersManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        new ListenersManager(this).registerListeners();
        new CommandsManager(this).registerCommands();
    }
}
