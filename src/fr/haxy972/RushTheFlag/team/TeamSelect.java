package fr.haxy972.RushTheFlag.team;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamSelect {




    public static void openTeamSelectInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9, "§b§lChoisir son équipe");

        //laine rouge
        ItemStack it = new ItemStack(Material.WOOL, 1, (byte)14);
        ItemMeta im = it.getItemMeta();
        im.setDisplayName("§c§lÉquipe Rouge");
        inventory.setItem(3, it);

        //laine bleu
        it = new ItemStack(Material.WOOL, 1, (byte)11);
        im = it.getItemMeta();
        im.setDisplayName("§9§lÉquipe Bleu");
        inventory.setItem(5, it);




        player.openInventory(inventory);
    }
}
