package fr.haxy972.RushTheFlag.Managers.Team;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;

public class FirstTeam extends Teams{

    public static final ArrayList<OfflinePlayer> playersList = new ArrayList<>();

    public FirstTeam(){
        teamName = "Orange";
        team_list = playersList;
        data_color = (byte)1;
        color_code = "§6";
    }



}
