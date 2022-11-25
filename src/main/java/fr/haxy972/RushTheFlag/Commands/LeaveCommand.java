package fr.haxy972.RushTheFlag.Commands;

import fr.haxy972.RushTheFlag.Managers.GameManager;
import fr.haxy972.RushTheFlag.Managers.SoundManager;
import fr.haxy972.RushTheFlag.Utils.PluginMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player))return false;
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("leave") ||cmd.getName().equalsIgnoreCase("quit")){
            GameManager gameManager = new GameManager(player);

            if(gameManager.getPlayerInGameList().contains(player)){
                gameManager.removePlayerFromGame();
                gameManager.setJoinAttributes();
                new PluginMessage(player).Notif("§cYou have left the game.");
                new SoundManager(player).playerSucess();
            }else{
                new PluginMessage(player).Err("You are not in the game, :/");
            }
        }


        return false;
    }
}
