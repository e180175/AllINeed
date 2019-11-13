package be.alaskalix.main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import be.alaskalix.builder.ChatBuilder;
import be.alaskalix.commands.CommandBroadcast;
import be.alaskalix.commands.CommandPm;
import be.alaskalix.commands.CommandSpawn;
import net.milkbowl.vault.chat.Chat;

public class Main extends JavaPlugin implements Listener {

	private static Chat chat = null;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
		getCommand("pm").setExecutor(new CommandPm());
		getCommand("broadcast").setExecutor(new CommandBroadcast());
		getCommand("spawn").setExecutor(new CommandSpawn(this));
	}

	@Override
	public void onDisable() {

	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		return chat != null;
	}

	@EventHandler
	public void changeWorld(PlayerChangedWorldEvent even) {
		Player p = even.getPlayer();
		p.setGameMode(GameMode.SURVIVAL);
	}

	@EventHandler
	public void chat(AsyncPlayerChatEvent even) {
		if (setupChat()) {
			ChatBuilder cb = new ChatBuilder(even, chat);
			even.setFormat(cb.getChat());
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent even) {
		even.getPlayer().teleport(new CommandSpawn(this).getSpawn());
		even.getPlayer().setGameMode(GameMode.SURVIVAL);
	}

	@EventHandler
	public void onDead(PlayerRespawnEvent even) {
		even.setRespawnLocation(new CommandSpawn(this).getSpawn());
	}

	@EventHandler
	public void OnRespawn(PlayerRespawnEvent even) {
		even.getPlayer().setGameMode(GameMode.SURVIVAL);
	}
}
