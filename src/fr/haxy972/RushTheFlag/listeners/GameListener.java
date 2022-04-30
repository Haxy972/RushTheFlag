package fr.haxy972.RushTheFlag.listeners;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.runnables.DeathRunnable;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import fr.haxy972.RushTheFlag.utils.TitleManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameListener implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent event){
        Player player = (Player) event.getEntity();
        player.setFoodLevel(20);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);

    }
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(!GameStatut.isStatut(GameStatut.INGAME)){
            return;
        }

        if(event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK){
            event.setCancelled(true);
        }
        if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        double y = player.getLocation().getY();

        if(y < 45){
            if(!player.getGameMode().equals(GameMode.SPECTATOR)) {
                onDeath(player);
                player.teleport(Main.getJoinSpawn());
            }
        }

    }


    @EventHandler
    public void onDeath(PlayerDeathEvent event){

        Player player = event.getEntity();
        player.setGameMode(GameMode.SPECTATOR);
        player.setHealth(20);
        TitleManager.sendTitle(player,"§c§lMort", "§7Vous allez bientot réapparaître", 40);
        new DeathRunnable(player).runTaskTimer(Main.INSTANCE, 0,20);
        event.setKeepInventory(true);
        event.setDeathMessage("");
        event.getDrops().clear();

    }

    public void onDeath(Player player){


        player.setGameMode(GameMode.SPECTATOR);
        player.setHealth(20);
        TitleManager.sendTitle(player,"§c§lMort", "§7Vous allez bientot réapparaître", 40);
        new DeathRunnable(player).runTaskTimer(Main.INSTANCE, 0,20);

    }

    private static Map<Location, Material> BlockPlaced = new HashMap<>();

    @EventHandler
    public void onFlagPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();




        ArrayList<Location> blockat = new ArrayList<>();
        for(int x = -2; x <= 2; x++){
            for(int y = -2; y <= 2; y++){
                for(int z = -2; z <= 2; z++){

                    double xs = Main.getNexusRouge().getX();
                    double ys = Main.getNexusRouge().getY();
                    double zs = Main.getNexusRouge().getZ();

                    Location loc = new Location(Main.getWorld(), xs + x-0.5, ys + y , zs+ z-0.5);
                    blockat.add(loc);

                    xs = Main.getNexusBleu().getX();
                    ys = Main.getNexusBleu().getY();
                    zs = Main.getNexusBleu().getZ();

                    loc = new Location(Main.getWorld(), xs + x-0.5, ys + y , zs+ z-0.5);
                    blockat.add(loc);
                }
            }

        }
        if(blockat.contains(event.getBlockPlaced().getLocation())){
            event.setCancelled(true);
            player.sendMessage(Main.getPrefix() + "§cVous ne pouvez pas poser de blocs ici");
        }

    }
    public static Player hasRedWool = null;
    public static Player hasBlueWool = null;

    @EventHandler
    public void onFlagBreak(BlockBreakEvent event){


        Player player = event.getPlayer();

        if(GameStatut.isStatut(GameStatut.INLOBBY)){
            player.sendMessage(Main.getPrefix() + "§cIl n'y a personne dans l'équipe adverse, action impossible");
            event.setCancelled(true);
            return;
        }


        ArrayList<Location> blockatbleu = new ArrayList<>();
        ArrayList<Location> blockatrouge = new ArrayList<>();
        for(int x = -2; x <= 2; x++){
            for(int y = -2; y <= 2; y++){
                for(int z = -2; z <= 2; z++){

                    double xs = Main.getNexusRouge().getX();
                    double ys = Main.getNexusRouge().getY();
                    double zs = Main.getNexusRouge().getZ();

                    Location loc = new Location(Main.getWorld(), xs + x-0.5, ys + y , zs+ z-0.5);
                    blockatrouge.add(loc);

                    xs = Main.getNexusBleu().getX();
                    ys = Main.getNexusBleu().getY();
                    zs = Main.getNexusBleu().getZ();

                    loc = new Location(Main.getWorld(), xs + x-0.5, ys + y , zs+ z-0.5);
                    blockatbleu.add(loc);
                }
            }

        }
        if(blockatrouge.contains(event.getBlock().getLocation())){
            if(event.getBlock().getType() != Material.WOOL) {
                event.setCancelled(true);
                player.sendMessage(Main.getPrefix() + "§cVous ne pouvez pas casser de blocs ici");

            }else{
                if(TeamSelect.teamBleu.contains(player)){
                    Bukkit.broadcastMessage(Main.getPrefix() + "§e" + player.getName() + "§a a récupéré la laine §c§lRouge§a !");
                    hasRedWool = player;


                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1f, 1f);

                    }
                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            for(Player players : Bukkit.getOnlinePlayers()){
                                players.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1f, 1f);

                            }

                        }
                    }, 20);

                }else if(TeamSelect.teamRouge.contains(player)){
                    event.setCancelled(true);
                    player.sendMessage(Main.getPrefix() + "§cVous ne pouvez pas casser votre propre laine");
                }
            }
        }
        if(blockatbleu.contains(event.getBlock().getLocation())){
            if(event.getBlock().getType() != Material.WOOL) {
                event.setCancelled(true);
                player.sendMessage(Main.getPrefix() + "§cVous ne pouvez pas casser de blocs ici");

            }else{
                if(TeamSelect.teamRouge.contains(player)){
                    Bukkit.broadcastMessage(Main.getPrefix() + "§e" + player.getName() + "§a a récupéré la laine §9§lBleu§a !");
                    hasBlueWool = player;

                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1f, 1f);

                    }
                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            for(Player players : Bukkit.getOnlinePlayers()){
                                players.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1f, 1f);

                            }

                        }
                    }, 20);

                }else if(TeamSelect.teamBleu.contains(player)){
                    event.setCancelled(true);
                    player.sendMessage(Main.getPrefix() + "§cVous ne pouvez pas casser votre propre laine");
                }
            }
        }

    }
    @EventHandler
    public void onBlockSpawnPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ArrayList<Location> blockat = new ArrayList<>();
        for (int x = -5; x <= 5; x++) {
            for (int y = -4; y <= 4; y++) {
                for (int z = -2; z <= 2; z++) {

                    double xs = Main.getSpawnBleu().getX()-0.5;
                    double ys = Main.getSpawnBleu().getY();
                    double zs = Main.getSpawnBleu().getZ() -0.5;
                    Location loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0 ,0);
                    blockat.add(loc);


                    xs = Main.getSpawnRouge().getX()-0.5;
                    ys = Main.getSpawnRouge().getY();
                    zs = Main.getSpawnRouge().getZ() -0.5;
                    loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0 ,0);
                    blockat.add(loc);
                }
            }
        }
        if(blockat.contains(event.getBlockPlaced().getLocation())){
            event.setCancelled(true);
            player.sendMessage(Main.getPrefix() + "§cVous ne pouvez pas poser de blocs ici");
        }
    }
    @EventHandler
    public void onBlockSpawnBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ArrayList<Location> blockat = new ArrayList<>();
        for (int x = -5; x <= 5; x++) {
            for (int y = -4; y <= 4; y++) {
                for (int z = -2; z <= 2; z++) {

                    double xs = Main.getSpawnBleu().getX()-0.5;
                    double ys = Main.getSpawnBleu().getY();
                    double zs = Main.getSpawnBleu().getZ() -0.5;
                    Location loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0 ,0);
                    blockat.add(loc);


                    xs = Main.getSpawnRouge().getX()-0.5;
                    ys = Main.getSpawnRouge().getY();
                    zs = Main.getSpawnRouge().getZ() -0.5;
                    loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0 ,0);
                    blockat.add(loc);
                }
            }
        }
        if(blockat.contains(event.getBlock().getLocation())){
            event.setCancelled(true);
            player.sendMessage(Main.getPrefix() + "§cVous ne pouvez pas casser de blocs ici");
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        event.setCancelled(true);

        Bukkit.broadcastMessage(getTeam(player) + message);



    }

    private String getTeam(Player player) {

        if(TeamSelect.teamBleu.contains(player)){
            return "§9§lBleu §9" + player.getName() + "§8» §7";
        }else if (TeamSelect.teamRouge.contains(player)){
            return "§c§lRouge §c" + player.getName() + "§8» §7";
        }


        return "§7" + player.getName() + "§8» §7";
    }

}
