package fr.haxy972.RushTheFlag.Listeners;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Utils.PluginMessage;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class onInventoryClick implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();

        if(!new GameManager().getPlayerInGameList().contains(player)){
            if(item.getType().equals(Material.NETHER_STAR) && item.getItemMeta().getDisplayName().contains("Spectator")){
                if(player.getGameMode().equals(GameMode.ADVENTURE)){
                    player.setGameMode(GameMode.SPECTATOR);
                }

            }
        }
    }
}
