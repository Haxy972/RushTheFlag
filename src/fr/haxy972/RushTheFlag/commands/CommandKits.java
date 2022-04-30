package fr.haxy972.RushTheFlag.commands;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandKits implements CommandExecutor {
    private static Map<Integer, ItemStack> kit = new HashMap<>();

    private String kitName;

    public static ItemStack getKit(String kitName) {
        File file = new File("plugins/RushTheFlag/kits/" + kitName + ".yml");
        try {
            if (file.isFile()) {
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.load(file);
                Material mat = Material.getMaterial(yaml.getString("display-item.type"));
                int amount = yaml.getInt("display-item.amount");
                ItemStack it = new ItemStack(mat, amount);
                ItemMeta im = it.getItemMeta();
                ArrayList<String> lore = (ArrayList) yaml.getStringList("display-item.lore");
                int i = 0;
                for(String lores : lore){

                    lore.set(i, lores.replace("&", "§"));
                    i++;

                }

                im.setLore(lore);
                im.setDisplayName(yaml.getString("display-name").replace("&", "§"));
                im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                it.setItemMeta(im);
                return it;

            }
        } catch (Exception e) {
            int test = 0;
            if (test == 0) {
                Bukkit.broadcastMessage(Main.getPrefix() + "§cUne erreur est survenue lors du chargement du kit: §e§l" + kitName);
            }
            test ++;


        }
        return null;
    }

    public static void getKitContent(Player player, String kitName){
        File file = new File("plugins/RushTheFlag/kits/" + kitName + ".yml");
        try {
            if (file.isFile()) {
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.load(file);
                //ARMURES
                ItemStack[] armor = player.getInventory().getArmorContents();

                if(yaml.contains("items.armor.helmet")){
                    ItemStack helmet = new ItemStack(Material.getMaterial(yaml.getString("items.armor.helmet.type")), yaml.getInt("items.armor.helmet.amount"));
                    armor[3] = helmet;
                    if (yaml.contains("items.armor.helmet.color")) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) armor[3].getItemMeta();
                        int R = yaml.getInt("items.armor.helmet.color.R");
                        int G = yaml.getInt("items.armor.helmet.color.G");
                        int B = yaml.getInt("items.armor.helmet.color.B");
                        meta.setColor(Color.fromRGB(R, G, B));
                        armor[3].setItemMeta(meta);
                    }
                    if (yaml.contains("items.armor.helmet.enchants")){
                        for (String enchant : yaml.getKeys(true)) {

                            if(enchant.contains("enchants") && enchant.contains("armor") && enchant.contains("helmet.enchants.")){
                                String enchantName = enchant.replace("items.armor.helmet.enchants", "").replace(".", "");
                                Integer enchantLevel = yaml.getInt("items.armor.helmet.enchants." + enchantName);
                                armor[3].addEnchantment(Enchantment.getByName(enchantName), enchantLevel);

                            }
                        }
                    }
                }else{
                    armor[3] = new ItemStack(Material.AIR);
                }
                if(yaml.contains("items.armor.chestplate")){
                    ItemStack chestplate = new ItemStack(Material.getMaterial(yaml.getString("items.armor.chestplate.type")), yaml.getInt("items.armor.chestplate.amount"));
                    armor[2] = chestplate;
                    if (yaml.contains("items.armor.chestplate.color")) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) armor[2].getItemMeta();
                        int R = yaml.getInt("items.armor.chestplate.color.R");
                        int G = yaml.getInt("items.armor.chestplate.color.G");
                        int B = yaml.getInt("items.armor.chestplate.color.B");
                        meta.setColor(Color.fromRGB(R, G, B));
                        armor[2].setItemMeta(meta);
                    }
                    if (yaml.contains("items.armor.chestplate.enchants")){
                        for (String enchant : yaml.getKeys(true)) {

                            if(enchant.contains("enchants") && enchant.contains("armor") && enchant.contains("chestplate.enchants.")){
                                String enchantName = enchant.replace("items.armor.chestplate.enchants", "").replace(".", "");
                                Integer enchantLevel = yaml.getInt("items.armor.chestplate.enchants." + enchantName);
                                armor[2].addEnchantment(Enchantment.getByName(enchantName), enchantLevel);

                            }
                        }
                    }


                }else{
                    armor[2] = new ItemStack(Material.AIR);
                }
                if(yaml.contains("items.armor.leggings")){
                    ItemStack leggings = new ItemStack(Material.getMaterial(yaml.getString("items.armor.leggings.type")), yaml.getInt("items.armor.leggings.amount"));
                    armor[1] = leggings;
                    if (yaml.contains("items.armor.leggings.color")) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) armor[1].getItemMeta();
                        int R = yaml.getInt("items.armor.leggings.color.R");
                        int G = yaml.getInt("items.armor.leggings.color.G");
                        int B = yaml.getInt("items.armor.leggings.color.B");
                        meta.setColor(Color.fromRGB(R, G, B));
                        armor[1].setItemMeta(meta);
                    }
                    if (yaml.contains("items.armor.leggings.enchants")){
                        for (String enchant : yaml.getKeys(true)) {

                            if(enchant.contains("enchants") && enchant.contains("armor") && enchant.contains("leggings.enchants.")){
                                String enchantName = enchant.replace("items.armor.leggings.enchants", "").replace(".", "");
                                Integer enchantLevel = yaml.getInt("items.armor.leggings.enchants." + enchantName);
                                armor[1].addEnchantment(Enchantment.getByName(enchantName), enchantLevel);

                            }
                        }
                    }


                }else{
                    armor[1] = new ItemStack(Material.AIR);
                }
                if(yaml.contains("items.armor.boots")){
                    ItemStack boots = new ItemStack(Material.getMaterial(yaml.getString("items.armor.boots.type")), yaml.getInt("items.armor.boots.amount"));
                    armor[0] = boots;
                    if (yaml.contains("items.armor.boots.color")) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) armor[0].getItemMeta();
                        int R = yaml.getInt("items.armor.boots.color.R");
                        int G = yaml.getInt("items.armor.boots.color.G");
                        int B = yaml.getInt("items.armor.boots.color.B");
                        meta.setColor(Color.fromRGB(R, G, B));
                        armor[0].setItemMeta(meta);

                    }
                    if (yaml.contains("items.armor.boots.enchants")){
                        for (String enchant : yaml.getKeys(true)) {

                            if(enchant.contains("enchants") && enchant.contains("armor") && enchant.contains("boots.enchants.")){
                                String enchantName = enchant.replace("items.armor.boots.enchants", "").replace(".", "");
                                Integer enchantLevel = yaml.getInt("items.armor.boots.enchants." + enchantName);
                                armor[0].addEnchantment(Enchantment.getByName(enchantName), enchantLevel);

                            }
                        }
                    }

                }else{
                    armor[0] = new ItemStack(Material.AIR);
                }

                player.getInventory().setArmorContents(armor);
                //FIN ARMURE

                for (String count : yaml.getKeys(true)) {

                    for(int i = 0; i < 36; i++){



                        if(count.equals("items." + i)){

                            if(!yaml.getString(count).equalsIgnoreCase("null")) {


                                ItemStack item = new ItemStack(Material.getMaterial(yaml.getString("items." + i + ".type")), yaml.getInt("items." + i + ".amount"));

                                if (yaml.contains("items." + i + ".data")) {
                                    byte data = (byte) yaml.getInt("items." + i + ".data");
                                    item = new ItemStack(Material.getMaterial(yaml.getString("items." + i + ".type")), yaml.getInt("items." + i + ".amount"), data);
                                }


                                if (yaml.contains("items." + i + ".enchants")) {
                                    for (String enchant : yaml.getKeys(true)) {
                                        if (enchant.contains("items." + i + ".enchants.")) {
                                            String enchantName = enchant.replace("items." + i + ".enchants", "").replace(".", "");
                                            Integer enchantLevel = yaml.getInt("items." + i + ".enchants." + enchantName);
                                            item.addEnchantment(Enchantment.getByName(enchantName), enchantLevel);

                                        }
                                    }
                                }
                                if (yaml.contains("items." + i + ".color")) {
                                    LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
                                    int R = yaml.getInt("items." + i + ".color.R");
                                    int G = yaml.getInt("items." + i + ".color.G");
                                    int B = yaml.getInt("items." + i + ".color.B");
                                    meta.setColor(Color.fromRGB(R, G, B));
                                    item.setItemMeta(meta);
                                }


                                player.getInventory().setItem(i, item);
                            }else{
                                player.getInventory().setItem(i, new ItemStack(Material.AIR));
                            }
                        }
                    }

                }





            }
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("rushtheflag") ||cmd.getName().equalsIgnoreCase("rtf")) {
            if (args.length > 0) {
                if (args.length >= 1) {

                    if (args[0].equalsIgnoreCase("kits")) {
                        if(args.length >= 2) {
                            if (args[1].equalsIgnoreCase("add")) {
                                if (args.length == 3) {
                                    for (int i = 0; i < 9; i++) {
                                        kit.put(i, player.getInventory().getItem(i));

                                    }
                                    int count = 35;
                                    for (int i = 9; i < 36; i++) {
                                        kit.put(i, player.getInventory().getItem(i));

                                    }
                                    kitName = args[2];

                                    createKit(player);


                                } else {
                                    player.sendMessage(Main.getPrefix() + "§c/rtf kits §eadd §7<nom>");
                                }
                            } else if (args[1].equalsIgnoreCase("remove")) {
                                if (args.length == 3) {
                                    deleteKit(player, args[2]);


                                } else {
                                    player.sendMessage(Main.getPrefix() + "§c/rtf kits §eremove §7<nom>");
                                }

                            } else if (args[1].equalsIgnoreCase("list")) {
                                File show = new File("plugins/RushTheFlag/kits/");
                                try {
                                    if(show.isDirectory()) {
                                        player.sendMessage(Main.getPrefix() + "§7Affichage de la liste des kits:");
                                        for(File file : show.listFiles()){
                                            player.sendMessage("§e- " + file.getName().replace(".yml", ""));
                                        }
                                    }

                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{
                                player.sendMessage(Main.getPrefix() + "§c/rtf §ekits §7<add/remove/list>");
                            }
                        } else {
                        player.sendMessage(Main.getPrefix() + "§c/rtf §ekits §7<add/remove/list>");
                    }

                    } else {
                        player.sendMessage(Main.getPrefix() + "§c/rtf <kits>");
                    }



                } else {
                    player.sendMessage(Main.getPrefix() + "§c/rtf <kits>");
                }
            } else {
                player.sendMessage(Main.getPrefix() + "§c/rtf <kits>");
            }
        }


        return false;
    }

    private void deleteKit(Player player, String name) {
        File delete = new File("plugins/RushTheFlag/kits/" + name + ".yml");
        try {
            if(delete.isFile()){
                delete.delete();
                player.sendMessage(Main.getPrefix() + "§aVous avez supprimé le kit: §e" + name);
            }else{
                player.sendMessage(Main.getPrefix() + "§cCe kit n'existe pas");
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void createKitFolder(){
        File file = new File("plugins/RushTheFlag/kits/");
        try {
            if(!file.isDirectory()){
                file.mkdir();
            }



        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    private void createKit(Player player) {
        File folder = new File("plugins/RushTheFlag/kits/");
        File file = new File("plugins/RushTheFlag/kits/" + kitName + ".yml");
        try {
            if (!folder.isDirectory()) {
                folder.mkdir();
            }
            if (!file.isFile()) {
                player.sendMessage(Main.getPrefix() + "§aVous avez ajouté le kit:§e§l " + kitName);
                player.sendMessage(Main.getPrefix() + "§7Rendez vous dans le fichier de configuration présent dans le dossier §8(§a§l/kits/§c§l" + kitName + ".yml§8)");
                file.createNewFile();
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.set("name", kitName);
                yaml.set("display-name", "&e&l" + kitName);
                yaml.set("display-item.type", "GRASS");
                yaml.set("display-item.amount", 1);
                ArrayList<String> lores = new ArrayList();
                lores.add("&7Ligne 1");
                lores.add("&7Ligne 2");
                yaml.set("display-item.lore", lores);




                ItemStack[] armor = player.getInventory().getArmorContents();
                if (armor[3].getType() != Material.AIR) {
                    yaml.set("items.armor.helmet.type", armor[3].getType().toString());
                    yaml.set("items.armor.helmet.amount", armor[3].getAmount());


                    if(armor[3].getItemMeta() instanceof LeatherArmorMeta) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) armor[3].getItemMeta();
                        if (meta.getColor() != null) {
                            yaml.set("items.armor.helmet.color.R", meta.getColor().getRed());
                            yaml.set("items.armor.helmet.color.G", meta.getColor().getGreen());
                            yaml.set("items.armor.helmet.color.B", meta.getColor().getBlue());
                        }
                    }

                    if (armor[3].getItemMeta().hasEnchants()) {
                        for (Enchantment enchant : armor[3].getEnchantments().keySet()) {
                            yaml.set("items.armor.helmet.enchants." + enchant.getName(), armor[3].getEnchantments().get(enchant));
                        }
                    }
                }

                if (armor[2].getType() != Material.AIR) {
                    yaml.set("items.armor.chestplate.type", armor[2].getType().toString());
                    yaml.set("items.armor.chestplate.amount", armor[2].getAmount());
                    if(armor[2].getItemMeta() instanceof LeatherArmorMeta) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) armor[2].getItemMeta();
                        if (meta.getColor() != null) {
                            yaml.set("items.armor.chestplate.color.R", meta.getColor().getRed());
                            yaml.set("items.armor.chestplate.color.G", meta.getColor().getGreen());
                            yaml.set("items.armor.chestplate.color.B", meta.getColor().getBlue());
                        }
                        if (armor[2].getItemMeta().hasEnchants()) {
                            for (Enchantment enchant : armor[2].getEnchantments().keySet()) {
                                yaml.set("items.armor.chestplate.enchants." + enchant.getName(), armor[2].getEnchantments().get(enchant));
                            }
                        }
                    }

                }


                if (armor[1].getType() != Material.AIR) {
                    yaml.set("items.armor.leggings.type", armor[1].getType().toString());
                    yaml.set("items.armor.leggings.amount", armor[1].getAmount());
                    if(armor[1].getItemMeta() instanceof LeatherArmorMeta) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) armor[1].getItemMeta();
                        if (meta.getColor() != null) {
                            yaml.set("items.armor.leggings.color.R", meta.getColor().getRed());
                            yaml.set("items.armor.leggings.color.G", meta.getColor().getGreen());
                            yaml.set("items.armor.leggings.color.B", meta.getColor().getBlue());
                        }
                        if (armor[1].getItemMeta().hasEnchants()) {
                            for (Enchantment enchant : armor[1].getEnchantments().keySet()) {
                                yaml.set("items.armor.leggings.enchants." + enchant.getName(), armor[1].getEnchantments().get(enchant));
                            }
                        }
                    }

                }

                if (armor[0].getType() != Material.AIR) {
                    yaml.set("items.armor.boots.type", armor[0].getType().toString());
                    yaml.set("items.armor.boots.amount", armor[0].getAmount());
                    if(armor[0].getItemMeta() instanceof LeatherArmorMeta) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) armor[0].getItemMeta();
                        if (meta.getColor() != null) {
                            yaml.set("items.armor.boots.color.R", meta.getColor().getRed());
                            yaml.set("items.armor.boots.color.G", meta.getColor().getGreen());
                            yaml.set("items.armor.boots.color.B", meta.getColor().getBlue());
                        }
                        if (armor[0].getItemMeta().hasEnchants()) {
                            for (Enchantment enchant : armor[0].getEnchantments().keySet()) {
                                yaml.set("items.armor.boots.enchants." + enchant.getName(), armor[0].getEnchantments().get(enchant));
                            }
                        }
                    }

                }


                for (int i = 0; i < 36; i++) {
                    if (kit.get(i) == null) {
                        yaml.set("items." + i, "null");
                    } else {
                        ItemStack it = kit.get(i);

                        yaml.set("items." + i + ".name", it.getItemMeta().getDisplayName());
                        yaml.set("items." + i + ".type", it.getType().toString());
                        yaml.set("items." + i + ".amount", it.getAmount());
                        if (!(it.getData().getData() == (byte) 0)) {
                            yaml.set("items." + i + ".data", it.getData().getData());
                        }

                        if(it.getItemMeta() instanceof LeatherArmorMeta){
                            LeatherArmorMeta armorMeta = (LeatherArmorMeta) it.getItemMeta();
                            if(armorMeta.getColor() != null){
                                yaml.set("items." + i + ".color.R", armorMeta.getColor().getRed());
                                yaml.set("items." + i + ".color.G", armorMeta.getColor().getGreen());
                                yaml.set("items." + i + ".color.B", armorMeta.getColor().getBlue());
                            }

                        }

                        if (it.getItemMeta().hasEnchants()) {
                            for (Enchantment enchant : it.getEnchantments().keySet()) {
                                yaml.set("items." + i + ".enchants." + enchant.getName(), it.getEnchantments().get(enchant));
                            }
                        }

                    }
                }


                yaml.save(file);

            } else {
                player.sendMessage(Main.getPrefix() + "§cCe kit existe déjà");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
