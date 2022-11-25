package fr.haxy972.RushTheFlag.Menus;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Managers.SoundManager;
import fr.haxy972.RushTheFlag.Managers.Team.FirstTeam;
import fr.haxy972.RushTheFlag.Managers.Team.SecondTeam;
import fr.haxy972.RushTheFlag.Managers.Team.Teams;
import fr.haxy972.RushTheFlag.Runnables.TeamSelRunnable;
import fr.haxy972.RushTheFlag.Utils.ItemCreator;
import fr.haxy972.RushTheFlag.Utils.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class TeamSelector {
    Player player;
    Inventory inventory;
    GameManager gameManager = new GameManager();
    public TeamSelector(Player player) {
        this.player = player;
        inventory = Bukkit.createInventory(player, 9, "                §fTeam Selector");
        addItems();
    }

    public void addItems() {
        Teams firstTeam = new FirstTeam();
        Teams secondTeam = new SecondTeam();
        inventory.setItem(3,new ItemCreator(Material.WOOL, firstTeam.getDataColor()).setName(firstTeam.getColorCode() + firstTeam.getName() + " §8(§7" + firstTeam.getSize() +"§8/§7" + firstTeam.maxCount +"§8)").setArrayLore(getArrayLore(firstTeam)).done());
        inventory.setItem(5,new ItemCreator(Material.WOOL, secondTeam.getDataColor()).setName(secondTeam.getColorCode() + secondTeam.getName() +" §8(§7" + secondTeam.getSize() +"§8/§7" + secondTeam.maxCount +"§8)").setArrayLore(getArrayLore(secondTeam)).done());
    }

    public ArrayList<String> getArrayLore(Teams team) {
        ArrayList<String> teamMenuLore = new ArrayList<>();
        int playerCount = 0;
        for(OfflinePlayer players : team.getTeam_list()){
            teamMenuLore.add(" §8-§e " + players.getName());
            playerCount ++;
        }

        if(playerCount > 0) {teamMenuLore.add("");}

        teamMenuLore.add("§a§l>§f Click to join this team");
        return teamMenuLore;
    }

    public void refreshInventory(){
        if(!player.getOpenInventory().getTitle().contains("Team Selector"))return;
        Teams firstTeam = new FirstTeam();
        Teams secondTeam = new SecondTeam();
        player.getOpenInventory().setItem(3,new ItemCreator(Material.WOOL, firstTeam.getDataColor()).setName(firstTeam.getColorCode() + firstTeam.getName() + " §8(§7" + firstTeam.getSize() +"§8/§7" + firstTeam.maxCount +"§8)").setArrayLore(new TeamSelector(player).getArrayLore(firstTeam)).done());
        player.getOpenInventory().setItem(5,new ItemCreator(Material.WOOL, secondTeam.getDataColor()).setName(secondTeam.getColorCode() + secondTeam.getName() +" §8(§7" + secondTeam.getSize() +"§8/§7" + secondTeam.maxCount +"§8)").setArrayLore(new TeamSelector(player).getArrayLore(secondTeam)).done());
    }


    public void open() {
        if(!gameManager.getPlayerInGameList().contains(player)) {
            player.openInventory(inventory);
            new TeamSelRunnable(player).runTaskTimer(Main.INSTANCE, 0, 2);
        }else{
            new PluginMessage(player).Err("You are already in game you can't choose a team");
        }
    }
}
