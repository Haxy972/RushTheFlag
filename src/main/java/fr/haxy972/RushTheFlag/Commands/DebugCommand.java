package fr.haxy972.RushTheFlag.Commands;

import fr.haxy972.RushTheFlag.Managers.Team.FirstTeam;
import fr.haxy972.RushTheFlag.Managers.Team.SecondTeam;
import fr.haxy972.RushTheFlag.Managers.Team.Teams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player))return false;

        if(cmd.getName().equalsIgnoreCase("test") || cmd.getName().equalsIgnoreCase("debug")){
            Player player = (Player) sender;
            FirstTeam team = new FirstTeam();
            SecondTeam team2 = new SecondTeam();
            if(args.length == 0) {
                player.sendMessage("------RED--------");
                player.sendMessage("" + team.getName());
                player.sendMessage("" + team.getSize());
                player.sendMessage("" + team.getTeam_list());
                player.sendMessage("------BLUE--------");
                player.sendMessage("" + team2.getName());
                player.sendMessage("" + team2.getSize());
                player.sendMessage("" + team2.getTeam_list());
                player.sendMessage("--------------");
                try {
                    player.sendMessage("Team: " + new Teams().getPlayerTeam(player).getColorCode() +new Teams().getPlayerTeam(player).getName());
                }catch (Exception ignored){}

            }else if(args[0].equalsIgnoreCase("blue")) {
                team2.addPlayerToTeam(player);
            }else if(args[0].equalsIgnoreCase("red")){
                team.addPlayerToTeam(player);
            }else{
                team.clearList();
            }
        }

        return false;
    }
}
