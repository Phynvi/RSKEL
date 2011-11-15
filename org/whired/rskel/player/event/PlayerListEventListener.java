package org.whired.rskel.player.event;

import org.whired.rskel.player.Player;
import org.whired.rskel.player.PlayerList;

/**
 * Listens for events raised by a {@link org.whired.rskel.player.PlayerList}
 * @author Whired
 */
public interface PlayerListEventListener {
	/**
	 * Invoked when a player is added to a list
	 * @param list the list that fired this event
	 * @param player the player that was added
	 */
	public void playerAdded(PlayerList list, Player player);
	
	/**
	 * Invoked when a player is removed from a list
	 * @param list the list that fired this event
	 * @param player the player that was removed
	 */
	public void playerRemoved(PlayerList list, Player player);
}
