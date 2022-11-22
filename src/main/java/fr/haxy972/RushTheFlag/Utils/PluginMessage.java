package fr.haxy972.RushTheFlag.Utils;

import org.bukkit.entity.Player;

public class PluginMessage {


    private final Player recipient;
    private String message;
    public PluginMessage(Player player){this.recipient = player;}

    public void Err(String text){
        this.message = "§cError §8§l> §7" + text;
        send();
    }

    public void send(){
        recipient.sendMessage(message);
    }

}
