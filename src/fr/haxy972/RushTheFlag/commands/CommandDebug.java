package fr.haxy972.RushTheFlag.commands;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDebug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player)){return false;}


        if(cmd.getName().equalsIgnoreCase("debug")){

            Player player = (Player) sender;
            player.sendMessage("Bleu: " + Main.getNexusBleu().getBlock().toString());
            player.sendMessage("Rouge: " + Main.getNexusRouge().getBlock().toString());
        }



        return false;
    }
}
