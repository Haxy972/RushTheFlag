package fr.haxy972.RushTheFlag.utils;

import fr.haxy972.RushTheFlag.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.Charset;

public class MessageYaml {


    private static FileConfiguration fileConfiguration;

    public static void checkYaml() {
        File file;
        String language;
        ConsoleCommandSender console = Main.INSTANCE.getServer().getConsoleSender();
        console.sendMessage("§7[§eRushTheFlag§7] §e-> §bLoading languages");
        if(Main.INSTANCE.getConfig().getString("language").equalsIgnoreCase("FR")) {
            file = new File("plugins/RushTheFlag/messages-fr.yml");
            language = "fr";
        }else if(Main.INSTANCE.getConfig().getString("language").equalsIgnoreCase("EN")) {
            file = new File("plugins/RushTheFlag/messages-en.yml");
            language = "en";
        }else{
            Bukkit.broadcastMessage(Main.getPrefix() + "§cLanguage in the config isn't set correctly");
            return;
        }

        try {
            if (!file.isFile()) {

                YamlConfiguration yaml = new YamlConfiguration();




                //yaml.set("messages.nether.new-day-nether", "");
                if(language == "en") {
                    file.createNewFile();

                    console.sendMessage(Main.getPrefix() + "§aLanguage selected: §e§lEN");
                    yaml.set("join.broadcast-message", "&7[&a+&7] &7{player}");
                    yaml.set("join.message-player", "&7Type &e&l/join&7 to join the game");
                    yaml.set("blocks.cant-place", "&cYou can't place block here ");
                    yaml.set("blocks.own-wool", "&cYou can't break your own wool");
                    yaml.set("blocks.cant-place-wool", "&cYou can't place this block");
                    yaml.set("death.title", "&c&lDead");
                    yaml.set("death.subtitle", "&7You will be reappear soon");
                    yaml.set("death.timer", "&eReappearing in {secondes} {unite}");
                    yaml.set("death.end", "&eRespawning...");

                    yaml.set("team.no-one", "&cThere is no one on the opposing team, action impossible");
                    yaml.set("team.take-wool", "&e&l{player}&a has take {wool-color} &awool");
                    yaml.set("team.chat-blue", "&9&lBlue &9{player}&8» &7");
                    yaml.set("team.chat-red", "&c&lRed &c{player}&8» &7");
                    yaml.set("team.chat-default", "&7{player}&8» &7");
                    yaml.set("team.join", "&7You have join the {team} &7team");
                    yaml.set("team.too-much-1", "&cThere are not enough players in the other team");
                    yaml.set("team.too-much-2", "&eSwitch sides or wait :/");
                    yaml.set("team.already", "&cYou're already in this team");
                    yaml.set("team.player-back", "&e{player}&a brought back the wool {wool-color}&a wool in his base");
                    yaml.set("team.lost-wool", "&e{player}&c has lost the {wool-color} &cwool");
                    yaml.set("team.win", "{team} &7team won the game");
                    yaml.set("team.same-team", "&cYou cannot attack your allies");
                    yaml.set("team.win-title", "&a&lVictory");
                    yaml.set("team.defeat-title", "&c&lDefeat");
                    yaml.set("team.end-default-title", "&c&lEND");
                    yaml.set("team.win-subtitle", "&9&lBleu &7won the game");
                    yaml.set("team.restart", "&cThe game will restart in &e&l30 seconds");
                    yaml.set("kits.choose-kit", "&aPlease choose a kit");
                    yaml.set("kits.chosen-kit", "&7You have chosen: {kit}");
                    yaml.set("kits.please-choose", "&cYou have not chosen a kit");

                    yaml.save(file);
                    System.out.println("DEBUG: messages-en.yml: recreated");

                }


                if(language == "fr") {
                    file.createNewFile();
                    console.sendMessage(Main.getPrefix() + "§aLanguage selected: §e§lFR");
                    yaml.set("join.broadcast-message", "&7[&a+&7] &7{player}");
                    yaml.set("join.message-player", "&7Tapez &e&l/join&7 pour rejoindre la partie");
                    yaml.set("blocks.cant-place", "&cVous ne pouvez pas poser de blocs ici");
                    yaml.set("blocks.own-wool", "&cVous ne pouvez pas casser votre propre laine");
                    yaml.set("blocks.cant-place-wool", "&cVous ne pouvez pas placer ce bloc");
                    yaml.set("death.title", "&c&lMort");
                    yaml.set("death.subtitle", "&7Vous allez bientot réapparaître");
                    yaml.set("death.timer", "&eRéapparition dans {secondes} {unite}");
                    yaml.set("death.end", "&eRéapparition en cours");

                    yaml.set("team.no-one", "&cIl n'y a personne dans l'équipe adverse, action impossible");
                    yaml.set("team.take-wool", "&e&l{player}&a a recupere la laine {wool-color}");
                    yaml.set("team.chat-blue", "&9&lBleu &9{player}&8» &7");
                    yaml.set("team.chat-red", "&c&lRouge &c{player}&8» &7");
                    yaml.set("team.chat-default", "&7{player}&8» &7");
                    yaml.set("team.join", "&7Vous avez rejoint l'équipe {team}");
                    yaml.set("team.too-much-1", "&cIl n'y a pas assez de joueur dans l'autre équipe");
                    yaml.set("team.too-much-2", "&eChangez de camp ou attendez :/");
                    yaml.set("team.already", "&cVous êtes deja dans cette équipe");
                    yaml.set("team.player-back", "&e{player}&a a ramené la laine {wool-color}&a à sa base");
                    yaml.set("team.lost-wool", "&e{player}&c a perdu la laine {wool-color}");
                    yaml.set("team.win", "&7L'equipe {team} &7gagne la partie");
                    yaml.set("team.same-team", "&cVous ne pouvez pas attaquer vos alliés");
                    yaml.set("team.win-title", "&a&lVictoire");
                    yaml.set("team.defeat-title", "&c&lDéfaite");
                    yaml.set("team.end-default-title", "&c&lFIN");
                    yaml.set("team.win-subtitle", "&7L'équipe &9&lBleu &7gagne la partie");
                    yaml.set("team.restart", "&cLa partie va redémarrer dans &e&l30 secondes");
                    yaml.set("kits.choose-kit", "&aVeuillez choisir un kit");
                    yaml.set("kits.chosen-kit", "&7Vous avez choisi le kit: {kit}");
                    yaml.set("kits.please-choose", "&cVous n'avez pas choisi de kit");
                    yaml.save(file);
                    System.out.println("DEBUG: messages-fr.yml: recreated");
                }
            }
            Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                @Override
                public void run() {
                    for(File files : file.getParentFile().listFiles()){
                        if(files.getPath() != file.getPath()){
                            if(files.getName().contains("messages")) {
                                if(!files.getName().equalsIgnoreCase(file.getName())){
                                    files.delete();
                                }
                            }
                        }
                    }
                }
            }, 20);





        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String getValue(String key) {
        File file = null;
        String language;


        if(Main.INSTANCE.getConfig().getString("language").equalsIgnoreCase("FR")) {
            file = new File("plugins/RushTheFlag/messages-fr.yml");
            language = "fr";
        }else if(Main.INSTANCE.getConfig().getString("language").equalsIgnoreCase("EN")) {
            file = new File("plugins/RushTheFlag/messages-en.yml");
            language = "en";
        }else{
            Bukkit.broadcastMessage(Main.getPrefix() + "§cLanguage in the config isn't set correctly");
        }


        try {

            YamlConfiguration yaml = new YamlConfiguration();
            yaml.load(file);
            String value = yaml.getString(key);
            return value;
        } catch (Exception e) {
            return Main.getPrefix() + "§cAn internal error has encountered, check §b" + file.getName() + " §ckey: §e" + key;

        }

    }

}






