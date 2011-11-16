package org.whired.rskel.player.event;

import java.awt.Point;

/**
 * Listens for player movement events
 * @author Whired
 */
public interface PlayerEventListener {
	/**
	 * Invoked when a player moves
	 * @param oldLocation the location the player moved from
	 * @param newLocation the location the player moved to
	 */
	public void playerMoved(Point oldLocation, Point newLocation);
}
