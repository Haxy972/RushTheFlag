package fr.haxy972.RushTheFlag.runnables;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import fr.haxy972.RushTheFlag.utils.MessageYaml;
import fr.haxy972.RushTheFlag.utils.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;


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

    public static ArrayList<Player> respawnedList = new ArrayList<>();
    public static String language = Main.INSTANCE.getConfig().getString("language");

    private void respawn(Player player) {

        if(GameStatut.isStatut(GameStatut.END)){
            player.setGameMode(GameMode.SPECTATOR);
            return;
        }



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
        if(language.equalsIgnoreCase("fr")) {
            player.sendMessage(Main.getPrefix() + "§7Vous pouvez changer de kit via §e§l/kits");
        }else if(language.equalsIgnoreCase("es")){
            player.sendMessage(Main.getPrefix() + "§7Puede cambiar el kit a través de §e§l/kits");
        }else{
            player.sendMessage(Main.getPrefix() + "§7To change kits type §e§l/kits");
        }
        respawnedList.add(player);
        Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
            @Override
            public void run() {
                respawnedList.remove(player);
            }
        }, 3*20);



    }

    private String getSeconds ( int timer){
        if (timer == 1) {
            if(language.equalsIgnoreCase("fr")) {
                return "seconde";
            }else{
                return "second";
            }
        }else if(language.equalsIgnoreCase("es")){
            if(language.equalsIgnoreCase("fr")) {
                return "secundos";
            }else{
                return "segondo";
            }
        } else {
            if(language.equalsIgnoreCase("fr")) {
                return "secondes";
            }else{
                return "seconds";
            }
        }

    }
}
