package be.alaskalix.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPm implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "/pm <joueur> <message>");
			} else {
				StringBuilder bc = new StringBuilder();
				for (String string : args) {
					bc.append(string + " ");
				}
				String target = bc.toString().substring(0, bc.toString().indexOf(" "));
				String text = bc.toString().substring(bc.toString().indexOf(" ") + 1);
				Player targetPlayer = Bukkit.getPlayer(target);
				if (targetPlayer == null) {
					player.sendMessage(ChatColor.RED + "Player don't exist !");
				} else if (targetPlayer.isValid() && targetPlayer.isOnline()) {
					targetPlayer.sendMessage(ChatColor.GREEN + "[" + player.getName() + "]" + ChatColor.YELLOW
							+ " PM you->§r " + text.replace('&', '§'));
					player.sendMessage(ChatColor.GREEN + "PM send !");
				} else {
					player.sendMessage(ChatColor.RED + "Player not online!");
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
