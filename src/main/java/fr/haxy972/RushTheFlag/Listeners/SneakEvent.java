package fr.haxy972.RushTheFlag.Listeners;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Utils.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.HashMap;

public class SneakEvent implements Listener {
    private static final HashMap<Player, Integer> sneakCount = new HashMap<>();

    @EventHandler
    public void onSneakEvent(PlayerToggleSneakEvent event){
        // Switching player GameMode on double sneak
        Player player = event.getPlayer();
        if(!new GameManager().getPlayerInGameList().contains(player)) {
            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                if(!sneakCount.containsKey(player)){sneakCount.put(player, 1);}else{sneakCount.replace(player,sneakCount.get(player) + 1);}
                if(sneakCount.get(player) >= 3){
                    if(player.getLocation().getBlock().getType() == Material.AIR) {
                        new GameManager(player).setJoinAttributes();
                        sneakCount.remove(player);
                    }else{
                        new PluginMessage(player).Err("Spawn impossible you are currently in a block");
                        sneakCount.remove(player);
                    }
                }


                // This part of code is to make a delay
                Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        if(sneakCount.containsKey(player)){
                            if(sneakCount.get(player) > 1){
                                sneakCount.replace(player, sneakCount.get(player) - 1);
                            }else{
                                sneakCount.remove(player);
                            }
                        }
                    }
                },5);
            }
        }
    }
}
