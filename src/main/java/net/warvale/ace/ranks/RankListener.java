package net.warvale.ace.ranks;

import net.warvale.ace.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.SQLException;

public class RankListener implements Listener {
    private Main plugin;
    public RankListener(Main plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(plugin.getConfig().getString("chatformat").equals("true")){
            Player player = event.getPlayer();
            String message = event.getMessage();
            event.setCancelled(true);
            String playerNameWithPrefixSuffix = player.getName() + ": ";
            try {
                playerNameWithPrefixSuffix = ChatColor.translateAlternateColorCodes('&', (!RankManager.getRankPrefix(player).equals(" ")?RankManager.getRankPrefix(player) : "") + RankManager.getRankNameColor(player) +" "+ player.getName() +(!RankManager.getRankSuffix(player).equals(" ")? " "+ RankManager.getRankSuffix(player) : "") + ChatColor.GRAY + ": ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for(Player p : event.getRecipients()){
                p.sendMessage(playerNameWithPrefixSuffix + ChatColor.WHITE + message);
            }
        }
    }
}
