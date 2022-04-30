package fr.haxy972.RushTheFlag.listeners;

import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.scoreboard.ScoreboardManager;
import fr.haxy972.RushTheFlag.utils.MessageYaml;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){

        Player player = event.getPlayer();
        player.getInventory().clear();
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 1f);
        event.setJoinMessage("");
        ItemStack[] armor = player.getInventory().getArmorContents();
        armor[0].setType(Material.AIR);
        armor[1].setType(Material.AIR);
        armor[2].setType(Material.AIR);
        armor[3].setType(Material.AIR);
        if(!Main.INSTANCE.getConfig().getString("game.team.tab-name-default").equalsIgnoreCase("none")) {
            player.setPlayerListName(Main.INSTANCE.getConfig().getString("game.team.tab-name-default").replace("&", "§").replace("{player}", player.getName()));
        }
            player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().setArmorContents(armor);
        if(!ScoreboardManager.scoreboardGame.containsKey(player)){
            new ScoreboardManager(player).loadScoreboard();
        }

        Bukkit.broadcastMessage(MessageYaml.getValue("join.broadcast-message").replace("&", "§").replace("{player}", player.getName()));
        player.sendMessage(MessageYaml.getValue("join.message-player").replace("&", "§").replace("{player}", player.getName()));
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Main.getJoinSpawn());
    }



}
