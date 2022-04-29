package fr.haxy972.RushTheFlag.commands;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandDebug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player)){return false;}

        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("debug")){
            if(args.length == 1){
                player.sendMessage(GameStatut.getStatut().toString());

            }else{
                player.sendMessage("§c/debug <args>");

            }
        }



        return false;
    }
}
