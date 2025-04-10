package net.zypr.imgTrail;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandController implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String @NotNull [] args) {
        if (command.getName().equals("imgtrail")) {
            if (sender instanceof Player player) {
                if (args.length == 0) {
                    sender.sendMessage("Usage: /imgtrail <url>");
                    return true;
                }
                String url = args[0];
                Trail trail = new Trail(url);
                sender.sendMessage("Trail created from URL: " + url);
                ImgTrail.getInstance().getTrailExecutor().setPlayerTrail(player, trail);
                return true;
            }
        }
        return false;
    }
}
