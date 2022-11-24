package fr.haxy972.RushTheFlag.Managers;

import fr.haxy972.RushTheFlag.Utils.ItemCreator;
import net.minecraft.server.v1_9_R2.TileEntity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GameInventoryManager {

    Player player;
    public GameInventoryManager(Player player){
        this.player = player;
    }


    public void clear() {
        player.getInventory().clear();
    }

    public void setSpectatorItems(){
        clear();
        Inventory playerInventory = player.getInventory();
        playerInventory.setItem(0, new ItemCreator(Material.COMPASS).setName("§bTeleport to Player").setLores("§a§l> §fClick to teleport to other player").done());
        playerInventory.setItem(1, new ItemCreator(Material.REDSTONE_TORCH_ON).setName("§eJoin").setLores("§a§l> §fClick to teleport to join the game").done());
        playerInventory.setItem(7, new ItemCreator(Material.NETHER_STAR).setName("§cSwitch to Spectator Mode").setLores("§a§l> §fClick to switch mode").done());
        ItemStack skull = new ItemCreator(Material.SKULL_ITEM, 1 ,(byte)3).setName("§bYour stats").setLores("§a§l> §fClick to see your statistics").done();
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(player.getName());
        skull.setItemMeta(meta);
        playerInventory.setItem(4, skull);
        playerInventory.setItem(8, new ItemCreator(Material.BLAZE_POWDER).setName("§bHide other spectators").setLores("§a§l> §fClick to hide").done());
    }
}
