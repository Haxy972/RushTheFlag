package fr.haxy972.RushTheFlag.Runnables;

import fr.haxy972.RushTheFlag.Utils.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SneakRunnable extends BukkitRunnable {
    Player player;

    public SneakRunnable (Player player){
        this.player = player;
    }

    @Override
    public void run() {
        TitleManager.sendActionBar(player, "§7(DOUBLE-TAP) §eSNEAK TO SWITCH IN FLY MODE");
        if(!player.getGameMode().equals(GameMode.SPECTATOR)){
            TitleManager.sendActionBar(player, "§f ");
            this.cancel();
        }


    }
}
