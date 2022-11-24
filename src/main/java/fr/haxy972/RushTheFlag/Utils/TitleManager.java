package fr.haxy972.RushTheFlag.Utils;

import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle.EnumTitleAction;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TitleManager {
    public TitleManager() {
    }

    public static void setPlayerList(Player player, String header, String footer) {
        IChatBaseComponent hj = ChatSerializer.a("{\"text\":\"" + header + "\"}");
        IChatBaseComponent fj = ChatSerializer.a("{\"text\":\"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter packet = (PacketPlayOutPlayerListHeaderFooter)construcHeaderAndFooterPacket(hj, fj);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    private static Object construcHeaderAndFooterPacket(Object header, Object footer) {
        try {
            Object packet = PacketPlayOutPlayerListHeaderFooter.class.newInstance();
            Field field;
            if (header != null) {
                field = PacketPlayOutPlayerListHeaderFooter.class.getDeclaredField("a");
                field.setAccessible(true);
                field.set(packet, header);
                field.setAccessible(false);
            }

            if (footer != null) {
                field = PacketPlayOutPlayerListHeaderFooter.class.getDeclaredField("b");
                field.setAccessible(true);
                field.set(packet, footer);
                field.setAccessible(false);
            }

            return packet;
        } catch (IllegalAccessException | NoSuchFieldException | InstantiationException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static final void sendTitle(Player player, String msgTitle, String msgSubTitle, int ticks) {
        IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\":\"" + msgTitle + "\"}");
        IChatBaseComponent chatSubTitle = ChatSerializer.a("{\"text\":\"" + msgSubTitle + "\"}");
        PacketPlayOutTitle p = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle p2 = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(p);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(p2);
        sendTime(player, ticks);
    }

    public static final void sendTime(Player player, int ticks) {
        PacketPlayOutTitle p = new PacketPlayOutTitle(EnumTitleAction.TIMES, (IChatBaseComponent)null, 20, ticks, 20);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(p);
    }

    public static final void sendActionBar(Player player, String message) {
        IChatBaseComponent cbc = ChatSerializer.a("{\"text\" :\"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(ppoc);
    }
}
