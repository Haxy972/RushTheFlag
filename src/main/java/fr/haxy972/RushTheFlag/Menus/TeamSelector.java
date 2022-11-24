package fr.haxy972.RushTheFlag.Menus;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Managers.SoundManager;
import fr.haxy972.RushTheFlag.Managers.Team.FirstTeam;
import fr.haxy972.RushTheFlag.Managers.Team.SecondTeam;
import fr.haxy972.RushTheFlag.Managers.Team.Teams;
import fr.haxy972.RushTheFlag.Runnables.TeamSelRunnable;
import fr.haxy972.RushTheFlag.Utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class TeamSelector {
    Player player;
    GameManager gameManager = new GameManager();
    Inventory inventory = Bukkit.createInventory(player, 9, "           §eTeam Selector §8- §7" +  gameManager.getPlayerInGameCount() + "/" + new Teams().maxCount * 2);
    public TeamSelector(Player player) {
        this.player = player;
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
        for(Player players : team.getTeam_list()){
            teamMenuLore.add(" §8-§e " + players.getName());
            playerCount ++;
        }

        if(playerCount > 0) {teamMenuLore.add("");}

        teamMenuLore.add("§a§l>§f Click to join this team");
        return teamMenuLore;
    }

    public void refreshInventory(){
        Teams firstTeam = new FirstTeam();
        Teams secondTeam = new SecondTeam();
        player.getOpenInventory().setItem(3,new ItemCreator(Material.WOOL, firstTeam.getDataColor()).setName(firstTeam.getColorCode() + firstTeam.getName() + " §8(§7" + firstTeam.getSize() +"§8/§7" + firstTeam.maxCount +"§8)").setArrayLore(new TeamSelector(player).getArrayLore(firstTeam)).done());
        player.getOpenInventory().setItem(5,new ItemCreator(Material.WOOL, secondTeam.getDataColor()).setName(secondTeam.getColorCode() + secondTeam.getName() +" §8(§7" + secondTeam.getSize() +"§8/§7" + secondTeam.maxCount +"§8)").setArrayLore(new TeamSelector(player).getArrayLore(secondTeam)).done());
    }


    public void open() {
        player.openInventory(inventory);
        new TeamSelRunnable(player).runTaskTimer(Main.INSTANCE, 0, 2);
        new SoundManager(player).playerSucess();
    }
}
