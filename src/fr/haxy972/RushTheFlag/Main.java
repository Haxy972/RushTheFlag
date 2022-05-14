package fr.haxy972.RushTheFlag;

import fr.haxy972.RushTheFlag.commands.CommandKits;
import fr.haxy972.RushTheFlag.commands.CommandTeam;
import fr.haxy972.RushTheFlag.listeners.ListenerManager;
import fr.haxy972.RushTheFlag.listeners.ResetListeners;
import fr.haxy972.RushTheFlag.runnables.GameRunnable;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import fr.haxy972.RushTheFlag.utils.MessageYaml;
import fr.haxy972.RushTheFlag.utils.PluginUpdater;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main INSTANCE;
    public static boolean blocked = false;
    private static String language;
    public static void checkAllSpawn(){
        boolean have = false;
        if(Main.INSTANCE.getConfig().getDouble("game.spawnJoin.x") == 0
                && Main.INSTANCE.getConfig().getDouble("game.spawnJoin.y") == 0
                && Main.INSTANCE.getConfig().getDouble("game.spawnJoin.z") == 0
                && Main.INSTANCE.getConfig().getInt("game.spawnJoin.pitch") == 0
                && Main.INSTANCE.getConfig().getInt("game.spawnJoin.yaw") == 0){


                if(language.equalsIgnoreCase("fr")){
                    Bukkit.broadcastMessage(Main.getPrefix() + "§cLe spawn de Connexion n'est pas défini !");
                }else if (language.equalsIgnoreCase("es")){
                    Bukkit.broadcastMessage(Main.getPrefix() + "§cEl spawn de Conexión no está definido");
                }else{
                    Bukkit.broadcastMessage(Main.getPrefix() + "§cJoin Spawn isn't set in the config !");
                }

                have = true;
        }
        if(Main.INSTANCE.getConfig().getDouble("game.spawnBlue.x") == 0
                && Main.INSTANCE.getConfig().getDouble("game.spawnBlue.y") == 0
                && Main.INSTANCE.getConfig().getDouble("game.spawnBlue.z") == 0
                && Main.INSTANCE.getConfig().getInt("game.spawnBlue.pitch") == 0
                && Main.INSTANCE.getConfig().getInt("game.spawnBlue.yaw") == 0){

            if(language.equalsIgnoreCase("fr")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§cLe spawn de l'équipe Bleu n'est pas défini");
            }else if (language.equalsIgnoreCase("es")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§cEl Spawn de el equipo Azul no se establece en la configuración !");
            }else{
                Bukkit.broadcastMessage(Main.getPrefix() + "§cBlue Spawn isn't set in the config !");
            }
            have = true;

        }
        if(Main.INSTANCE.getConfig().getDouble("game.spawnRed.x") == 0
                && Main.INSTANCE.getConfig().getDouble("game.spawnRed.y") == 0
                && Main.INSTANCE.getConfig().getDouble("game.spawnRed.z") == 0
                && Main.INSTANCE.getConfig().getInt("game.spawnRed.pitch") == 0
                && Main.INSTANCE.getConfig().getInt("game.spawnRed.yaw") == 0){


            if(language.equalsIgnoreCase("fr")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§cLe spawn de l'équipe Rouge n'est pas défini");
            }else if (language.equalsIgnoreCase("es")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§El Spawn de el equipo Rojo no se establece en la configuración !");
            }else{
                Bukkit.broadcastMessage(Main.getPrefix() + "§cRed Spawn isn't set in the config !");
            }
            have = true;
        }
        if(Main.INSTANCE.getConfig().getDouble("game.nexusBlue.x") == 0
                && Main.INSTANCE.getConfig().getDouble("game.nexusBlue.y") == 0
                && Main.INSTANCE.getConfig().getDouble("game.nexusBlue.z") == 0){

            if(language.equalsIgnoreCase("fr")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§cLe nexus de l'équipe Bleu n'est pas défini !");
            }else if (language.equalsIgnoreCase("es")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§c¡El nexo del equipo Azul no está definido!");
            }else{
                Bukkit.broadcastMessage(Main.getPrefix() + "§cBlue Nexus isn't set in the config !");
            }

            have = true;
        }
        if(Main.INSTANCE.getConfig().getDouble("game.nexusRed.x") == 0
                && Main.INSTANCE.getConfig().getDouble("game.nexusRed.y") == 0
                && Main.INSTANCE.getConfig().getDouble("game.nexusRed.z") == 0){


            if(language.equalsIgnoreCase("fr")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§cLe nexus de l'équipe Rouge n'est pas défini !");
            }else if (language.equalsIgnoreCase("es")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§c¡El nexo del equipo Rojo no está definido!");
            }else{
                Bukkit.broadcastMessage(Main.getPrefix() + "§cRed Nexus isn't set in the config !");
            }
            have = true;
        }
        if(have == true){
            if(language.equalsIgnoreCase("fr")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§7Tapez §e\"/rtf setspawns\"§7 pour mettre les spawns");
            }else if (language.equalsIgnoreCase("es")){
                Bukkit.broadcastMessage(Main.getPrefix() + "§7Escriba §e\"/rtf setspawns\"§7 para establice los spawns");
            }else{
                Bukkit.broadcastMessage(Main.getPrefix() + "§7Type §e\"/rtf setspawns\"§7 to set spawns");
            }

        }

    }


    public static String getPrefix() {

        return Main.INSTANCE.getConfig().getString("prefix").replace("&", "§");
    }

    public static Location getJoinSpawn() {
        double x = Main.INSTANCE.getConfig().getDouble("game.spawnJoin.x");
        double y = Main.INSTANCE.getConfig().getDouble("game.spawnJoin.y");
        double z = Main.INSTANCE.getConfig().getDouble("game.spawnJoin.z");
        int yaw = Main.INSTANCE.getConfig().getInt("game.spawnJoin.yaw");
        int pitch = Main.INSTANCE.getConfig().getInt("game.spawnJoin.pitch");


        return new Location(getWorld(), x, y, z, yaw, pitch);
    }

    public static Location getSpawnBleu() {
        double x = Main.INSTANCE.getConfig().getDouble("game.spawnBlue.x");
        double y = Main.INSTANCE.getConfig().getDouble("game.spawnBlue.y");
        double z = Main.INSTANCE.getConfig().getDouble("game.spawnBlue.z");
        int yaw = Main.INSTANCE.getConfig().getInt("game.spawnBlue.yaw");
        int pitch = Main.INSTANCE.getConfig().getInt("game.spawnBlue.pitch");
        return new Location(getWorld(), x, y, z, yaw, pitch);
    }

    public static Location getSpawnRouge() {
        double x = Main.INSTANCE.getConfig().getDouble("game.spawnRed.x");
        double y = Main.INSTANCE.getConfig().getDouble("game.spawnRed.y");
        double z = Main.INSTANCE.getConfig().getDouble("game.spawnRed.z");
        int yaw = Main.INSTANCE.getConfig().getInt("game.spawnRed.yaw");
        int pitch = Main.INSTANCE.getConfig().getInt("game.spawnRed.pitch");


        return new Location(getWorld(), x, y, z, yaw, pitch);
    }

    public static Location getNexusRouge() {
        double x = Main.INSTANCE.getConfig().getDouble("game.nexusRed.x");
        double y = Main.INSTANCE.getConfig().getDouble("game.nexusRed.y");
        double z = Main.INSTANCE.getConfig().getDouble("game.nexusRed.z");

        return new Location(getWorld(), x, y, z);
    }

    public static Location getNexusBleu() {
        double x = Main.INSTANCE.getConfig().getDouble("game.nexusBlue.x");
        double y = Main.INSTANCE.getConfig().getDouble("game.nexusBlue.y");
        double z = Main.INSTANCE.getConfig().getDouble("game.nexusBlue.z");

        return new Location(getWorld(), x, y, z);
    }

    public static World getWorld() {

        return Bukkit.getWorld(Main.INSTANCE.getConfig().getString("game.world"));
    }

    @Override
    public void onEnable() {
        this.INSTANCE = this;
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage("§7[§eRushTheFlag§7] §e-> §bLoading config file..");
        saveDefaultConfig();
        MessageYaml.checkYaml();
        CommandKits.createKitFolder();
        PluginUpdater.check(this, "Haxy972", "RushTheFlag");
        language = Main.INSTANCE.getConfig().getString("language");


        GameStatut.setStatut(GameStatut.INLOBBY);
        new ListenerManager(INSTANCE).registerEvent();
        getCommand("join").setExecutor(new CommandTeam());
        getCommand("kits").setExecutor(new CommandTeam());
        getCommand("rushtheflag").setExecutor(new CommandKits());

        for (Player players : Bukkit.getOnlinePlayers()) {
            serverReloaded(players);
        }
        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                checkAllSpawn();
            }
        },20);



    }

    private void serverReloaded(Player player) {
        for (int i = 0; i < 300; i++) {
            player.sendMessage(" ");
        }
        player.getInventory().clear();
        player.sendMessage(Main.getPrefix() + MessageYaml.getValue("join.message-player").replace("&", "§").replace("{player}", player.getName()));
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Main.getJoinSpawn());
        ItemStack[] armor = player.getInventory().getArmorContents();
        armor[0].setType(Material.AIR);
        armor[1].setType(Material.AIR);
        armor[2].setType(Material.AIR);
        armor[3].setType(Material.AIR);
        player.setFoodLevel(20);
        player.setHealth(20);
        player.closeInventory();
        if (!Main.INSTANCE.getConfig().getString("game.team.tab-name-default").equalsIgnoreCase("none")) {
            player.setPlayerListName(Main.INSTANCE.getConfig().getString("game.team.tab-name-default").replace("&", "§").replace("{player}", player.getName()));
        }
        if(Main.INSTANCE.getConfig().getBoolean("nametag.default-group-enabled") && Main.INSTANCE.getConfig().getBoolean("nametag.enabled")){
            Main.INSTANCE.getServer().dispatchCommand(getServer().getConsoleSender(), Main.INSTANCE.getConfig().getString("nametag.default-group-onjoin").replace("{player}", player.getName()).replace("/", ""));
        }

        player.getInventory().setArmorContents(armor);
        new ScoreboardRunnable().runTaskTimer(this, 0, 1);
        new GameRunnable().runTaskTimer(this, 0, 20);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 1f);

        for (Chunk chunk : Main.getWorld().getLoadedChunks()) {
            for (Entity en : chunk.getEntities()) {
                if (en instanceof Item) {
                    en.remove();
                }
            }
        }


        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                new ScoreboardManager(player).loadScoreboard();
                if (Main.INSTANCE.getConfig().getBoolean("nametag.default-group-enabled") && Main.INSTANCE.getConfig().getBoolean("nametag.enabled")) {
                    Main.INSTANCE.getServer().dispatchCommand(getServer().getConsoleSender(), Main.INSTANCE.getConfig().getString("nametag.default-group-onjoin").replace("{player}", player.getName()).replace("/", ""));
                }
            }
        }, 20);
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (ScoreboardManager.scoreboardGame.containsKey(player)) {
                ScoreboardManager.scoreboardGame.get(player).destroy();
                ScoreboardManager.scoreboardGame.remove(player);
            }
        }
        ResetListeners.reloadBlocks();

    }


}
