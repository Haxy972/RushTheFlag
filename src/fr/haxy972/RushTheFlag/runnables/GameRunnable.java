package fr.haxy972.RushTheFlag.runnables;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

public class GameRunnable extends BukkitRunnable {
    
    
    
    @Override
    public void run() {

        checkGameStatut();
        checkWools();








        
    }

    private void checkGameStatut() {


        if (TeamSelect.teamRouge.size() != 0 && TeamSelect.teamBleu.size() + 1 != 0){
            GameStatut.setStatut(GameStatut.INGAME);
        }else{
            GameStatut.setStatut(GameStatut.INLOBBY);

        }



    }

    private void checkWools() {

    }
}
