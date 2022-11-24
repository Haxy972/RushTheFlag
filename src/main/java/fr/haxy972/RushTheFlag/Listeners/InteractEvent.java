package fr.haxy972.RushTheFlag.Listeners;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Runnables.SneakRunnable;
import fr.haxy972.RushTheFlag.Utils.PluginMessage;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();

        if(item == null && action != null)return;

        if(!new GameManager().getPlayerInGameList().contains(player)){
            switch (item.getType()){
                case NETHER_STAR:
                    if(item.getItemMeta().getDisplayName().contains("Spectator")){
                        if(player.getGameMode().equals(GameMode.ADVENTURE)){
                            player.setGameMode(GameMode.SPECTATOR);
                            new SneakRunnable(player).runTaskTimer(Main.INSTANCE, 0 ,5);
                        }
                    }
                    break;

                case REDSTONE_TORCH_ON:
                    player.performCommand("join");
                    break;
                case SKULL_ITEM:
                case COMPASS:
                    new PluginMessage(player).Notif("Soon");
                    break;
                // TO DO > Hide player Item
            }
        }
    }
}
