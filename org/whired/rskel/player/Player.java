package org.whired.rskel.player;

import org.whired.rskel.World;

/**
 * A player
 * @author Whired
 */
public abstract class Player {
	private final String name;
	private World world;
	/**
	 * Creates a player with the given name
	 * @param world the world this player is in
	 * @param name the name of this player
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the world for this player
	 * @param world the world this player is in
	 */
	protected void setWorld(World world) {
		this.world = world;
	}
	
	/**
	 * Gets the name of this player
	 * @return 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the world this player is in
	 * @return the world
	 */
	public World getWorld() {
		return this.world;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * Sends a message to this player
	 * @param message the message to send
	 */
	public abstract void sendMessage(String message);
}
