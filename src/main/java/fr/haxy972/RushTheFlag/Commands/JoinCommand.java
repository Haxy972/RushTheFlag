package fr.haxy972.RushTheFlag.Commands;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Utils.PluginMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] msg) {

        if(!(sender instanceof Player))return false;
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("join") || cmd.getName().equalsIgnoreCase("play")){
            GameManager gameManager = new GameManager(player);
            if(!gameManager.getPlayerInGameList().contains(player)){
                
            }else{
                new PluginMessage(player).Err("You have already join the game.");
            }
        }


        return false;
    }
}