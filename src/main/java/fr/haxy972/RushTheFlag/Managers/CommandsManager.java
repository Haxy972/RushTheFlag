package fr.haxy972.RushTheFlag.Managers;

import fr.haxy972.RushTheFlag.Commands.JoinCommand;
import fr.haxy972.RushTheFlag.Main;

public class CommandsManager {
    Main plugin;
    public CommandsManager(Main main) {
        this.plugin = main;
    }

    public void registerCommands(){
        plugin.getCommand("join").setExecutor(new JoinCommand());
    }

}
