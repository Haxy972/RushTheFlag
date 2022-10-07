package fr.haxy972.RushTheFlag.Listeners;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.Managers.GameInventoryManager;
import fr.haxy972.RushTheFlag.Managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        GameManager gameManager = new GameManager(player);
        // Sending join messages
        event.setJoinMessage("§e" + player.getName() + "§7 à rejoint la partie");
        player.sendMessage("§7Vous pouvez rejoindre la partie en cliquant sur la plume");

        // Reset of default attribut of player food lvl and health ....
        player.setHealth(20);player.setFoodLevel(20);player.setLevel(0);player.setExp(0);

        // Giving join item to player && Spec mode
        new GameInventoryManager(player).giveJoinItems();
        Bukkit.getScheduler().runTaskLater(Main.INSTANCE, () -> player.setGameMode(GameMode.SPECTATOR), 5);
    }


}
