package fr.haxy972.RushTheFlag.team;

import fr.haxy972.RushTheFlag.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TeamSelect implements Listener {
    public static ArrayList<Player> teamRouge = new ArrayList<>();
    public static ArrayList<Player> teamBleu = new ArrayList<>();



    public static void openTeamSelectInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9, "§8Equipes§8§l»");

        //laine rouge
        ItemStack it = new ItemStack(Material.WOOL, 1, (byte)14);
        ItemMeta im = it.getItemMeta();
        im.setDisplayName("§c§lRouge");
        it.setItemMeta(im);
        inventory.setItem(3, it);

        //laine bleu
        it = new ItemStack(Material.WOOL, 1, (byte)11);
        im = it.getItemMeta();
        im.setDisplayName("§9§lBleu");
        it.setItemMeta(im);
        inventory.setItem(5, it);




        player.openInventory(inventory);
    }

    @EventHandler
    public void PlayerInteract(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        Inventory inventory = event.getInventory();


        if (item == null) {
            return;
        }
        if(inventory.getName().equalsIgnoreCase("§8Equipes§8§l»"))
            if (item.getType().equals(Material.WOOL)) {

                if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRouge")){
                    if(!teamRouge.contains(player) && !teamBleu.contains(player)) {
                        player.closeInventory();
                        player.sendMessage(Main.getPrefix() + "§7Vous avez rejoint l'équipe §c§lRouge");
                        teamRouge.add(player);
                        joinTeam(player, "rouge");
                    }else if(teamBleu.contains(player)) {
                        player.closeInventory();
                        player.sendMessage(Main.getPrefix() + "§7Vous avez rejoint l'équipe §c§lRouge");
                        teamBleu.remove(player);
                        teamRouge.add(player);
                        joinTeam(player, "rouge");
                    }else{
                        player.sendMessage(Main.getPrefix() + "§c Vous êtes déjà dans cette équipe");
                    }
                }


                if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§9§lBleu")) {
                    if(!teamBleu.contains(player) && !teamRouge.contains(player)) {
                        player.closeInventory();
                        player.sendMessage(Main.getPrefix() + "§7Vous avez rejoint l'équipe §9§lBleu");
                        teamBleu.add(player);
                        joinTeam(player, "bleu");
                    }else if(teamRouge.contains(player)){
                        player.closeInventory();
                        player.sendMessage(Main.getPrefix() + "§7Vous avez rejoint l'équipe §9§lBleu");
                        teamRouge.remove(player);
                        teamBleu.add(player);
                        joinTeam(player, "bleu");

                    }else{
                        player.sendMessage(Main.getPrefix() + "§c Vous êtes déjà dans cette équipe");
                    }

                }
            }

    }

    private void joinTeam(Player player, String team) {
        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 1f);
        if(team.equalsIgnoreCase("rouge")){
            player.teleport(Main.getSpawnRouge());
            player.setGameMode(GameMode.SURVIVAL);
        }else if(team.equalsIgnoreCase("bleu")){
            player.teleport(Main.getSpawnBleu());
            player.setGameMode(GameMode.SURVIVAL);
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 1f);
        }


    }
}
