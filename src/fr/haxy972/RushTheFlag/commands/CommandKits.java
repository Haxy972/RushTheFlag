package fr.haxy972.RushTheFlag.commands;

import fr.haxy972.RushTheFlag.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
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
                ItemStack it = new ItemStack(mat);
                ItemMeta im = it.getItemMeta();
                im.setDisplayName(yaml.getString("display-name").replace("&", "§"));
                it.setItemMeta(im);
                Bukkit.broadcastMessage(it.toString());
                return it;

            }
        } catch (Exception e) {
            e.printStackTrace();


        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("admkits")) {
            if (args.length > 0) {
                if (args.length >= 1) {

                    if (args[0].equalsIgnoreCase("add")) {
                        if (args.length == 2) {
                            for (int i = 0; i < 37; i++) {
                                kit.put(i, player.getInventory().getItem(i));

                            }
                            kitName = args[1];

                            createKit(player);


                        } else {
                            player.sendMessage(Main.getPrefix() + "§c/admkits <add> <nom>");
                        }
                    } else {
                        player.sendMessage(Main.getPrefix() + "§c/admkits <add/remove>");
                    }

                    if (args[0].equalsIgnoreCase("open")) {
                        player.getInventory().clear();
                        player.sendMessage(kit.toString());

                        for (int i = 0; i < 37; i++) {
                            player.getInventory().setItem(i, kit.get(i));

                        }
                    }

                } else {
                    player.sendMessage(Main.getPrefix() + "§c/admkits <add/remove>");
                }
            } else {
                player.sendMessage(Main.getPrefix() + "§c/admkits <add/remove>");
            }
        }


        return false;
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
                file.createNewFile();
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.set("name", kitName);
                yaml.set("display-name", "&b" + kitName);
                yaml.set("display-item.type", "GRASS_BLOCK");


                ItemStack[] armor = player.getInventory().getArmorContents();
                if (armor[3].getType() != null) {
                    yaml.set("items.armor.helmet.type", armor[3].getType().toString());
                    yaml.set("items.armor.helmet.amount", armor[3].getAmount());

                    if (armor[3].getItemMeta().hasEnchants()) {
                        for (Enchantment enchant : armor[3].getEnchantments().keySet()) {
                            yaml.set("items.armor.helmet.enchants." + enchant.getName(), armor[3].getEnchantments().get(enchant));
                        }
                    }
                }

                if (armor[2].getType() != null) {
                    yaml.set("items.armor.chestplate.type", armor[2].getType().toString());
                    yaml.set("items.armor.chestplate.amount", armor[2].getAmount());

                    if (armor[2].getItemMeta().hasEnchants()) {
                        for (Enchantment enchant : armor[2].getEnchantments().keySet()) {
                            yaml.set("items.armor.chestplate.enchants." + enchant.getName(), armor[2].getEnchantments().get(enchant));
                        }
                    }
                }


                if (armor[1].getType() != null) {
                    yaml.set("items.armor.leggings.type", armor[1].getType().toString());
                    yaml.set("items.armor.leggings.amount", armor[1].getAmount());

                    if (armor[1].getItemMeta().hasEnchants()) {
                        for (Enchantment enchant : armor[1].getEnchantments().keySet()) {
                            yaml.set("items.armor.leggings.enchants." + enchant.getName(), armor[1].getEnchantments().get(enchant));
                        }
                    }
                }

                if (armor[0].getType() != Material.AIR) {
                    yaml.set("items.armor.boots.type", armor[0].getType().toString());
                    yaml.set("items.armor.boots.amount", armor[0].getAmount());

                    if (armor[0].getItemMeta().hasEnchants()) {
                        for (Enchantment enchant : armor[0].getEnchantments().keySet()) {
                            yaml.set("items.armor.boots.enchants." + enchant.getName(), armor[0].getEnchantments().get(enchant));
                        }
                    }
                }


                for (int i = 0; i < 37; i++) {
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

                        if (it.getItemMeta().hasEnchants()) {
                            for (Enchantment enchant : it.getEnchantments().keySet()) {
                                yaml.set("items." + i + ".enchants." + enchant.getName(), it.getEnchantments().get(enchant));
                            }
                        }

                    }
                    Bukkit.broadcastMessage("" + kit.get(i));
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
