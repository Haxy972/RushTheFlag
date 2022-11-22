package fr.haxy972.RushTheFlag.Menus;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Managers.SoundManager;
import fr.haxy972.RushTheFlag.Utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class TeamSelector {
    Player player;
    GameManager gameManager = new GameManager();
    Inventory inventory = Bukkit.createInventory(player, 9, "§eKit Selector §8- §7" +  gameManager.getPlayerInGameCount() + "/" + gameManager.getGameMaxPlayer());
    public TeamSelector(Player player) {
        this.player = player;
        addItems();
    }

    private void addItems() {

        inventory.setItem(3,new ItemCreator(Material.WOOL, (byte)11).setName("§9Blue Team").setArrayLore(getArrayLore(1)).done());
        inventory.setItem(5,new ItemCreator(Material.WOOL, (byte)14).setName("§cRed Team").setLores("§7Click to join").done());
    }

    private ArrayList<String> getArrayLore(int i) {
        ArrayList<String> teamMenuLore = new ArrayList<>();
        teamMenuLore.add("");
        return teamMenuLore;
    }


    public void open() {
        player.openInventory(inventory);
        new SoundManager(player).playerSucess();
    }
}
