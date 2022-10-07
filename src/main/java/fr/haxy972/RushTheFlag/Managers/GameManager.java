package fr.haxy972.RushTheFlag.Managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameManager {

    private final Player player;
    private static ArrayList<Player> playersList = new ArrayList<>();

    public GameManager(Player player){
        this.player = player;
    }

    public void addPlayerToGame(){playersList.add(player);}

    public void removePlayerFromGame(){playersList.remove(player);}

    public Integer getPlayerInGameCount(){return playersList.size();}

    public ArrayList<Player> getPlayerInGameList(){return playersList;}



}
