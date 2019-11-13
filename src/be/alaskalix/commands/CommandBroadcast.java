package be.alaskalix.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBroadcast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "/broadcast <message>");
				return false;
			} else {
				StringBuilder bc = new StringBuilder();
				for (String string : args) {
					bc.append(string + " ");
				}
				Bukkit.broadcastMessage("§4[BroadCast]§r " + bc.toString().replace('&', '§'));
				return true;
			}
		} else {
			return false;
		}
	}
}
