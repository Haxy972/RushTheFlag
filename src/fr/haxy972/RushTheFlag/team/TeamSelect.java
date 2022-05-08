package fr.haxy972.RushTheFlag.team;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.commands.CommandKits;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import fr.haxy972.RushTheFlag.utils.MessageYaml;
import org.bukkit.*;
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
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeamSelect implements Listener {
    public static ArrayList<Player> teamRouge = new ArrayList<>();
    public static ArrayList<Player> teamBleu = new ArrayList<>();

    private static String language = Main.INSTANCE.getConfig().getString("language").toLowerCase();

    public static void openTeamSelectInventory(Player player) {
        Inventory inventory;
        if(language.equalsIgnoreCase("fr")) {
            inventory = Bukkit.createInventory(player, 9, "§8Equipes§8§l»");
        }else if(language.equalsIgnoreCase("es")) {
            inventory = Bukkit.createInventory(player, 9, "§8Equipos§8§l»");
        }else {
            inventory = Bukkit.createInventory(player, 9, "§8Teams§8§l»");
        }

        //laine rouge
        ItemStack it = new ItemStack(Material.WOOL, 1, (byte)14);
        ItemMeta im = it.getItemMeta();
        if(language.equalsIgnoreCase("fr")) {
            im.setDisplayName("§c§lRouge");

        }else if(language.equalsIgnoreCase("es")){
            im.setDisplayName("§c§lRojo");
        }else{
            im.setDisplayName("§c§lRed");
        }

        ArrayList<String> lore = new ArrayList<String>();
        if(language.equalsIgnoreCase("fr")) {
            lore.add("§7Joueurs§8» §e" + teamRouge.size());
        }else if(language.equalsIgnoreCase("es")){
            lore.add("§7Jugadores§8» §e" + teamRouge.size());
        }else{
            lore.add("§7Players§8» §e" + teamRouge.size());
        }
        im.setLore(lore);
        it.setItemMeta(im);
        inventory.setItem(3, it);

        //laine bleu
        it = new ItemStack(Material.WOOL, 1, (byte)11);
        im = it.getItemMeta();
        if(language.equalsIgnoreCase("fr")) {
            im.setDisplayName("§9§lBleu");

        }else if(language.equalsIgnoreCase("es")){
            im.setDisplayName("§9§lAzul");
        }else{
            im.setDisplayName("§9§lBlue");
        }
        lore = new ArrayList<String>();
        if(language.equalsIgnoreCase("fr")) {
            lore.add("§7Joueurs§8» §e" + teamBleu.size());
        }else if(language.equalsIgnoreCase("es")){
            lore.add("§7Jugadores§8» §e" + teamBleu.size());
        }else{
            lore.add("§7Players§8» §e" + teamBleu.size());
        }
        im.setLore(lore);
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
        if(inventory.getName().equalsIgnoreCase("§8Equipes§8§l»")
                ||inventory.getName().equalsIgnoreCase("§8Teams§8§l»")
                || inventory.getName().equalsIgnoreCase("§8Equipos§8§l»"))
            event.setCancelled(true);
            int margeteam = Main.INSTANCE.getConfig().getInt("game.team.count-tolerance");
            if (item.getType().equals(Material.WOOL)) {
                String name = item.getItemMeta().getDisplayName();


                if (name.equalsIgnoreCase("§c§lRouge")
                        ||name.equalsIgnoreCase("§c§lRed")
                        || name.equalsIgnoreCase("§c§lRojo")){
                    if(!teamRouge.contains(player) && !teamBleu.contains(player)) {
                        if(teamRouge.size() <= teamBleu.size() + margeteam) {
                            player.closeInventory();
                            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.join").replace("&", "§").replace("{team}", "§c§lRouge"));
                            player.setPlayerListName(Main.INSTANCE.getConfig().getString("game.team.tab-name-red").replace("&", "§").replace("{player}", player.getName()));
                            teamRouge.add(player);
                            joinTeam(player, "rouge");
                        }else{
                            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.too-much-1").replace("&", "§"));
                            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.too-much-2").replace("&", "§"));
                            player.closeInventory();
                        }
                    }else if(teamBleu.contains(player)) {
                        teamBleu.remove(player);
                        if(language.equalsIgnoreCase("fr")) {

                            player.sendMessage(Main.getPrefix() + "§cERREUR ACTION IMPOSSIBLE");
                            player.kickPlayer(Main.getPrefix() + "§cErreur Système");
                        }else{
                            player.sendMessage(Main.getPrefix() + "§cERROR IMPOSSIBLE ACTION");
                            player.kickPlayer(Main.getPrefix() + "§cSystem Error");
                        }

                    }else{
                        player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.already").replace("&", "§"));
                    }
                }


                if(name.equalsIgnoreCase("§9§lBleu")
                        ||name.equalsIgnoreCase("§9§lBlue")
                        || name.equalsIgnoreCase("§9§lAzul")) {
                    if(!teamBleu.contains(player) && !teamRouge.contains(player)) {

                        if(teamBleu.size() <= teamRouge.size() + margeteam) {
                            player.closeInventory();
                            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.join").replace("&", "§").replace("{team}", "§9§lBleu"));
                            player.setPlayerListName(Main.INSTANCE.getConfig().getString("game.team.tab-name-blue").replace("&", "§").replace("{player}", player.getName()));

                            teamBleu.add(player);
                            joinTeam(player, "bleu");
                        }else{
                            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.too-much-1").replace("&", "§"));
                            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.too-much-2").replace("&", "§"));
                            player.closeInventory();
                        }
                    }else if(teamRouge.contains(player)){
                        teamRouge.remove(player);

                        if(language.equalsIgnoreCase("fr")) {

                            player.sendMessage(Main.getPrefix() + "§cERREUR ACTION IMPOSSIBLE");
                            player.kickPlayer(Main.getPrefix() + "§cErreur Système");
                        }else if(language.equalsIgnoreCase("es")){
                            player.sendMessage(Main.getPrefix() + "§cERROR ACCIÓN IMPOSIBLE");
                            player.kickPlayer(Main.getPrefix() + "§cError del Sistema");
                        }else{
                            player.sendMessage(Main.getPrefix() + "§cERROR IMPOSSIBLE ACTION");
                            player.kickPlayer(Main.getPrefix() + "§cSystem Error");
                        }

                    }else{
                        player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.already").replace("&", "§"));
                    }

                }
            }

    }

    private void joinTeam(Player player, String team) {
        if(team.equalsIgnoreCase("rouge")){
            player.teleport(Main.getSpawnRouge());
            player.setGameMode(GameMode.SURVIVAL);
            if(ScoreboardManager.scoreboardGame.containsKey(player)){
                if(language.equalsIgnoreCase("fr")) {
                    ScoreboardManager.scoreboardGame.get(player).setLine(7, "§fEquipe: §c§lRouge");
                }else if(language.equalsIgnoreCase("es")){
                    ScoreboardManager.scoreboardGame.get(player).setLine(7, "§fEquipo: §c§lRojo");
                }else{
                    ScoreboardManager.scoreboardGame.get(player).setLine(7, "§fTeam: §c§lRed");
                }
            }
        }else if(team.equalsIgnoreCase("bleu")){
            player.teleport(Main.getSpawnBleu());
            player.setGameMode(GameMode.SURVIVAL);
            if(ScoreboardManager.scoreboardGame.containsKey(player)){
                if(language.equalsIgnoreCase("fr")) {
                    ScoreboardManager.scoreboardGame.get(player).setLine(7, "§fEquipe: §9§lBleu");
                }else if(language.equalsIgnoreCase("es")){
                    ScoreboardManager.scoreboardGame.get(player).setLine(7, "§fEquipo: §9§lAzul");
                }else{
                    ScoreboardManager.scoreboardGame.get(player).setLine(7, "§fTeam: §9§lBlue");
                }
            }


        }
        kitChoose(player);





    }


    public static Map<String, String> kitCorrespondance = new HashMap<>();

    public static void kitChoose(Player player) {


        int range = Main.INSTANCE.getConfig().getInt("kits.inventory-rows");
        player.sendMessage(Main.getPrefix() + MessageYaml.getValue("kits.choose-kit").replace("&", "§"));
        Inventory kitInventory = Bukkit.createInventory(player, range*9, "§b§lKits");



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
        File show = new File("plugins/RushTheFlag/kits/");
        boolean havefile = false;
        try {
            if(show.isDirectory()) {
                for(File file2 : show.listFiles()){
                    havefile = true;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        if(!havefile){
            ItemStack item = new ItemStack(Material.BARRIER);
            ItemMeta im = item.getItemMeta();
            if(language.equalsIgnoreCase("fr")) {
                im.setDisplayName("§cAuncun");
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7Vérifiez la configuration du plugin");
                lore.add("§ePour en ajouter: §c/rtf kits add <nom>");
                im.setLore(lore);
                item.setItemMeta(im);
            }else{
                im.setDisplayName("§cNone");
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7Check the plugin configuration");
                lore.add("§eTo add kits: §c/rtf kits add <nom>");
                im.setLore(lore);
                item.setItemMeta(im);
            }
            kitInventory.setItem(0, item);
        }

        player.openInventory(kitInventory);
    }
    public static Map<Player, String> playerKit = new HashMap<>();
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

                    player.sendMessage(Main.getPrefix() + MessageYaml.getValue("kits.chosen-kit").replace("&", "§").replace("{kit}", "" + item.getItemMeta().getDisplayName()));

                    if(ScoreboardManager.scoreboardGame.containsKey(player)){
                        ScoreboardManager.scoreboardGame.get(player).setLine(2, "§fKit: §r" + item.getItemMeta().getDisplayName());
                    }


                }
            }

            if(isHere){
                CommandKits.getKitContent(player, clickedOn.replace(".yml", ""));
                if(!hasKit.contains(player)){
                    hasKit.add(player);
                }
                if(playerKit.containsKey(player)){

                }
                playerKit.put(player, clickedOn);
                player.closeInventory();

            }
            Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                @Override
                public void run() {
                    replaceArmor(player);
                }
            }, 1);


        }
    }

    public static void replaceArmor(Player player) {

        ItemStack[] armor = player.getInventory().getArmorContents();
        String team = null;
        if(teamRouge.contains(player)){
            team = "rouge";
        }else if(teamBleu.contains(player)){
            team = "bleu";
        }

        if(armor[0].getType() == Material.LEATHER_BOOTS){
            LeatherArmorMeta armormeta = (LeatherArmorMeta) armor[0].getItemMeta();

            if(teamBleu.equals(team.equalsIgnoreCase("bleu"))){
                Color color = Color.fromRGB(51, 76,178);
                armormeta.setColor(color);
            }else if(team.equalsIgnoreCase("rouge")){
                Color color = Color.fromRGB(153, 51,51);
                armormeta.setColor(color);
            }else{
            }
            armor[0].setItemMeta(armormeta);
        }
        if(armor[1].getType() == Material.LEATHER_LEGGINGS){
            LeatherArmorMeta armormeta = (LeatherArmorMeta) armor[1].getItemMeta();

            if(teamBleu.equals(team.equalsIgnoreCase("bleu"))){
                Color color = Color.fromRGB(51, 76,178);
                armormeta.setColor(color);
            }else if(team.equalsIgnoreCase("rouge")){
                Color color = Color.fromRGB(153, 51,51);
                armormeta.setColor(color);
            }

            armor[1].setItemMeta(armormeta);
        }
        if(armor[2].getType() == Material.LEATHER_CHESTPLATE){
            LeatherArmorMeta armormeta = (LeatherArmorMeta) armor[2].getItemMeta();

            if(teamBleu.equals(team.equalsIgnoreCase("bleu"))){
                Color color = Color.fromRGB(51, 76,178);
                armormeta.setColor(color);
            }else if(team.equalsIgnoreCase("rouge")){
                Color color = Color.fromRGB(153, 51,51);
                armormeta.setColor(color);
            }
            armor[2].setItemMeta(armormeta);
        }
        if(armor[3].getType() == Material.LEATHER_HELMET){
            LeatherArmorMeta armormeta = (LeatherArmorMeta) armor[3].getItemMeta();
            if(teamBleu.equals(team.equalsIgnoreCase("bleu"))){
                Color color = Color.fromRGB(51, 76,178);
                armormeta.setColor(color);
            }else if(team.equalsIgnoreCase("rouge")){
                Color color = Color.fromRGB(153, 51,51);
                armormeta.setColor(color);
            }
            armor[3].setItemMeta(armormeta);
        }

        player.getInventory().setArmorContents(armor);



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
            File show = new File("plugins/RushTheFlag/kits/");
            boolean havefile = false;
            try {
                if(show.isDirectory()) {
                    for(File file2 : show.listFiles()){
                        havefile = true;
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            if(!havefile){
                hasKit.add(player);
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 2,2);
            }

            if (!hasKit.contains(player)) {
                player.sendMessage(Main.getPrefix() + MessageYaml.getValue("kits.please-choose").replace("&", "§"));
                Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        if(Bukkit.getOnlinePlayers().contains(player)) {
                            kitChoose(player);
                        }
                    }
                }, 1/100000000);

            }
            if(!havefile){
                hasKit.remove(player);
                if(language.equalsIgnoreCase("fr")) {
                    player.sendMessage(Main.getPrefix() + "§cVeuillez modifier la configuration du plugin");
                    player.sendMessage(Main.getPrefix() + "§eAucun kit enregistré");
                } else{
                    player.sendMessage(Main.getPrefix() + "§cPlease modify the plugin configuration");
                    player.sendMessage(Main.getPrefix() + "§eNo kit registred");
                }
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 2,2);
            }
        }
    }
}
