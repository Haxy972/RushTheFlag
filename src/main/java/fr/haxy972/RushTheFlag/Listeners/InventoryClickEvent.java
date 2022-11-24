package fr.haxy972.RushTheFlag.Listeners;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Managers.SoundManager;
import fr.haxy972.RushTheFlag.Managers.Team.FirstTeam;
import fr.haxy972.RushTheFlag.Managers.Team.SecondTeam;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickEvent implements Listener {

    @EventHandler
    public void inventoryClick(org.bukkit.event.inventory.InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        GameManager gameManager = new GameManager();
        Inventory inventory = event.getInventory();

        if(!gameManager.getPlayerInGameList().contains(player)){
            player.closeInventory();
            event.setCancelled(true);
            if(inventory.getName().contains("Team Selector")){
                if(item.getType().equals(Material.WOOL)){
                    new SoundManager(player).play(Sound.BLOCK_NOTE_PLING, 3f);
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
