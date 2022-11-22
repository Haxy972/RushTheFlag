package fr.haxy972.RushTheFlag.Menus;

import fr.haxy972.RushTheFlag.Managers.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TeamSelector {
    Player player;
    Inventory inventory = Bukkit.createInventory(player, 9, "§cKit Selector");
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
