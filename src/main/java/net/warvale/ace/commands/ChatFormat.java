package net.warvale.ace.commands;

import net.warvale.ace.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatFormat extends AbstractCommand {
    private Main plugin;
    public ChatFormat(Main plugin){
        super("chatformat", "<true|false>");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if(!(sender instanceof Player)){
            throw new CommandException("Only players can execute this command");
        }
        Player player = (Player) sender;
        if(args.length != 1){
            return false;
        }
        if(!(args[0].equals("true") || args[0].equals("false"))){
            return false;
        }
        plugin.getConfig().set("chatformat", args[0]);
        player.sendMessage(ChatColor.GREEN + (args[0].equals("true")?"Ace will now change the way player's names are displayed in chat!":"Ace will no longer directly effect the way player's names are displayed in chat!"));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
