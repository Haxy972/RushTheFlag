package fr.haxy972.RushTheFlag;

import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardSign;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ScoreboardRunnable extends BukkitRunnable {

    private static String scoreboardFooter = Main.INSTANCE.getConfig().getString("scoreboard-ip").replace("&", "§");
    Integer timer = 0;
    @Override
    public void run() {
        if(Main.INSTANCE.getConfig().getInt("scoreboard-animation") == 0){
            return;
        }
        if(Main.INSTANCE.getConfig().getInt("scoreboard-animation") == 1) {

            for (Player players : Bukkit.getOnlinePlayers()) {
                if (ScoreboardManager.scoreboardGame.containsKey(players)) {
                    timer ++;
                    if(scoreboardFooter.contains("§")){
                        scoreboardFooter = scoreboardFooter.replace("§4", "");
                        scoreboardFooter = scoreboardFooter.replace("§c", "");
                        scoreboardFooter = scoreboardFooter.replace("§6", "");
                        scoreboardFooter = scoreboardFooter.replace("§e", "");
                        scoreboardFooter = scoreboardFooter.replace("§2", "");
                        scoreboardFooter = scoreboardFooter.replace("§a", "");
                        scoreboardFooter = scoreboardFooter.replace("§b", "");
                        scoreboardFooter = scoreboardFooter.replace("§3", "");
                        scoreboardFooter = scoreboardFooter.replace("§1", "");
                        scoreboardFooter = scoreboardFooter.replace("§9", "");
                        scoreboardFooter = scoreboardFooter.replace("§d", "");
                        scoreboardFooter = scoreboardFooter.replace("§5", "");
                        scoreboardFooter = scoreboardFooter.replace("§f", "");
                        scoreboardFooter = scoreboardFooter.replace("§7", "");
                        scoreboardFooter = scoreboardFooter.replace("§8", "");
                        scoreboardFooter = scoreboardFooter.replace("§0", "");
                        scoreboardFooter = scoreboardFooter.replace("§k", "");
                        scoreboardFooter = scoreboardFooter.replace("§l", "");
                        scoreboardFooter = scoreboardFooter.replace("§m", "");
                        scoreboardFooter = scoreboardFooter.replace("§n", "");
                        scoreboardFooter = scoreboardFooter.replace("§o", "");
                        scoreboardFooter = scoreboardFooter.replace("§r", "");

                    }
                    if(timer == 5){
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "   §b" + scoreboardFooter);
                    }

                    if(timer == 10){
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "   §f" + scoreboardFooter);
                    }

                    if(timer == 15){
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "   §b" + scoreboardFooter);
                    }

                    if(timer == 20){
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "   §f" + scoreboardFooter);
                    }

                    if(timer == 25){
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "   §b" + scoreboardFooter);
                    }

                    if(timer == 50){
                        timer = 0;
                    }






                }
            }
        }
        if(Main.INSTANCE.getConfig().getInt("scoreboard-animation") == 2) {

            for (Player players : Bukkit.getOnlinePlayers()) {
                if (ScoreboardManager.scoreboardGame.containsKey(players)) {
                    if(scoreboardFooter.contains("§")){
                        scoreboardFooter = scoreboardFooter.replace("§4", "");
                        scoreboardFooter = scoreboardFooter.replace("§c", "");
                        scoreboardFooter = scoreboardFooter.replace("§6", "");
                        scoreboardFooter = scoreboardFooter.replace("§e", "");
                        scoreboardFooter = scoreboardFooter.replace("§2", "");
                        scoreboardFooter = scoreboardFooter.replace("§a", "");
                        scoreboardFooter = scoreboardFooter.replace("§b", "");
                        scoreboardFooter = scoreboardFooter.replace("§3", "");
                        scoreboardFooter = scoreboardFooter.replace("§1", "");
                        scoreboardFooter = scoreboardFooter.replace("§9", "");
                        scoreboardFooter = scoreboardFooter.replace("§d", "");
                        scoreboardFooter = scoreboardFooter.replace("§5", "");
                        scoreboardFooter = scoreboardFooter.replace("§f", "");
                        scoreboardFooter = scoreboardFooter.replace("§7", "");
                        scoreboardFooter = scoreboardFooter.replace("§8", "");
                        scoreboardFooter = scoreboardFooter.replace("§0", "");
                        scoreboardFooter = scoreboardFooter.replace("§k", "");
                        scoreboardFooter = scoreboardFooter.replace("§l", "");
                        scoreboardFooter = scoreboardFooter.replace("§m", "");
                        scoreboardFooter = scoreboardFooter.replace("§n", "");
                        scoreboardFooter = scoreboardFooter.replace("§o", "");
                        scoreboardFooter = scoreboardFooter.replace("§r", "");

                    }


                    ArrayList<Character> charList = new ArrayList();
                    for(char character : scoreboardFooter.toCharArray()){
                        charList.add(character);
                    }
                    int charCounter = charList.size();













                    ArrayList<Integer> listTimer = new ArrayList<>();
                    for(int i = 0; i < 64; i += 2){
                        listTimer.add(i);
                    }
                    if(listTimer.contains(timer)){
                        if(counterAnimationSeq != charCounter || counterAnimationSeq < charCounter){

                            ScoreboardManager.scoreboardGame.get(players).setLine(0, "§b" + WrittingAnimationSeq);
                            WrittingAnimationSeq = WrittingAnimationSeq + charList.get(counterAnimationSeq);
                            counterAnimationSeq ++;
                        }

                    }else if(timer == 105) {
                        WrittingAnimationSeq = "";
                        counterAnimationSeq = 0;
                        timer = 0;
                    }
                    if (timer == charCounter*2) {
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "§b" + WrittingAnimationSeq);
                    }else if(timer == charCounter*2 -2){
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "§b" + WrittingAnimationSeq);
                    }
                    if (timer == charCounter*2 + 5) {
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "§f" + WrittingAnimationSeq);
                    }
                    if (timer == charCounter*2 + 10) {
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "§b" + WrittingAnimationSeq);
                    }
                    if (timer == charCounter*2 + 15) {
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "§f" + WrittingAnimationSeq);
                    }
                    if (timer == charCounter*2 + 20) {
                        ScoreboardManager.scoreboardGame.get(players).setLine(0, "§b" + WrittingAnimationSeq);
                    }
                    timer ++;


                }
            }
        }
    }
    private static int counterAnimationSeq = 0;
    private static String WrittingAnimationSeq = "";
}
