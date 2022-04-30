package fr.haxy972.RushTheFlag.runnables;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import fr.haxy972.RushTheFlag.utils.MessageYaml;
import fr.haxy972.RushTheFlag.utils.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class DeathRunnable extends BukkitRunnable {



    int timer = Main.INSTANCE.getConfig().getInt("game.death-timer") + 1;
    private Player player;

    public DeathRunnable(Player player) {
        this.player = player;
    }
    @Override
    public void run() {
        timer--;


        if (timer == 0) {
            TitleManager.sendActionBar(player, MessageYaml.getValue("death.end").replace("&", "§"));
            respawn(player);
            this.cancel();
        } else {
            TitleManager.sendActionBar(player, MessageYaml.getValue("death.timer").replace("&", "§").replace("{secondes}", "" + timer).replace("{unite}", getSeconds(timer)));


        }



    }

    private void respawn(Player player) {
        if(TeamSelect.teamBleu.contains(player)){
            player.teleport(Main.getSpawnBleu());
            player.setGameMode(GameMode.SURVIVAL);
            player.setFoodLevel(20);
        }
        if(TeamSelect.teamRouge.contains(player)){
            player.teleport(Main.getSpawnRouge());
            player.setGameMode(GameMode.SURVIVAL);
            player.setFoodLevel(20);
        }



    }

    private String getSeconds ( int timer){
        if (timer == 1) {
            return "seconde";
        } else {
            return "secondes";
        }

    }
}
