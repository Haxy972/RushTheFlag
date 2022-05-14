package fr.haxy972.RushTheFlag.commands;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CommandTeam implements CommandExecutor {
    public static String language = Main.INSTANCE.getConfig().getString("language");
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player)){return false;}

        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("join")){
            if(GameStatut.isStatut(GameStatut.END)){
                if(language.equalsIgnoreCase("fr")) {
                    player.sendMessage(Main.getPrefix() + "§cAction impossible, veuillez attendre la fin de la partie");
                }else if(language.equalsIgnoreCase("es")){
                    player.sendMessage(Main.getPrefix() + "§cAcción imposible, espere hasta el final del juego");
                }else{
                    player.sendMessage(Main.getPrefix() + "§cAction not possible, please wait for the end of the game");
                }
                return false;
            }



            if(player.getGameMode().equals(GameMode.SPECTATOR) && !TeamSelect.teamBleu.contains(player) && !TeamSelect.teamRouge.contains(player)){


                TeamSelect.openTeamSelectInventory(player);
            }else{
                if(language.equalsIgnoreCase("fr")) {
                    player.sendMessage(Main.getPrefix() + "§cVous ne pouvez pas exécuter cette commande en cours de jeu");
                }else if(language.equalsIgnoreCase("es")){
                    player.sendMessage(Main.getPrefix() + "§cNo puede ejecutar este comando durante el juego");
                }else{
                    player.sendMessage(Main.getPrefix() + "§cYou cannot execute this command while playing");
                }

            }
        }

        if(cmd.getName().equalsIgnoreCase("kits")){
            if(player.getGameMode().equals(GameMode.SURVIVAL)){

                boolean inzone = false;
                for(Entity players : Main.getWorld().getNearbyEntities(Main.getSpawnRouge(), 5,2,4)){
                    if(players.getName().equals(player.getName())){
                        inzone = true;
                    }
                }

                for(Entity players : Main.getWorld().getNearbyEntities(Main.getSpawnBleu(), 5,2,4)){
                    if(players.getName().equals(player.getName())){
                        inzone = true;
                    }
                }

                if(inzone){
                    TeamSelect.kitChoose(player);
                }else{
                    if(language.equalsIgnoreCase("fr")) {
                        player.sendMessage(Main.getPrefix() + "§cVous pouvez changer de kit à votre spawn uniquement");
                    }else if(language.equalsIgnoreCase("es")){
                        player.sendMessage(Main.getPrefix() + "§cUsted puede cambiar el kit a su spawn solamente");
                    }else{
                        player.sendMessage(Main.getPrefix() + "§cYou can change kit to your spawn only");
                    }

                }

            }else{
                if(language.equalsIgnoreCase("fr")) {
                    player.sendMessage(Main.getPrefix() + "§cVous devez d'abord rejoindre la partie avant cela");
                }else if(language.equalsIgnoreCase("es")){
                    player.sendMessage(Main.getPrefix() + "§cPrimero tienes que unirte al juego antes de eso");
                }else{
                    player.sendMessage(Main.getPrefix() + "§cYou must first join the party before that");
                }

            }
        }



        return false;
    }
}
