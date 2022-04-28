package fr.haxy972.RushTheFlag;

import fr.haxy972.RushTheFlag.listeners.ListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    Main INSTANCE;

    @Override
    public void onEnable() {
        this.INSTANCE = this;
        GameStatut.setStatut(GameStatut.INLOBBY);
        new ListenerManager(INSTANCE).registerEvent();
        getCommand("join").setExecutor(new CommandTeam());


    }

    @Override
    public void onDisable() {

    }




    public static Location getJoinSpawn(){
        double x = -623.175;
        double y = 108;
        double z = -116.946;
        int yaw = -89;
        int pitch = 43;


        return new Location(getWorld(), x, y, z, yaw, pitch);
    }

    public static World getWorld(){

        return Bukkit.getWorld("world");
    }




}
