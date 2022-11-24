package fr.haxy972.RushTheFlag.Utils;

import org.bukkit.entity.Player;

public class PluginMessage {


    private final Player recipient;
    public PluginMessage(Player player){this.recipient = player;}

    public void Err(String text){
        send("§cError §8§l> §7" + text);
    }

    public void Notif(String text){
        send("§aNotification §8§l> §7" + text);
    }

    private void send(String message){
        recipient.sendMessage(message);
    }

}
