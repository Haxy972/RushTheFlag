package fr.haxy972.RushTheFlag.Managers.Team;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FirstTeam extends Teams{

    public static final ArrayList<Player> playersList = new ArrayList<>();

    public FirstTeam(){
        teamName = "Red";
        team_list = playersList;
        data_color = (byte)14;
        color_code = "§c";
    }



}
