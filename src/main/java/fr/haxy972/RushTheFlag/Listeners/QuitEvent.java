package fr.haxy972.RushTheFlag.Listeners;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Managers.Team.Teams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("");
        if(new Teams().getPlayerTeam(player) != null)new Teams().removePlayerFromTeam(player);
        if(new GameManager(player).getPlayerInGameList().contains(player))new GameManager(player).removePlayerFromGame();
    }
}
