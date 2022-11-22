package fr.haxy972.RushTheFlag.Menus;

import fr.haxy972.RushTheFlag.Managers.SoundManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TeamSelector {
    Player player;
    Inventory inventory;
    public TeamSelector(Player player) {
        this.player = player;
        addItems();
    }

    private void addItems() {

    }


    public void open() {
        player.openInventory(inventory);
        new SoundManager(player).playerSucess();
    }
}
