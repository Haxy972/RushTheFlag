package fr.haxy972.RushTheFlag;

import fr.haxy972.RushTheFlag.team.TeamSelect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandTeam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player)){return false;}


        if(cmd.getName().equalsIgnoreCase("join")){
            Player player = (Player) sender;
            if(player.getGameMode().equals(GameMode.SPECTATOR)){


                TeamSelect.openTeamSelectInventory(player);
            }
        }



        return false;
    }
}
