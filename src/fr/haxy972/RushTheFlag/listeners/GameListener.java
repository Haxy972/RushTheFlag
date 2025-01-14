package fr.haxy972.RushTheFlag.listeners;

import fr.haxy972.RushTheFlag.GameStatut;
import fr.haxy972.RushTheFlag.Main;
import fr.haxy972.RushTheFlag.commands.CommandKits;
import fr.haxy972.RushTheFlag.runnables.DeathRunnable;
import fr.haxy972.RushTheFlag.team.TeamSelect;
import fr.haxy972.RushTheFlag.utils.MessageYaml;
import fr.haxy972.RushTheFlag.utils.TitleManager;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameListener implements Listener {

    public static Player hasRedWool = null;
    public static ItemStack[] hasRedWoolInventory = null;
    public static ItemStack[] hasRedArmor = null;
    public static Player hasBlueWool = null;
    public static ItemStack[] hasBlueWoolInventory = null;
    public static ItemStack[] hasBlueArmor = null;
    private static Map<Location, Material> BlockPlaced = new HashMap<>();

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);

    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {


        if (event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
            event.setCancelled(true);
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }




    }
    private static Map<Player, Player> attackedBy = new HashMap<>();

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow){
            if(event.getDamager() instanceof Arrow && ((Arrow) event.getDamager()).getShooter() instanceof Player && event.getEntity() instanceof Player){
                Arrow a = (Arrow) event.getDamager();
                Player attacker = (Player) a.getShooter();
                Player player = (Player) event.getEntity();

                if(player == attacker){
                    event.setCancelled(true);
                    return;
                }



                if (DeathRunnable.respawnedList.contains(player)) {
                    event.setCancelled(true);
                    attacker.sendMessage(Main.getPrefix() + MessageYaml.getValue("death.anti-spawn-kill").replace("&", "§"));
                    return;
                }
                if (DeathRunnable.respawnedList.contains(attacker)) {
                    event.setCancelled(true);
                    attacker.sendMessage(Main.getPrefix() + MessageYaml.getValue("death.calm-down").replace("&", "§"));
                    return;
                }

                if(!attackedBy.containsKey(player))
                    attackedBy.put(player, attacker);
                else{
                    attackedBy.replace(player, attacker);
                }

                if (TeamSelect.teamRouge.contains(player)) {
                    if (TeamSelect.teamRouge.contains(attacker)) {
                        event.setCancelled(true);
                        attacker.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.same-team").replace("&", "§"));
                        return;
                    }
                } else if (TeamSelect.teamBleu.contains(player)) {
                    if (TeamSelect.teamBleu.contains(attacker)) {
                        event.setCancelled(true);
                        attacker.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.same-team").replace("&", "§"));
                        return;
                    }
                }
                Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        if(attackedBy.containsKey(player) && attackedBy.get(player).equals(attacker)){
                            attackedBy.remove(player);
                        }
                    }
                }, 5*20);

            }



        }





        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player attacker = (Player) event.getDamager();

            if (DeathRunnable.respawnedList.contains(player)) {
                event.setCancelled(true);
                attacker.sendMessage(Main.getPrefix() + MessageYaml.getValue("death.anti-spawn-kill").replace("&", "§"));
                return;
            }
            if (DeathRunnable.respawnedList.contains(attacker)) {
                event.setCancelled(true);
                attacker.sendMessage(Main.getPrefix() + MessageYaml.getValue("death.calm-down").replace("&", "§"));
                return;
            }
            if(!attackedBy.containsKey(player))
                attackedBy.put(player, attacker);
            else{
                attackedBy.replace(player, attacker);
            }

            if (TeamSelect.teamRouge.contains(player)) {
                if (TeamSelect.teamRouge.contains(attacker)) {
                    event.setCancelled(true);
                    attacker.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.same-team").replace("&", "§"));
                }
            } else if (TeamSelect.teamBleu.contains(player)) {
                if (TeamSelect.teamBleu.contains(attacker)) {
                    event.setCancelled(true);
                    attacker.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.same-team").replace("&", "§"));
                }
            }
            Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                @Override
                public void run() {
                    if(attackedBy.containsKey(player) && attackedBy.get(player).equals(attacker)){
                        attackedBy.remove(player);
                    }
                }
            }, 5*20);

        }


    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        double y = player.getLocation().getY();
        double miny = Main.INSTANCE.getConfig().getDouble("altitude-min");

        if (y < miny) {
            if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
                onDeath(player);
                player.teleport(Main.getJoinSpawn());
            }
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player attacker = event.getEntity().getKiller();

        for(Player players : Bukkit.getOnlinePlayers()){
            if(language.equalsIgnoreCase("fr")){
                TitleManager.sendActionBar(players, "§e§l" + player.getName() + " §ea été tué par §b§l" + attacker.getName());
            }else if(language.equalsIgnoreCase("es")){
                TitleManager.sendActionBar(players, "§e§l" + player.getName() + " §efue asesinado por §b§l" + attacker.getName());
            }else{
                TitleManager.sendActionBar(players, "§e§l" + player.getName() + " §e was killed by §b§l" + attacker.getName());
            }

        }

        if(language.equalsIgnoreCase("fr")){
            TitleManager.sendActionBar(player, "§eVous avez tué §b§l" + player.getName());
        }else if(language.equalsIgnoreCase("es")){
            TitleManager.sendActionBar(player, "§eMataste a §b§l" + player.getName());
        }else{
            TitleManager.sendActionBar(player, "§eYou killed §b§l" + player.getName());
        }

        attacker.playSound(attacker.getLocation(), Sound.LEVEL_UP, 1f, 1f);



        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Main.getJoinSpawn());

        player.setHealth(20);
        TitleManager.sendTitle(player, MessageYaml.getValue("death.title").replace("&", "§"), MessageYaml.getValue("death.subtitle").replace("&", "§"), 40);
        new DeathRunnable(player).runTaskTimer(Main.INSTANCE, 0, 20);
        event.setKeepInventory(true);
        event.setDeathMessage("");
        event.getDrops().clear();

        checkIfHasWool(player);
        CommandKits.getKitContent(player, TeamSelect.playerKit.get(player).replace(".yml", ""));
        TeamSelect.replaceArmor(player);

    }

    public void onDeath(Player player) {
        for(Player players : Bukkit.getOnlinePlayers()){
            if(!attackedBy.containsKey(player)) {
                if(language.equalsIgnoreCase("fr")){
                    TitleManager.sendActionBar(players, "§e" + player.getName() + " est tombé dans le vide");
                }else if(language.equalsIgnoreCase("fr")){
                    TitleManager.sendActionBar(players, "§e" + player.getName() + " cayó en el vacío");
                }else {
                    TitleManager.sendActionBar(players, "§e" + player.getName() + " fell into the void");
                }

            }else{

                if(language.equalsIgnoreCase("fr")){
                    TitleManager.sendActionBar(players, "§e" + player.getName() + " a été poussé dans le vide par §b§l" + attackedBy.get(player).getName());
                }else if(language.equalsIgnoreCase("es")){
                    TitleManager.sendActionBar(players, "§e" + player.getName() + " fue empujado al vacío por §b§l" + attackedBy.get(player).getName());
                }else{
                    TitleManager.sendActionBar(players, "§e" + player.getName() + " was pushed into the void by §b§l" + attackedBy.get(player).getName());
                }





                if(attackedBy.get(player).getName().equalsIgnoreCase(players.getName())){
                    if(language.equalsIgnoreCase("fr")){
                        TitleManager.sendActionBar(attackedBy.get(player), "§eVous avez tué §b§l" + player.getName());
                    }else if(language.equalsIgnoreCase("es")){
                        TitleManager.sendActionBar(attackedBy.get(player), "§eMataste a §b§l" + player.getName());
                    }else{
                        TitleManager.sendActionBar(attackedBy.get(player), "§eYou killed §b§l" + player.getName());
                    }
                }
            }


        }

        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Main.getJoinSpawn());
        player.setHealth(20);
        TitleManager.sendTitle(player, MessageYaml.getValue("death.title").replace("&", "§"), MessageYaml.getValue("death.subtitle").replace("&", "§"), 40);
        new DeathRunnable(player).runTaskTimer(Main.INSTANCE, 0, 20);
        checkIfHasWool(player);
        CommandKits.getKitContent(player, TeamSelect.playerKit.get(player).replace(".yml", ""));
        TeamSelect.replaceArmor(player);
    }

    private void checkIfHasWool(Player player) {

        if (hasBlueWool == player) {

            player.getInventory().setContents(hasBlueWoolInventory);

            ItemStack[] armored = hasBlueArmor;
            player.getInventory().setArmorContents(hasBlueArmor);

            if(language.equalsIgnoreCase("fr")){
                Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.lost-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§9§lBleu"));

            }else if(language.equalsIgnoreCase("es")){
                Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.lost-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§9§lAzul"));

            }else{
                Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.lost-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§9§lBlue"));
            }            hasBlueWool = null;
            hasBlueArmor = null;
            hasBlueWoolInventory = null;
        }

        if (hasRedWool == player) {

            player.getInventory().setContents(hasRedWoolInventory);

            ItemStack[] armored = hasRedArmor;
            player.getInventory().setArmorContents(hasRedArmor);

            if(language.equalsIgnoreCase("fr")){
                Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.lost-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§c§lRouge"));

            }else if(language.equalsIgnoreCase("es")){
                Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.lost-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§c§lRojo"));

            }else{
                Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.lost-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§c§lRed"));
            }
            hasRedWool = null;
            hasRedArmor = null;
            hasRedWoolInventory = null;
        }

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        if(event.getWhoClicked().getGameMode() == GameMode.SURVIVAL) {

            if (event.getCurrentItem().getType().equals(Material.LEATHER_HELMET)
                        || event.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE)
                        || event.getCurrentItem().getType().equals(Material.LEATHER_LEGGINGS)
                        || event.getCurrentItem().getType().equals(Material.LEATHER_BOOTS)) {

                    event.setCancelled(true);

                }

        }else {
            return;
        }

    }




    public static String language = Main.INSTANCE.getConfig().getString("language");
    @EventHandler
    public void onFlagPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();


        if (event.getBlockPlaced().getType() == Material.WOOL) {
            if (event.getBlockPlaced().getData() == 14 || event.getBlockPlaced().getData() == 11) {

                player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place-wool").replace("&", "§"));
                event.setCancelled(true);
                return;
            }
        }

        ArrayList<Location> blockat = new ArrayList<>();
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -2; z <= 2; z++) {

                    double xs = Main.getNexusRouge().getX();
                    double ys = Main.getNexusRouge().getY();
                    double zs = Main.getNexusRouge().getZ();

                    Location loc = new Location(Main.getWorld(), xs + x - 0.5, ys + y, zs + z - 0.5);
                    blockat.add(loc);

                    xs = Main.getNexusBleu().getX();
                    ys = Main.getNexusBleu().getY();
                    zs = Main.getNexusBleu().getZ();

                    loc = new Location(Main.getWorld(), xs + x - 0.5, ys + y, zs + z - 0.5);
                    blockat.add(loc);
                }
            }

        }
        if (blockat.contains(event.getBlockPlaced().getLocation())) {
            event.setCancelled(true);
            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));
        }

    }

    @EventHandler
    public void onFlagBreak(BlockBreakEvent event) {


        Player player = event.getPlayer();

        if (!GameStatut.isStatut(GameStatut.INLOBBY)) {
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
        for (int x = -margeflag; x <= margeflag; x++) {
            for (int y = -margeflag; y <= margeflag; y++) {
                for (int z = -margeflag; z <= margeflag; z++) {

                    double xs = Main.getNexusRouge().getX();
                    double ys = Main.getNexusRouge().getY();
                    double zs = Main.getNexusRouge().getZ();

                    Location loc = new Location(Main.getWorld(), xs + x - 0.5, ys + y, zs + z - 0.5);
                    blockatrouge.add(loc);

                    xs = Main.getNexusBleu().getX();
                    ys = Main.getNexusBleu().getY();
                    zs = Main.getNexusBleu().getZ();

                    loc = new Location(Main.getWorld(), xs + x - 0.5, ys + y, zs + z - 0.5);
                    blockatbleu.add(loc);
                }
            }

        }
        if (blockatrouge.contains(event.getBlock().getLocation())) {
            if (event.getBlock().getType() != Material.WOOL && event.getBlock().getType() != Material.AIR && !TeamSelect.teamRouge.contains(player)) {
                event.setCancelled(true);
                player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));

            } else {
                if (TeamSelect.teamBleu.contains(player)) {
                    if (GameStatut.isStatut(GameStatut.INLOBBY)) {
                        player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.no-one").replace("&", "§"));
                        event.setCancelled(true);
                        return;
                    }
                    hasRedArmor = player.getInventory().getArmorContents();
                    hasRedWoolInventory = player.getInventory().getContents();
                    hasRedWool = player;
                    player.getInventory().clear();
                    if(language.equalsIgnoreCase("fr")){
                        Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.take-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§c§lRouge"));
                    }else if(language.equalsIgnoreCase("es")){
                        Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.take-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§c§lRojo"));
                    }else{
                        Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.take-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§c§lRed"));
                    }
                    ItemStack[] armor = player.getInventory().getArmorContents();


                    for (int i = 0; i < 9; i++) {
                        player.getInventory().setItem(i, new ItemStack(Material.WOOL, 1, (short) 0, (byte) 14));

                    }


                    armor[0].setType(Material.AIR);
                    armor[1].setType(Material.AIR);
                    armor[2].setType(Material.AIR);
                    armor[3] = new ItemStack(new ItemStack(Material.WOOL, 1, (short) 0, (byte) 14));
                    player.getInventory().setArmorContents(armor);


                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1f, 1f);

                    }
                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1f, 1f);

                            }

                        }
                    }, 20);

                } else if (TeamSelect.teamRouge.contains(player)) {
                    event.setCancelled(true);
                    player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.own-wool").replace("&", "§"));
                }
            }
        }
        if (blockatbleu.contains(event.getBlock().getLocation())) {
            if (event.getBlock().getType() != Material.WOOL && event.getBlock().getType() != Material.AIR && !TeamSelect.teamBleu.contains(player)) {
                event.setCancelled(true);
                player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));

            } else {
                if (TeamSelect.teamRouge.contains(player)) {
                    if (GameStatut.isStatut(GameStatut.INLOBBY)) {
                        player.sendMessage(Main.getPrefix() + MessageYaml.getValue("team.no-one").replace("&", "§"));
                        ;
                        event.setCancelled(true);
                        return;
                    }

                    hasBlueArmor = player.getInventory().getArmorContents();
                    hasBlueWoolInventory = player.getInventory().getContents();
                    hasBlueWool = player;
                    player.getInventory().clear();
                    if(language.equalsIgnoreCase("fr")){
                        Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.take-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§9§lBleu"));
                    }else if(language.equalsIgnoreCase("es")){
                        Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.take-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§9§lAzul"));
                    }else{
                        Bukkit.broadcastMessage(Main.getPrefix() + MessageYaml.getValue("team.take-wool").replace("&", "§").replace("{player}", player.getName()).replace("{wool-color}", "§9§lBlue"));
                    }

                    for (int i = 0; i < 9; i++) {
                        player.getInventory().setItem(i, new ItemStack(Material.WOOL, 1, (short) 0, (byte) 11));

                    }

                    ItemStack[] armor = player.getInventory().getArmorContents();
                    armor[0].setType(Material.AIR);
                    armor[1].setType(Material.AIR);
                    armor[2].setType(Material.AIR);
                    armor[3] = new ItemStack(new ItemStack(Material.WOOL, 1, (short) 0, (byte) 11));
                    player.getInventory().setArmorContents(armor);

                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1f, 1f);

                    }
                    Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1f, 1f);

                            }

                        }
                    }, 20);

                } else if (TeamSelect.teamBleu.contains(player)) {
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

                    double xs = Main.getSpawnBleu().getX() - 0.5;
                    double ys = Main.getSpawnBleu().getY();
                    double zs = Main.getSpawnBleu().getZ() - 0.5;
                    Location loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0, 0);
                    blockat.add(loc);


                    xs = Main.getSpawnRouge().getX() - 0.5;
                    ys = Main.getSpawnRouge().getY();
                    zs = Main.getSpawnRouge().getZ() - 0.5;
                    loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0, 0);
                    blockat.add(loc);
                }
            }
        }
        if (blockat.contains(event.getBlockPlaced().getLocation())) {
            event.setCancelled(true);
            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));
        }
        //INFINITE BLOCKS
        if (event.isCancelled()) {
            return;
        }
        if (!Main.INSTANCE.getConfig().getBoolean("blocks.infinite-sandstone")) {
            return;
        }

        if (event.getBlockPlaced().getType() == Material.SANDSTONE) {
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


        if(Main.INSTANCE.getConfig().getBoolean("blocks.infinite-sandstone")){
            if(event.getBlock().getType().equals(Material.SANDSTONE)) {

                Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        event.getBlock().setType(Material.AIR);
                    }
                }, 1);

            }
        }



        for (int x = -margeX; x <= margeX; x++) {
            for (int y = -margeY; y <= margeY; y++) {
                for (int z = -margeZ; z <= margeZ; z++) {

                    double xs = Main.getSpawnBleu().getX() - 0.5;
                    double ys = Main.getSpawnBleu().getY();
                    double zs = Main.getSpawnBleu().getZ() - 0.5;
                    Location loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0, 0);
                    blockat.add(loc);


                    xs = Main.getSpawnRouge().getX() - 0.5;
                    ys = Main.getSpawnRouge().getY();
                    zs = Main.getSpawnRouge().getZ() - 0.5;
                    loc = new Location(Main.getWorld(), xs + x, ys + y, zs + z, 0, 0);
                    blockat.add(loc);
                }
            }
        }
        if (blockat.contains(event.getBlock().getLocation())) {
            event.setCancelled(true);
            player.sendMessage(Main.getPrefix() + MessageYaml.getValue("blocks.cant-place").replace("&", "§"));
        }

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        event.setCancelled(true);

        Bukkit.broadcastMessage(getTeam(player) + message);


    }


    private String getTeam(Player player) {

        if (TeamSelect.teamBleu.contains(player)) {
            return MessageYaml.getValue("team.chat-blue").replace("&", "§").replace("{player}", player.getName());
        } else if (TeamSelect.teamRouge.contains(player)) {
            return MessageYaml.getValue("team.chat-red").replace("&", "§").replace("{player}", player.getName());
        }


        return MessageYaml.getValue("team.chat-default").replace("&", "§").replace("{player}", player.getName());
    }

}
