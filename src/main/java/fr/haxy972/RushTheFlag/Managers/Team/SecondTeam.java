package fr.haxy972.RushTheFlag.Managers.Team;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SecondTeam extends Teams{

    private static final ArrayList<OfflinePlayer> playersList = new ArrayList<>();

    public SecondTeam(){
        teamName = "Purple";
        team_list = playersList;
        data_color = (byte)10;
        color_code = "§5";
    }
}
