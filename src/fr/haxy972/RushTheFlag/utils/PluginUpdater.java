package fr.haxy972.RushTheFlag.utils;
/**
 * Spigot-CheckUpdate
 *
 * Copyright Katorly Lab
 * (github.com/katorlys)
 *
 * License under GPLv3
 */

import fr.haxy972.RushTheFlag.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

public class PluginUpdater {
    private static String prefix = Main.INSTANCE.getConfig().getString("prefix").replace("&", "§");

    public static void check(JavaPlugin plugin, String user, String repo) {
        String currentversion = plugin.getDescription().getVersion();
        Bukkit.getLogger().info("[RushTheFlag] --> Verification du paiement...");
        try {
            URL url = new URL("https://raw.githubusercontent.com/user-name/repo-name/main/blocked.txt".replace("user-name", user).replace("repo-name", repo));
            InputStream is = url.openStream();
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            String checkBlocked = br.readLine();
            if (checkBlocked.equals("true")) {
                Bukkit.getLogger().info("Version: bloque");
                Bukkit.broadcastMessage(prefix + "§c§lPlugin verouille");
                Bukkit.broadcastMessage(prefix + "§cVous devez payer le plugin");
                Bukkit.broadcastMessage(prefix + "§7Si vous voulez que le plugin refonctionne, veuillez payer");
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.playSound(players.getLocation(), Sound.NOTE_PLING, 2,2);
                }

                Main.blocked = true;
            }else{
                Bukkit.getLogger().info("[RushTheFlag] --> Paiement effectue.");
            }
        } catch (Throwable t) {
            try {
                Bukkit.getLogger().info("[RushTheFlag] --> Erreur lors de la verification, nouvelle tentative");
                URL url = new URL(Objects.requireNonNull(
                        Objects.requireNonNull("https://cdn.jsdelivr.net/gh/user-name/repo-name/blocked.txt")
                                .replace("user-name", user).replace("repo-name", repo)));
                InputStream is = url.openStream();
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ir);
                String checkBlocked = br.readLine();
                if (checkBlocked.equals("true")) {
                    Bukkit.getLogger().info("Version: bloque");
                    Bukkit.broadcastMessage(prefix + "§c§lPlugin verouille");
                    Bukkit.broadcastMessage(prefix + "§cVous devez payer le plugin");
                    Bukkit.broadcastMessage(prefix + "§7Si vous voulez que le plugin refonctionne, veuillez payer");
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.playSound(players.getLocation(), Sound.NOTE_PLING, 2,2);
                    }

                    Main.blocked = true;
                }else{
                    Bukkit.getLogger().info("[RushTheFlag] --> Paiement effectue.");
                }
            } catch (Throwable e) {
                Bukkit.getLogger().info("[RushTheFlag] --> Erreur lors de la verification, veuillez contacter l'auteur du programme");
                Bukkit.broadcastMessage(prefix + "§c§lPlugin verouille");
                Bukkit.broadcastMessage(prefix + "§7Une erreur est survenue lors de la verification du paiement");
                Bukkit.broadcastMessage(prefix + "§7Le plugin est donc §c§lbloque §7jusqu'a nouvel ordre");
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.playSound(players.getLocation(), Sound.NOTE_PLING, 2,2);
                }

                Main.blocked = true;
            }
        }
    }
}