package org.whired.rskel.player;

import java.util.HashMap;
import java.util.HashSet;
import org.whired.rskel.world.World;
import org.whired.rskel.player.event.PlayerListEventListener;

/**
 * A managed list for players
 * @author Whired
 */
public class PlayerList {
	private final HashMap<String, Player> players = new HashMap<String, Player>();
	private final HashSet<PlayerListEventListener> listeners = new HashSet<PlayerListEventListener>();
	private final World world;
	
	/**
	 * Creates a new list of players for the specified world
	 * @param world the world that this list is for
	 */
	public PlayerList(World world) {
		this.world = world;
	}
	
	/**
	 * Adds a player to this list
	 * @param player the player to add
	 */
	public void add(Player player) {
		players.put(player.getName(), player);
		for(PlayerListEventListener listener : listeners) {
			listener.playerAdded(this, player);
		}
	}
	
	/**
	 * Removes a player from this list
	 * @param player the player to remove
	 */
	public void remove(Player player) {
		players.remove(player.getName());
		for(PlayerListEventListener listener : listeners) {
			listener.playerRemoved(this, player);
		}
	}
	
	/**
	 * Adds a listener for this list
	 * @param listener the listener to add
	 */
	public void addListener(PlayerListEventListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes a listener for this list
	 * @param listener the listener to remove
	 */
	public void removeListener(PlayerListEventListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Gets the current collection of players as an array
	 * @return a {@code Player[]} of the current players
	 */
	public Player[] toArray() {
		Player[] arr = new Player[players.size()];
		for(int i = 0; i < players.size(); i++)
			arr[i] = (Player)players.values().toArray()[i];
		return arr;
	}
}
