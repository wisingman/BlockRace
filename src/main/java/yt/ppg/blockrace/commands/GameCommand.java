package yt.ppg.blockrace.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yt.ppg.blockrace.Core;

public class GameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is in game only.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            return false;
        }

        String command = args[0].toLowerCase();
        switch (command) {
            case "start":
                Core.getInstance().startGame(player);
                break;
            case "reset":
                player.sendMessage("Resetting...");
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.kickPlayer("Game is resetting (Requested by " + player.getName() + ")");
                }
                Bukkit.shutdown();
                break;
            default:
                player.sendMessage("Unknown command");
        }

        return true;
    }
}
