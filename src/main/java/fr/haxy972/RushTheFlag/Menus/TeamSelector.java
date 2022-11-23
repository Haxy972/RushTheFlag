package fr.haxy972.RushTheFlag.Menus;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Managers.SoundManager;
import fr.haxy972.RushTheFlag.Managers.Team.FirstTeam;
import fr.haxy972.RushTheFlag.Managers.Team.SecondTeam;
import fr.haxy972.RushTheFlag.Managers.Team.Teams;
import fr.haxy972.RushTheFlag.Utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class TeamSelector {
    Player player;
    GameManager gameManager = new GameManager();
    Inventory inventory = Bukkit.createInventory(player, 9, "           §eTeam Selector §8- §7" +  gameManager.getPlayerInGameCount() + "/" + gameManager.getGameMaxPlayer());
    public TeamSelector(Player player) {
        this.player = player;
        addItems();
    }

    private void addItems() {
        Teams firstTeam = new FirstTeam();
        Teams secondTeam = new SecondTeam();
        inventory.remove(Material.AIR);

        inventory.setItem(3,new ItemCreator(Material.WOOL, firstTeam.getDataColor()).setName(firstTeam.getColorCode() + firstTeam.getName() + " §8(§7" + firstTeam.getSize() +"§8/§7" + firstTeam.maxCount +"§8)").setArrayLore(getArrayLore(firstTeam)).done());
        inventory.setItem(5,new ItemCreator(Material.WOOL, secondTeam.getDataColor()).setName(secondTeam.getColorCode() + secondTeam.getName() +" §8(§7" + secondTeam.getSize() +"§8/§7" + secondTeam.maxCount +"§8)").setArrayLore(getArrayLore(secondTeam)).done());
    }

    private ArrayList<String> getArrayLore(Teams team) {
        ArrayList<String> teamMenuLore = new ArrayList<>();
        int playerCount = 0;
        for(Player players : team.getTeam_list()){
            teamMenuLore.add(" §8-§e " + players.getName());
            playerCount ++;
        }

        if(playerCount > 0) {teamMenuLore.add("");}

        teamMenuLore.add("§a§l>§f Click to join this team");
        return teamMenuLore;
    }


    public void open() {
        player.openInventory(inventory);
        new SoundManager(player).playerSucess();
    }
}
