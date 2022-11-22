package fr.haxy972.RushTheFlag.Managers;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {

    Player player;

    public SoundManager(Player player){
        this.player = player;
    }

    public void play(Sound sound, Float intensity){
        player.playSound(player.getLocation(), sound,intensity, intensity);
    }

    public void play(Sound sound){
        player.playSound(player.getLocation(), sound,1f, 1f);
    }

    public void playerSucess(){
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1f, 1f);
    }
}
