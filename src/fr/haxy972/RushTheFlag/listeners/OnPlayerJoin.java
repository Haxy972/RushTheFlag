package fr.haxy972.RushTheFlag.listeners;

import fr.haxy972.RushTheFlag.Main;
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


        Bukkit.broadcastMessage("§7[§a+§7] §7" + player.getName());
        player.sendMessage(Main.getPrefix() + "§7Tapez §e§l/join§7 pour rejoindre la partie");
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Main.getJoinSpawn());
    }



}
