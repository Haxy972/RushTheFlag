package fr.haxy972.RushTheFlag.utils;

import fr.haxy972.RushTheFlag.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageYaml {


    public static void checkYaml() {
        File file = new File("plugins/RushTheFlag/message.yml");


        try {
            if (!file.isFile()) {
                file.createNewFile();
                YamlConfiguration yaml = new YamlConfiguration();

                //yaml.set("messages.nether.new-day-nether", "");
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
                yaml.set("team.lost-wool", "§e{player}§c a perdu la laine {wool-color}");
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



                System.out.println("DEBUG: message.yml: recreated");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getValue(String key) {


        try {
            File file = new File("plugins/RushTheFlag/message.yml");
            YamlConfiguration yaml = new YamlConfiguration();
            yaml.load(file);
            String value = yaml.getString(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return Main.getPrefix() + "§cUne erreur de configuration est survenue: §e§lmessage.yml";
    }

}






