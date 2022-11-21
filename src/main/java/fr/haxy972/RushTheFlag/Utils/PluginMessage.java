package fr.haxy972.RushTheFlag.Utils;

import org.bukkit.entity.Player;

public class PluginMessage {


    private Player recipient;
    private String message;
    public PluginMessage(Player sender){this.recipient = recipient;}

    public void Err(String text){
        this.message = "§cError§8� §7" + text;
    }

    public void Send(){
        recipient.sendMessage(message);
    }

}
