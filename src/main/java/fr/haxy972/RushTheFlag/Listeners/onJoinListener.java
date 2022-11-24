package fr.haxy972.RushTheFlag.Listeners;

import fr.haxy972.RushTheFlag.Managers.GameInventoryManager;
import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Managers.Team.FirstTeam;
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
        new GameManager(player).setJoinAttributes();
        // Hiding from all players
        for(Player players : Bukkit.getOnlinePlayers()){players.hidePlayer(player);}
    }

}
