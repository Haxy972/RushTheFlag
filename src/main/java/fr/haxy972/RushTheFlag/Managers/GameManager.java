package fr.haxy972.RushTheFlag.Managers;

import fr.haxy972.RushTheFlag.Menus.TeamSelector;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameManager {

    private Player player;
    private static final ArrayList<Player> playersList = new ArrayList<>();
    private final Integer maxPlayer = Bukkit.getMaxPlayers();

    public GameManager(Player player){
        this.player = player;
    }
    public GameManager(){}

    public void addPlayerToGame() {playersList.add(player);}

    public Integer getGameMaxPlayer(){return maxPlayer;}
    public void removePlayerFromGame(){playersList.remove(player);}

    public Integer getPlayerInGameCount(){return playersList.size();}

    public ArrayList<Player> getPlayerInGameList(){return playersList;}

    public void openTeamSelector() {new TeamSelector(player).open();}

    public void setJoinAttributes(){
        player.setHealth(20);player.setFoodLevel(20);player.setLevel(0);player.setExp(0);
        new GameInventoryManager(player).setSpectatorItems();
        player.setGameMode(GameMode.ADVENTURE);
        player.setAllowFlight(true);player.setFlying(true);player.setInvulnerable(true);
    }
}
