package fr.haxy972.RushTheFlag.Managers.Team;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Utils.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Teams {

    String teamName;
    ArrayList<OfflinePlayer> team_list;
    Byte data_color;
    String color_code;

    public Integer maxCount = (new GameManager().getGameMaxPlayer()-3)/2;

    public String getName(){
        return teamName;
    }

    public Boolean isFull(){
        return maxCount == team_list.size();
    }
    public ArrayList<OfflinePlayer> getTeam_list(){
        return team_list;
    }

    public Integer getSize(){
        return team_list.size();
    }


    public Byte getDataColor(){
        return data_color;
    }
    public String getColorCode(){
        return color_code;
    }

    public void addPlayerToTeam(Player player){
        if(!team_list.contains(player)){
            if(!isFull()) {

                /* PlayerTeam variable returns "null" as default if player doesn't have a team
                *  So to remove from team list I did a try/catch
                **/
                try {
                    Teams playerTeam = new Teams().getPlayerTeam(player);
                    playerTeam.team_list.remove(player);
                } catch (Exception ignored) {}

                team_list.add(player);
                new PluginMessage(player).Notif("§7You have joined the " + getColorCode() + getName() + " §7team");
            }else{
                new PluginMessage(player).Err("This team is full, try to join later");
            }
        }else{
            new PluginMessage(player).Err("You are already in this team");
        }
    }
    public void removePlayerFromTeam(Player player){
        Teams playerTeam = getPlayerTeam(player);
        playerTeam.getTeam_list().remove(player);
    }

    public void clearList() {
        team_list.clear();
    }

    public Teams getPlayerTeam(Player player){
        if(new FirstTeam().team_list.contains(player)){
            return new FirstTeam();
        }else if(new SecondTeam().team_list.contains(player)){
            return new SecondTeam();
        }
        return null;
    }
}
