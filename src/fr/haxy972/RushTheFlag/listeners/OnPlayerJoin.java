package fr.haxy972.RushTheFlag.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){

        Player player = event.getPlayer();

        event.setJoinMessage("§e" + player.getName() + "a rejoint le §b§lRushTheFlag");
        player.setGameMode(GameMode.SPECTATOR);



    }


}
