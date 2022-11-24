package fr.haxy972.RushTheFlag.Runnables;

import fr.haxy972.RushTheFlag.Managers.Team.FirstTeam;
import fr.haxy972.RushTheFlag.Managers.Team.SecondTeam;
import fr.haxy972.RushTheFlag.Managers.Team.Teams;
import fr.haxy972.RushTheFlag.Menus.TeamSelector;
import fr.haxy972.RushTheFlag.Utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamSelRunnable extends BukkitRunnable {
    private final Player player;

    public TeamSelRunnable(Player player){
        this.player = player;
    }

    @Override
    public void run() {
        if(!player.getOpenInventory().getTitle().contains("Team Selector"))cancel();
        new TeamSelector(player).refreshInventory();
    }
}
