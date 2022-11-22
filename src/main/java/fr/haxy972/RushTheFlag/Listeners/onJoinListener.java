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

        // Setting up default attributes
        event.setJoinMessage("");
        Bukkit.broadcastMessage("§e" + player.getName() + "§7 has joined the game");
        player.sendMessage("§7You can join the game with §e\"/join\"");
        player.setHealth(20);player.setFoodLevel(20);player.setLevel(0);player.setExp(0);new GameInventoryManager(player);

        // Switching to Adventure with Fly Mode and hiding from all players
        player.setGameMode(GameMode.ADVENTURE);
        player.setAllowFlight(true);player.setFlying(true);player.setInvulnerable(true);
        for(Player players : Bukkit.getOnlinePlayers()){players.hidePlayer(player);}
    }

}
