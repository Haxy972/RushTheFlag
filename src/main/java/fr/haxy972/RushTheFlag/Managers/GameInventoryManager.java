package fr.haxy972.RushTheFlag.Managers;

import fr.haxy972.RushTheFlag.Utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GameInventoryManager {

    Player player;
    public GameInventoryManager(Player player){
        this.player = player;
    }


    public void clear() {
        player.getInventory().clear();
    }
}
