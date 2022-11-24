package fr.haxy972.RushTheFlag.Listeners;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Managers.Team.FirstTeam;
import fr.haxy972.RushTheFlag.Managers.Team.SecondTeam;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onInventoryClick implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        GameManager gameManager = new GameManager();
        Inventory inventory = event.getInventory();

        if(!gameManager.getPlayerInGameList().contains(player)){
            event.setCancelled(true);
            if(inventory.getName().contains("Team Selector")){
                if(item.getType().equals(Material.WOOL)){
                    if(item.getData().getData() == new FirstTeam().getDataColor()){
                        new FirstTeam().addPlayerToTeam(player);
                    }else if(item.getData().getData() == new SecondTeam().getDataColor()) {
                        new SecondTeam().addPlayerToTeam(player);
                    }
                }
            }
        }
    }
}
