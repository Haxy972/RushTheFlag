package fr.haxy972.RushTheFlag.runnables;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.listeners.GameListener;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import fr.haxy972.RushTheFlag.utils.MessageYaml;
import fr.haxy972.RushTheFlag.utils.TitleManager;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

public class GameRunnable extends BukkitRunnable {
    
    
    
    @Override
    public void run() {




        if(!GameStatut.isStatut(GameStatut.END)){
            checkGameStatut();
            checkWools();
            checkWinner();
        }else{
            this.cancel();
        }








        
    }
    public static boolean alreadyWin = false;

    private void checkWinner() {
        if(alreadyWin){
            return;
        }

        Location location = Main.getNexusRouge();
        for(Entity en : Main.getWorld().getNearbyEntities(location, 5,5 ,5)){
            if(en instanceof Player){
                Player player = (Player) en;
                if(TeamSelect.teamRouge.contains(player) && GameListener.hasBlueWool == player){
                    alreadyWin = true;
                    GameStatut.setStatut(GameStatut.END);
                    Bukkit.broadcastMessage(Main.getPrefix() + "§e" + MessageYaml.getValue("team.player-back").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§9§lBleu"));
                    Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.win").replace("&", "§").replace("{team}", "§c§lRouge"));


                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.getInventory().clear();
                        players.playSound(players.getLocation(), Sound.WITHER_DEATH, 1f, 1f);
                        if(TeamSelect.teamRouge.contains(player)){
                            TitleManager.sendTitle(players, MessageYaml.getValue("team.win-title").replace("&", "§"), MessageYaml.getValue("team.win-subtitle").replace("&", "§").replace("{team}", "§c§lRouge"), 3 * 20);
                        }else if (TeamSelect.teamBleu.contains(player)){
                            TitleManager.sendTitle(players, MessageYaml.getValue("team.defeat-title").replace("&", "§"), MessageYaml.getValue("team.win-subtitle").replace("&", "§").replace("{team}", "§c§lRouge"), 3 * 20);
                        }else{
                            TitleManager.sendTitle(players, MessageYaml.getValue("team.end-default-title").replace("&", "§"), MessageYaml.getValue("team.end-default-subtitle").replace("&", "§").replace("{team}", "§c§lRouge"), 3 * 20);
                        }
                        Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.restart").replace("&", "§"));
                    }

                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            for(Player players : Bukkit.getOnlinePlayers()) {
                                players.setGameMode(GameMode.SPECTATOR);
                            }

                        }
                    }, 3*20);

                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            Main.INSTANCE.getServer().reload();
                        }
                    }, 27*20);

                }
            }


        }
        location = Main.getNexusBleu();
        for(Entity en : Main.getWorld().getNearbyEntities(location, 5,5 ,5)) {
            if (en instanceof Player) {
                Player player = (Player) en;
                if (TeamSelect.teamBleu.contains(player) && GameListener.hasRedWool == player) {
                    alreadyWin = true;
                    GameStatut.setStatut(GameStatut.END);
                    Bukkit.broadcastMessage(Main.getPrefix() + "§e" + MessageYaml.getValue("team.player-back").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§c§lRouge"));
                    Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.win").replace("&", "§").replace("{team}", "§9§lBleu"));


                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.getInventory().clear();
                        players.playSound(players.getLocation(), Sound.WITHER_DEATH, 1f, 1f);
                        if (TeamSelect.teamBleu.contains(player)) {
                            TitleManager.sendTitle(players, MessageYaml.getValue("team.win-title").replace("&", "§"), MessageYaml.getValue("team.win-subtitle").replace("&", "§").replace("{team}", "§9§lBleu"), 3 * 20);
                        } else if (TeamSelect.teamRouge.contains(player)) {
                            TitleManager.sendTitle(players, MessageYaml.getValue("team.defeat-title").replace("&", "§"), MessageYaml.getValue("team.win-subtitle").replace("&", "§").replace("{team}", "§9§lBleu"), 3 * 20);
                        } else {
                            TitleManager.sendTitle(players, MessageYaml.getValue("team.end-default-title").replace("&", "§"), MessageYaml.getValue("team.win-subtitle").replace("&", "§").replace("{team}", "§9§lBleu"), 3 * 20);
                        }
                        Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.restart").replace("&", "§"));
                    }

                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.setGameMode(GameMode.SPECTATOR);
                            }

                        }
                    }, 3 * 20);

                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            Main.INSTANCE.getServer().reload();
                        }
                    }, 27 * 20);

                }
            }
        }
    }

    private void checkGameStatut() {
        int forDebug = TeamSelect.teamRouge.size();
        if(Main.INSTANCE.getConfig().getBoolean("debug")){
            forDebug = TeamSelect.teamRouge.size() +1;
        }


        if (TeamSelect.teamBleu.size() != 0 && forDebug != 0){
            GameStatut.setStatut(GameStatut.INGAME);
        }else{
            GameStatut.setStatut(GameStatut.INLOBBY);

        }



    }

    private void checkWools() {
        if(GameListener.hasRedWool == null){
            Location loc = Main.getNexusRouge();
            loc.getBlock().setType(Material.WOOL);
            loc.getBlock().setData((byte) 14);
            for(Player players : Bukkit.getOnlinePlayers()){
                if(ScoreboardManager.scoreboardGame.containsKey(players))
                    ScoreboardManager.scoreboardGame.get(players).setLine(5, "§c§lRouge§7: §a§l✔");
            }

        }else{
            for(Player players : Bukkit.getOnlinePlayers()){
                if(ScoreboardManager.scoreboardGame.containsKey(players))
                    ScoreboardManager.scoreboardGame.get(players).setLine(5, "§c§lRouge§7: §c§lX");
            }
        }
        if(GameListener.hasBlueWool == null){
            Location loc = Main.getNexusBleu();
            loc.getBlock().setType(Material.WOOL);
            loc.getBlock().setData((byte) 11);
            for(Player players : Bukkit.getOnlinePlayers()){
                if(ScoreboardManager.scoreboardGame.containsKey(players))
                    ScoreboardManager.scoreboardGame.get(players).setLine(4, "§9§lBleu§7: §a§l✔");;
            }
        }else{
            for(Player players : Bukkit.getOnlinePlayers()){
                if(ScoreboardManager.scoreboardGame.containsKey(players))
                    ScoreboardManager.scoreboardGame.get(players).setLine(4, "§9§lBleu§7: §c§lX");
            }
        }
    }
}