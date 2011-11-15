package org.whired.rskel;

import org.whired.rskel.player.Player;
import org.whired.rskel.player.PlayerList;

/**
 * A game world
 * @author Whired
 */
public class World {
	private final boolean membersOnly;
	private final PlayerList players = new PlayerList(this);
	
	/**
	 * Creates a new world
	 * @param membersOnly whether or not this world is members only
	 */
	public World(boolean membersOnly) {
		this.membersOnly = membersOnly;
	}
	
	/**
	 * Creates a new world for free and member players
	 */
	public World() {
		this.membersOnly = false;
	}
	
	/**
	 * Checks whether or not this world is members only
	 * @return {@code true} if this world is members only, otherwise {@code false}.
	 */
	public boolean isMembersOnly() {
		return this.membersOnly;
	}
	
	/**
	 * Gets the list of active players in this world
	 * @return the list
	 */
	public PlayerList getPlayers() {
		return this.players;
	}
	
	/**
	 * Creates a message for all players in this world
	 * @param message the message to create
	 */
	public void createMessage(String message) {
		for(Player player : getPlayers().toArray())
			player.sendMessage(message);
	}
}
