package fr.haxy972.RushTheFlag.commands;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.listeners.GameListener;
import fr.haxy972.RushTheFlag.listeners.ResetListeners;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import fr.haxy972.RushTheFlag.utils.PluginUpdater;
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
                PluginUpdater.check(Main.INSTANCE, "Haxy972", "RushTheFlag");
            }else{
                player.sendMessage("§c/debug <args>");
                GameListener.hasRedWool = null;
                GameListener.hasBlueWool = null;

            }
        }



        return false;
    }
}
