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
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class PluginUpdater {
    private static String prefix = Main.INSTANCE.getConfig().getString("prefix").replace("&", "§");
    public static boolean uptodate = false;
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
                uptodate = true;
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
                    uptodate = true;


                }else{
                    console.sendMessage("§7[§eRushTheFlag§7] §e-> §cYour plugin is outdated, you are using §b§l" + currentversion + "§c or the new version is §e§l" + checkBlocked);
                    console.sendMessage("§7[§eRushTheFlag§7] §e-> §eType §b§l\"/rtf update\" §ein the chat");
                }
            } catch (Throwable e) {
                console.sendMessage("§7[§eRushTheFlag§7] §e-> §cAn error has encountered when checking update, please check latest version yourself");

            }
        }
    }
    public static String language = Main.INSTANCE.getConfig().getString("language");
    public static void updateVersion(Player player){

        if(uptodate){
            if(language.equalsIgnoreCase("fr")){
                player.sendMessage(Main.getPrefix() + "§cVous possedez la derniere version du plugin");
            }else if (language.equalsIgnoreCase("es")){
                player.sendMessage(Main.getPrefix() + "§cUsted tiene la última versión del plugin");
            }else{
                player.sendMessage(Main.getPrefix() + "§cYou have the latest version of the plugin");
            }
            return;
        }




        File file = new File("/plugins/RushTheFlag/");
        try{

            URL url = new URL("https://raw.githubusercontent.com/Haxy972/RushTheFlag/latest/jar/RushTheFlag.jar");
            Path path = Paths.get("plugins/RushTheFlag-latest.jar");
            if(language.equalsIgnoreCase("fr")){
                player.sendMessage(Main.getPrefix() + "§6Telechargement du fichier...");
            }else if (language.equalsIgnoreCase("es")){
                player.sendMessage(Main.getPrefix() + "§6Descarga de archivos...");
            }else{
                player.sendMessage(Main.getPrefix() + "§6Downloading file...");
            }
            Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                @Override
                public void run() {
                    try (InputStream inputStream = url.openStream()){
                        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(language.equalsIgnoreCase("fr")){
                        player.sendMessage(Main.getPrefix() + "§aLe plugin a ete telecharge. §eFichier: §7RushTheFlag-latest.jar");
                        player.sendMessage(Main.getPrefix() + "§cTous les fichiers de configurations ont ete supprimes");
                        player.sendMessage(Main.getPrefix() + "§eVeuillez supprimer l'ancien fichier du plugin !");
                    }else if (language.equalsIgnoreCase("es")){
                        player.sendMessage(Main.getPrefix() + "§aEl plugin ha sido descargado.. §eArchivo: §7RushTheFlag-latest.jar");
                        player.sendMessage(Main.getPrefix() + "§cTodos los archivos de configuracion han sido eliminados");
                        player.sendMessage(Main.getPrefix() + "§ePor favor, elimine la version anterior en su explorador de archivos");
                    }else {
                        player.sendMessage(Main.getPrefix() + "§aThe plugin have been downloaded. §eFile: §7RushTheFlag-latest.jar");
                        player.sendMessage(Main.getPrefix() + "§cAll configuration files have been deleted");
                        player.sendMessage(Main.getPrefix() + "§ePlease delete the older version in your file explorer");
                    }

                    if(file.isDirectory()){
                        file.delete();
                    }
                }
            },20);





        }catch(Exception e){
            e.printStackTrace();
            Bukkit.broadcastMessage(Main.getPrefix() + "§cUne erreur est survenue lors du téléchargement du plugin.");
        }


    }
}