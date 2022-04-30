package fr.haxy972.RushTheFlag.listeners;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.runnables.DeathRunnable;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import fr.haxy972.RushTheFlag.utils.MessageYaml;
import fr.haxy972.RushTheFlag.utils.TitleManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameListener implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);

    }
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(!GameStatut.isStatut(GameStatut.INGAME)){
            return;
        }

        if(event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK){
            event.setCancelled(true);
        }
        if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        double y = player.getLocation().getY();

        if(y < 45){
            if(!player.getGameMode().equals(GameMode.SPECTATOR)) {
                onDeath(player);
                player.teleport(Main.getJoinSpawn());
            }
        }

    }


    @EventHandler
    public void onDeath(PlayerDeathEvent event){

        Player player = event.getEntity();
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Main.getJoinSpawn());

        player.setHealth(20);
        TitleManager.sendTitle(player,MessageYaml.getValue("death.title").replace("&", "§"), MessageYaml.getValue("death.subtitle").replace("&", "§"), 40);
        new DeathRunnable(player).runTaskTimer(Main.INSTANCE, 0,20);
        event.setKeepInventory(true);
        event.setDeathMessage("");
        event.getDrops().clear();

        checkIfHasWool(player);

    }

    public void onDeath(Player player){


        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Main.getJoinSpawn());
        player.setHealth(20);
        TitleManager.sendTitle(player,MessageYaml.getValue("death.title").replace("&", "§"), MessageYaml.getValue("death.subtitle").replace("&", "§"), 40);
        new DeathRunnable(player).runTaskTimer(Main.INSTANCE, 0,20);
        checkIfHasWool(player);
    }

    private void checkIfHasWool(Player player) {

        if(hasBlueWool == player){

            player.getInventory().setContents(hasBlueWoolInventory);

            ItemStack[] armored = hasBlueArmor;
            player.getInventory().setArmorContents(hasBlueArmor);

            Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.lost-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§9§lBleu"));
            hasBlueWool = null;
            hasBlueArmor = null;
            hasBlueWoolInventory = null;
        }

        if(hasRedWool == player){

            player.getInventory().setContents(hasRedWoolInventory);

            ItemStack[] armored = hasRedArmor;
            player.getInventory().setArmorContents(hasRedArmor);

            Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.lost-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§c§lRouge"));
            hasRedWool = null;
            hasRedArmor = null;
            hasRedWoolInventory = null;
        }

    }

    private static Map<Location, Material> BlockPlaced = new HashMap<>();

    @EventHandler
    public void onFlagPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();


        if(event.getBlockPlaced().getType() == Material.WOOL){
            if(event.getBlockPlaced().getData() == 14 || event.getBlockPlaced().getData() == 11){

                player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place-wool").replace("&", "§"));
                event.setCancelled(true);
                return;
            }
        }

        ArrayList<Location> blockat = new ArrayList<>();
        for(int x = -2; x <= 2; x++){
            for(int y = -2; y <= 2; y++){
                for(int z = -2; z <= 2; z++){

                    double xs = Main.getNexusRouge().getX();
                    double ys = Main.getNexusRouge().getY();
                    double zs = Main.getNexusRouge().getZ();

                    Location loc = new Location(Main.getWorld(), xs + x-0.5, ys + y , zs+ z-0.5);
                    blockat.add(loc);

                    xs = Main.getNexusBleu().getX();
                    ys = Main.getNexusBleu().getY();
                    zs = Main.getNexusBleu().getZ();

                    loc = new Location(Main.getWorld(), xs + x-0.5, ys + y , zs+ z-0.5);
                    blockat.add(loc);
                }
            }

        }
        if(blockat.contains(event.getBlockPlaced().getLocation())){
            event.setCancelled(true);
            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));
        }

    }






    public static Player hasRedWool = null;
    public static ItemStack[] hasRedWoolInventory = null;
    public static ItemStack[] hasRedArmor = null;

    public static Player hasBlueWool = null;
    public static ItemStack[] hasBlueWoolInventory = null;
    public static ItemStack[] hasBlueArmor = null;

    @EventHandler
    public void onFlagBreak(BlockBreakEvent event){




        Player player = event.getPlayer();

        if(!GameStatut.isStatut(GameStatut.INLOBBY)) {
            if (event.getBlock().getType() == Material.WOOL) {
                if (event.getBlock().getData() == 14) {
                    if (!TeamSelect.teamRouge.contains(player)) {
                        event.getBlock().getDrops().clear();
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                    }

                } else if (event.getBlock().getData() == 11) {
                    if (!TeamSelect.teamBleu.contains(player)) {
                        event.getBlock().getDrops().clear();
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                    }

                }
            }
        }




        ArrayList<Location> blockatbleu = new ArrayList<>();
        ArrayList<Location> blockatrouge = new ArrayList<>();
        int margeflag = Main.INSTANCE.getConfig().getInt("regions.flag");
        for(int x = -margeflag; x <= margeflag; x++){
            for(int y = -margeflag; y <= margeflag; y++){
                for(int z = -margeflag; z <= margeflag; z++){

                    double xs = Main.getNexusRouge().getX();
                    double ys = Main.getNexusRouge().getY();
                    double zs = Main.getNexusRouge().getZ();

                    Location loc = new Location(Main.getWorld(), xs + x-0.5, ys + y , zs+ z-0.5);
                    blockatrouge.add(loc);

                    xs = Main.getNexusBleu().getX();
                    ys = Main.getNexusBleu().getY();
                    zs = Main.getNexusBleu().getZ();

                    loc = new Location(Main.getWorld(), xs + x-0.5, ys + y , zs+ z-0.5);
                    blockatbleu.add(loc);
                }
            }

        }
        if(blockatrouge.contains(event.getBlock().getLocation())){
            if(event.getBlock().getType() != Material.WOOL && event.getBlock().getType() != Material.AIR && !TeamSelect.teamRouge.contains(player)) {
                event.setCancelled(true);
                player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));

            }else{
                if(TeamSelect.teamBleu.contains(player)){
                    if(GameStatut.isStatut(GameStatut.INLOBBY)){
                        player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.no-one").replace("&", "§"));
                        event.setCancelled(true);
                        return;
                    }
                    hasRedArmor = player.getInventory().getArmorContents();
                    hasRedWoolInventory = player.getInventory().getContents();
                    hasRedWool = player;
                    player.getInventory().clear();

                    Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.take-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§c§lRouge"));
                    ItemStack[] armor = player.getInventory().getArmorContents();





                    for(int i = 0; i < 9; i++){
                        player.getInventory().setItem(i, new ItemStack(Material.WOOL, 1, (short) 0, (byte) 14));

                    }


                    armor[0].setType(Material.AIR);
                    armor[1].setType(Material.AIR);
                    armor[2].setType(Material.AIR);
                    armor[3] = new ItemStack(new ItemStack(Material.WOOL, 1, (short) 0, (byte) 14));
                    player.getInventory().setArmorContents(armor);



                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1f, 1f);

                    }
                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            for(Player players : Bukkit.getOnlinePlayers()){
                                players.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1f, 1f);

                            }

                        }
                    }, 20);

                }else if(TeamSelect.teamRouge.contains(player)){
                    event.setCancelled(true);
                    player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.own-wool").replace("&", "§"));
                }
            }
        }
        if(blockatbleu.contains(event.getBlock().getLocation())){
            if(event.getBlock().getType() != Material.WOOL && event.getBlock().getType() != Material.AIR && !TeamSelect.teamBleu.contains(player)) {
                event.setCancelled(true);
                player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));

            }else{
                if(TeamSelect.teamRouge.contains(player)){
                    if(GameStatut.isStatut(GameStatut.INLOBBY)){
                        player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.no-one").replace("&", "§"));;
                        event.setCancelled(true);
                        return;
                    }

                    hasBlueArmor = player.getInventory().getArmorContents();
                    hasBlueWoolInventory = player.getInventory().getContents();
                    hasBlueWool = player;
                    player.getInventory().clear();
                    Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.take-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§9§lBleu"));


                    for(int i = 0; i < 9; i++){
                        player.getInventory().setItem(i, new ItemStack(Material.WOOL, 1, (short) 0, (byte) 11));

                    }

                    ItemStack[] armor = player.getInventory().getArmorContents();
                    armor[0].setType(Material.AIR);
                    armor[1].setType(Material.AIR);
                    armor[2].setType(Material.AIR);
                    armor[3] = new ItemStack(new ItemStack(Material.WOOL, 1, (short) 0, (byte) 11));
                    player.getInventory().setArmorContents(armor);

                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1f, 1f);

                    }
                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            for(Player players : Bukkit.getOnlinePlayers()){
                                players.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1f, 1f);

                            }

                        }
                    }, 20);

                }else if(TeamSelect.teamBleu.contains(player)){
                    event.setCancelled(true);
                    player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.own-wool").replace("&", "§"));
                }
            }
        }

    }
    @EventHandler
    public void onBlockSpawnPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ArrayList<Location> blockat = new ArrayList<>();





        int margeX = Main.INSTANCE.getConfig().getInt("regions.base.x");
        int margeY = Main.INSTANCE.getConfig().getInt("regions.base.y");
        int margeZ = Main.INSTANCE.getConfig().getInt("regions.base.z");

        for (int x = -margeX; x <= margeX; x++) {
            for (int y = -margeY; y <= margeY; y++) {
                for (int z = -margeZ; z <= margeZ; z++) {

                    double xs = Main.getSpawnBleu().getX()-0.5;
                    double ys = Main.getSpawnBleu().getY();
                    double zs = Main.getSpawnBleu().getZ() -0.5;
                    Location loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0 ,0);
                    blockat.add(loc);


                    xs = Main.getSpawnRouge().getX()-0.5;
                    ys = Main.getSpawnRouge().getY();
                    zs = Main.getSpawnRouge().getZ() -0.5;
                    loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0 ,0);
                    blockat.add(loc);
                }
            }
        }
        if(blockat.contains(event.getBlockPlaced().getLocation())){
            event.setCancelled(true);
            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));
        }
        //INFINITE BLOCKS
        if(event.isCancelled()){
            return;
        }
        if(!Main.INSTANCE.getConfig().getBoolean("blocks.infinite-sandstone")){return;}

        if(event.getBlockPlaced().getType() == Material.SANDSTONE){
            int amount = player.getItemInHand().getAmount();
            Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                @Override
                public void run() {
                    player.getItemInHand().setAmount(amount);
                }
            }, 1);
        }
    }
    @EventHandler
    public void onBlockSpawnBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ArrayList<Location> blockat = new ArrayList<>();


        int margeX = Main.INSTANCE.getConfig().getInt("regions.base.x");
        int margeY = Main.INSTANCE.getConfig().getInt("regions.base.y");
        int margeZ = Main.INSTANCE.getConfig().getInt("regions.base.z");

        for (int x = -margeX; x <= margeX; x++) {
            for (int y = -margeY; y <= margeY; y++) {
                for (int z = -margeZ; z <= margeZ; z++) {

                    double xs = Main.getSpawnBleu().getX()-0.5;
                    double ys = Main.getSpawnBleu().getY();
                    double zs = Main.getSpawnBleu().getZ() -0.5;
                    Location loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0 ,0);
                    blockat.add(loc);


                    xs = Main.getSpawnRouge().getX()-0.5;
                    ys = Main.getSpawnRouge().getY();
                    zs = Main.getSpawnRouge().getZ() -0.5;
                    loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0 ,0);
                    blockat.add(loc);
                }
            }
        }
        if(blockat.contains(event.getBlock().getLocation())){
            event.setCancelled(true);
            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));
        }

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        event.setCancelled(true);

        Bukkit.broadcastMessage(getTeam(player) + message);



    }



    private String getTeam(Player player) {

        if(TeamSelect.teamBleu.contains(player)){
            return MessageYaml.getValue("team.chat-blue").replace("&", "§").replace("{player}", player.getName());
        }else if (TeamSelect.teamRouge.contains(player)){
            return MessageYaml.getValue("team.chat-red").replace("&", "§").replace("{player}", player.getName());
        }


        return MessageYaml.getValue("team.chat-default").replace("&", "§").replace("{player}", player.getName());
    }

}
