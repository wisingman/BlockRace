package uk.maxmoore.blockrace.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.ChatColor;

public class CC {

    public static Component translate(String str) {
        str = translateString(str);
        return MiniMessage.miniMessage().deserialize(str);
    }

    public static String translateString(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

}
