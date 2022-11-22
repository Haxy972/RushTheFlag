package fr.haxy972.RushTheFlag.Managers;

import fr.haxy972.RushTheFlag.Menus.TeamSelector;
import org.bukkit.Bukkit;
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
}
