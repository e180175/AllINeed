package be.alaskalix.builder;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.milkbowl.vault.chat.Chat;

public class ChatBuilder {

	private AsyncPlayerChatEvent even;
	private Chat chat;

	public ChatBuilder(AsyncPlayerChatEvent even, Chat chat) {
		this.even = even;
		this.chat = chat;
	}

	public String getChat() {
		return "§r<" + getPrefix() + "§r " + buildName() + "§r> "  + getMessage();
	}

	private String buildName() {
		if (getPlayer().isOp()) {
			return ChatColor.RED + getName();
		} else {
			return getName();
		}
	}

	private String getMessage() {
		return even.getMessage().replace('&', '§');
	}

	private String getName() {
		return getPlayer().getName();
	}

	private String getPrefix() {
		return chat.getPlayerPrefix(getPlayer()).replace('&', '§');
	}

	private Player getPlayer() {
		return even.getPlayer();
	}

}
