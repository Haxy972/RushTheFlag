package fr.haxy972.RushTheFlag.Listeners;

import fr.haxy972.RushTheFlag.Managers.Team.Teams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onQuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if(new Teams().getPlayerTeam(player) != null){
            new Teams().removePlayerFromTeam(player);
        }
    }
}
