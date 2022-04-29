package fr.haxy972.RushTheFlag.runnables;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardRunnable extends BukkitRunnable {

    int temp = 0;
    @Override
    public void run() {
        temp ++;

        for(Player players : Bukkit.getOnlinePlayers()){
            if(ScoreboardManager.scoreboardGame.containsKey(players)){
                if(temp == 1){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §fplay.selarium.fr");
                }
                if(temp == 2){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bp§flay.selarium.fr");
                }
                if(temp == 3){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bpl§fay.selarium.fr");
                }
                if(temp == 4){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bpla§fy.selarium.fr");
                }
                if(temp == 5){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.§fselarium.fr");
                }
                if(temp == 6){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.se§flarium.fr");
                }
                if(temp == 7){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.sela§frium.fr");
                }
                if(temp == 8){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.selari§fum.fr");
                }
                if(temp == 9){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.selariu§fm.fr");
                }
                if(temp == 10){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.selarium.f§fr");
                }
                if(temp == 11){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.selarium.fr");
                }
                if(temp == 25){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §fplay.selarium.fr");
                }
                if(temp == 30){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.selarium.fr");
                }
                if(temp == 35){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §fplay.selarium.fr");
                }
                if(temp == 40){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.selarium.fr");

                }
                if(temp == 55){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.selarium.f§fr");

                }
                if(temp == 56){

                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.selarium§f.fr");
                }
                if(temp == 57){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.selari§fum.fr");

                }
                if(temp == 58){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.sela§frium.fr");

                }
                if(temp == 59){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.se§flarium.fr");
                }
                if(temp == 60){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bplay.§fselarium.fr");

                }
                if(temp == 61){

                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bpla§fy.selarium.fr");
                }
                if(temp == 62){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bpl§fay.selarium.fr");
                }
                if(temp == 63){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §bp§flay.selarium.fr");
                }
                if(temp == 64){
                    ScoreboardManager.scoreboardGame.get(players).setLine(0, "    §fplay.selarium.fr");
                }




            }
        }
        if(temp == 70){
            this.cancel();
            Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                @Override
                public void run() {
                    temp = 0;
                    new ScoreboardRunnable().runTaskTimer(Main.INSTANCE, 0, 1);
                }
            }, 1*20);

        }





    }
}
