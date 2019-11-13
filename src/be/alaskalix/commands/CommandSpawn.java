package be.alaskalix.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.alaskalix.main.Main;

public class CommandSpawn implements CommandExecutor {

	private Main main;
	private Location spawn;

	public CommandSpawn(Main main) {
		this.main = main;
		this.spawn = new Location(Bukkit.getWorld("world"), getCoord("x"), getCoord("y"), getCoord("z"), getCoord("yaw").floatValue(), getCoord("pitch").floatValue());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg3) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			p.teleport(spawn);
			return true;
		}
		return false;
	}

	public Location getSpawn() {
		return spawn;
	}

	private Double getCoord(String coord) {
		return main.getConfig().getDouble("spawnpoint.world.spawn." + coord);
	}
}
