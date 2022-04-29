package fr.haxy972.RushTheFlag.runnables;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import fr.haxy972.RushTheFlag.utils.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class DeathRunnable extends BukkitRunnable {



    int timer = 6;
    private Player player;

    public DeathRunnable(Player player) {
        this.player = player;
    }
    @Override
    public void run() {
        timer--;


        if (timer == 0) {
            TitleManager.sendActionBar(player, "§eRéapparition en cours");
            respawn(player);
            this.cancel();
        } else {
            TitleManager.sendActionBar(player, "§eRéapparition dans " + timer + getSeconds(timer));


        }



    }

    private void respawn(Player player) {
        if(TeamSelect.teamBleu.contains(player)){
            player.teleport(Main.getSpawnBleu());
            player.setGameMode(GameMode.SURVIVAL);
        }
        if(TeamSelect.teamRouge.contains(player)){
            player.teleport(Main.getSpawnRouge());
            player.setGameMode(GameMode.SURVIVAL);
        }



    }

    private String getSeconds ( int timer){
        if (timer == 1) {
            return " seconde";
        } else {
            return " secondes";
        }

    }
}
