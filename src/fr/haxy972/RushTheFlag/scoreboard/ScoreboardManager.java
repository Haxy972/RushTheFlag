package fr.haxy972.RushTheFlag.scoreboard;

import java.util.HashMap;
import java.util.Map;

import fr.haxy972.RushTheFlag.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class ScoreboardManager {

    public Player player;
    public ScoreboardSign scoreboard;
    public static Map<Player, ScoreboardSign> scoreboardGame = new HashMap<>();

    public ScoreboardManager(Player players) {
        player = players;
        scoreboard = new ScoreboardSign(player, player.getName());
        scoreboardGame.put(player, scoreboard);

    }

    public void loadScoreboard() {
        scoreboard.setObjectiveName(Main.getPrefix().replace("§8»", ""));
        scoreboard.create();
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(9, "§d");
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(8, "§fJoueur: §b§l" + player.getName());
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(7, "§fEquipe: §7§lAucune");
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(6, "§2");
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(5, "§c§lRouge§7: §a§l✔");
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(4, "§9§lBleu§7: §a§l✔");
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(3, "§a");
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(2, "§fKit: §e§lAucun");
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(1, "§d");
        ((ScoreboardSign) scoreboardGame.get(player)).setLine(0, "    §fplay.selarium.fr");

//        scoreboardGame.get(player).setLine(2, "§fKit: §e§lAucun");
//        scoreboardGame.get(player).setLine(7, "§fEquipe: §7§lAucune");
    }


}
