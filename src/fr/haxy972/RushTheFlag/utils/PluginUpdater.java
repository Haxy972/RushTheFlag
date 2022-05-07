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
import org.bukkit.command.ConsoleCommandSender;
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

        ConsoleCommandSender console = Main.INSTANCE.getServer().getConsoleSender();


        String currentversion = plugin.getDescription().getVersion();
        console.sendMessage("§7[§eRushTheFlag§7] §e-> §bChecking plugin version, Please wait...");
        try {
            URL url = new URL("https://raw.githubusercontent.com/user-name/repo-name/main/latest.txt".replace("user-name", user).replace("repo-name", repo));
            InputStream is = url.openStream();
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            String checkBlocked = br.readLine();
            if (checkBlocked.equals(currentversion)) {;
                console.sendMessage("§7[§eRushTheFlag§7] §e-> §aPlugin up to date");

            }else{
                console.sendMessage("§7[§eRushTheFlag§7] §e-> §cYour plugin is outdated, you are using §b§l" + currentversion + "§c or the new version is §e§l" + checkBlocked);
                console.sendMessage("§7[§eRushTheFlag§7] §e -> §eType §b§l\"/rtf update\" §ein the chat");
            }
        } catch (Throwable t) {
            try {
                URL url = new URL(Objects.requireNonNull(
                        Objects.requireNonNull("https://cdn.jsdelivr.net/gh/user-name/repo-name/latest.txt")
                                .replace("user-name", user).replace("repo-name", repo)));
                InputStream is = url.openStream();
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ir);
                String checkBlocked = br.readLine();
                if (checkBlocked.equals(currentversion)) {
                    console.sendMessage("§7[§eRushTheFlag§7] -§e> §aPlugin up to date");

                }else{
                    console.sendMessage("§7[§eRushTheFlag§7] §e-> §cYour plugin is outdated, you are using §b§l" + currentversion + "§c or the new version is §e§l" + checkBlocked);
                    console.sendMessage("§7[§eRushTheFlag§7] §e-> §eType §b§l\"/rtf update\" §ein the chat");
                }
            } catch (Throwable e) {
                console.sendMessage("§7[§eRushTheFlag§7] §e-> §cAn error has encountered when checking update, please check latest version yourself");

            }
        }
    }
}