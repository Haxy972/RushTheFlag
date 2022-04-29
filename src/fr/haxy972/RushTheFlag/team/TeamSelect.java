package fr.haxy972.RushTheFlag.team;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.commands.CommandKits;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            event.setCancelled(true);
            if (item.getType().equals(Material.WOOL)) {

                if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRouge")){
                    if(!teamRouge.contains(player) && !teamBleu.contains(player)) {
                        player.closeInventory();
                        player.sendMessage(Main.getPrefix() + "§7Vous avez rejoint l'équipe §c§lRouge");
                        teamRouge.add(player);
                        joinTeam(player, "rouge");
                    }else if(teamBleu.contains(player)) {

                        teamBleu.remove(player);
                        player.sendMessage(Main.getPrefix() + "§cERREUR ACTION IMPOSSIBLE");
                        player.kickPlayer(Main.getPrefix() + "§cErreur Système");

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
                        teamRouge.remove(player);
                        player.sendMessage(Main.getPrefix() + "§cERREUR ACTION IMPOSSIBLE");
                        player.kickPlayer(Main.getPrefix() + "§cErreur Système");

                    }else{
                        player.sendMessage(Main.getPrefix() + "§cVous êtes déjà dans cette équipe");
                    }

                }
            }

    }

    private void joinTeam(Player player, String team) {
        if(team.equalsIgnoreCase("rouge")){
            player.teleport(Main.getSpawnRouge());
            player.setGameMode(GameMode.SURVIVAL);
        }else if(team.equalsIgnoreCase("bleu")){
            player.teleport(Main.getSpawnBleu());
            player.setGameMode(GameMode.SURVIVAL);


        }
        kitChoose(player);





    }


    public static Map<String, String> kitCorrespondance = new HashMap<>();

    public static void kitChoose(Player player) {

        player.sendMessage(Main.getPrefix() + "§aVeuillez choisir un kit");
        Inventory kitInventory = Bukkit.createInventory(player, 9, "§b§lKits");
//        ItemStack kitguerrier = new ItemStack(Material.IRON_SWORD);
//        ItemMeta gmeta = kitguerrier.getItemMeta();
//        gmeta.setDisplayName("§e§lGuerrier");
//        gmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        kitguerrier.setItemMeta(gmeta);
//
//
//        ItemStack kitarcher = new ItemStack(Material.BOW);
//        ItemMeta kmeta = kitarcher.getItemMeta();
//        kmeta.setDisplayName("§a§lArcher");
//        kmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        kitarcher.setItemMeta(kmeta);
//        kitInventory.setItem(1, kitarcher);
//        kitInventory.setItem(0, kitguerrier);


        File file = new File("plugins/RushTheFlag/kits/");
        ArrayList<String> list = new ArrayList<>();
        try{
            if(file.isDirectory()){
                for(File files : file.listFiles()){
                    list.add(files.getName());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        for(int i = 0; i < list.size(); i++){

            ItemStack item = CommandKits.getKit(list.get(i).replace(".yml", ""));

            if(!kitCorrespondance.containsKey(list.get(i))){
                kitCorrespondance.put(item.getItemMeta().getDisplayName(), list.get(i));
            }
            kitInventory.setItem(0 + i , CommandKits.getKit(list.get(i).replace(".yml", "")));
        }


        player.openInventory(kitInventory);
    }

    @EventHandler
    public void kitInteract(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        Inventory inventory = event.getInventory();


        if (item == null) {
            return;
        }
        if (inventory.getName().equalsIgnoreCase("§b§lKits")){
            event.setCancelled(true);
            player.getInventory().clear();

            File file = new File("plugins/RushTheFlag/kits/");
            ArrayList<String> displayedNames = new ArrayList<>();
            try {
                if (file.isDirectory()) {
                    for (File files : file.listFiles()) {
                        YamlConfiguration yaml = new YamlConfiguration();
                        yaml.load(files);
                        displayedNames.add(yaml.getString("display-name"));


                    }

                }
            }catch(Exception e){
                e.printStackTrace();
            }
            boolean isHere = false;
            String clickedOn = null;

            for(String is : displayedNames){
                if(is.replace("&", "§").equalsIgnoreCase(item.getItemMeta().getDisplayName())){
                    isHere= true;
                    clickedOn = kitCorrespondance.get(item.getItemMeta().getDisplayName());
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f,1f);
                    player.sendMessage(Main.getPrefix() + "§7Vous avez choisi le kit: " + item.getItemMeta().getDisplayName());
                }
            }

            if(isHere){
                CommandKits.getKitContent(player, clickedOn.replace(".yml", ""));
                if(!hasKit.contains(player)){
                    hasKit.add(player);
                }
                player.closeInventory();

            }


        }
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if(hasKit.contains(player)){
            hasKit.remove(player);
        }
        if(teamRouge.contains(player)){
            teamRouge.remove(player);
        }
        if(teamBleu.contains(player)){
            teamBleu.remove(player);
        }
    }



    @EventHandler
    public void onEntityDura(PlayerItemDamageEvent event){
        Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
            @Override
            public void run() {
                event.getItem().setDurability((short)0);
            }
        }, 3);
    }



    public static ArrayList<Player> hasKit = new ArrayList<>();
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if(event.getInventory().getName().equalsIgnoreCase("§b§lKits")) {
            if (!hasKit.contains(player)) {
                player.sendMessage(Main.getPrefix() + "§cVous n'avez pas choisi de kit");
                Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        if(Bukkit.getOnlinePlayers().contains(player)) {
                            kitChoose(player);
                        }
                    }
                }, 1/100000000);

            }
        }
    }
}
