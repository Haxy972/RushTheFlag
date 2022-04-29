package fr.haxy972.RushTheFlag.listeners;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.runnables.DeathRunnable;
import fr.haxy972.RushTheFlag.utils.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
    @EventHandler
    public void onDeath(Player player){


        player.setGameMode(GameMode.SPECTATOR);
        player.setHealth(20);
        TitleManager.sendTitle(player,"§c§lMort", "§7Vous allez bientot réapparaître", 40);
        new DeathRunnable(player).runTaskTimer(Main.INSTANCE, 0,20);

    }

    private static Map<Location, Material> BlockPlaced = new HashMap<>();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
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



}
