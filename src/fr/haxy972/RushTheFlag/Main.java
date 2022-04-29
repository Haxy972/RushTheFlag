package fr.haxy972.RushTheFlag;

import fr.haxy972.RushTheFlag.commands.CommandDebug;
import fr.haxy972.RushTheFlag.commands.CommandKits;
import fr.haxy972.RushTheFlag.commands.CommandTeam;
import fr.haxy972.RushTheFlag.listeners.ListenerManager;
import fr.haxy972.RushTheFlag.runnables.GameRunnable;
import fr.haxy972.RushTheFlag.runnables.ScoreboardRunnable;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main INSTANCE;




    @Override
    public void onEnable() {
        this.INSTANCE = this;
        saveDefaultConfig();
        GameStatut.setStatut(GameStatut.INLOBBY);
        new ListenerManager(INSTANCE).registerEvent();
        getCommand("join").setExecutor(new CommandTeam());
        getCommand("kits").setExecutor(new CommandTeam());
        getCommand("rushtheflag").setExecutor(new CommandKits());
        getCommand("debug").setExecutor(new CommandDebug());

        for(Player players : Bukkit.getOnlinePlayers()){
            serverReloaded(players);
        }


    }

    private void serverReloaded(Player player) {
        for(int i = 0; i < 300; i++){
            player.sendMessage(" ");
        }
        player.getInventory().clear();
        player.sendMessage(Main.getPrefix() + "§6Le serveur a été reload");
        player.sendMessage(Main.getPrefix() + "§7Tapez §e§l/join§7 pour rejoindre la partie");
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Main.getJoinSpawn());
        ItemStack[] armor = player.getInventory().getArmorContents();
        armor[0].setType(Material.AIR);
        armor[1].setType(Material.AIR);
        armor[2].setType(Material.AIR);
        armor[3].setType(Material.AIR);


        player.getInventory().setArmorContents(armor);
        new ScoreboardRunnable().runTaskTimer(this, 0, 1);
        new GameRunnable().runTaskTimer(this, 0, 20);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 1f);

        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                new ScoreboardManager(player).loadScoreboard();
            }
        }, 20);
    }

    @Override
    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if (ScoreboardManager.scoreboardGame.containsKey(player)) {
                ScoreboardManager.scoreboardGame.get(player).destroy();
                ScoreboardManager.scoreboardGame.remove(player);
            }
        }
    }

    public static String getPrefix() {
        return "§b§lRush§f§lThe§b§lFlag§8» ";
    }


    public static Location getJoinSpawn(){
        double x = -623.175;
        double y = 108;
        double z = -116.946;
        int yaw = -89;
        int pitch = 43;


        return new Location(getWorld(), x, y, z, yaw, pitch);
    }

    public static Location getSpawnBleu() {
        double x = -599.897;
        double y = 70;
        double z = -167.770;
        int yaw = 114;
        int pitch = -3;
        return new Location(getWorld(), x, y, z, yaw, pitch);
    }
    public static Location getSpawnRouge() {
        double x = -601.561;
        double y = 70;
        double z = -59.477;
        int yaw = -60;
        int pitch = -4;


        return new Location(getWorld(), x, y, z, yaw, pitch);
    }

    public static Location getNexusRouge(){
        double x = -600.500;
        double y = 71;
        double z = -66.500;

        return new Location(getWorld(), x, y, z);
    }

    public static Location getNexusBleu(){
        double x = -600.500;
        double y = 71;
        double z = -160.500;

        return new Location(getWorld(), x, y, z);
    }

    public static World getWorld(){

        return Bukkit.getWorld("world");
    }




}
