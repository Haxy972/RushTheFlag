package fr.haxy972.RushTheFlag;

import fr.haxy972.RushTheFlag.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    Main INSTANCE;

    @Override
    public void onEnable() {
        this.INSTANCE = this;
        GameStatut.setStatut(GameStatut.INLOBBY);
        new ListenerManager(INSTANCE).registerEvent();


    }

    @Override
    public void onDisable() {

    }
}
