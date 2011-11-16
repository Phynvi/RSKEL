package org.whired.rskel.player;

import java.awt.Point;
import org.whired.rskel.World;
import org.whired.rskel.item.ItemContainer;
import org.whired.rskel.player.event.PlayerEventListener;

/**
 * A player
 * @author Whired
 */
public abstract class Player implements PlayerEventListener {

	private final String name;
	private World world;
	private Point location = new Point(0, 0);
	private ItemContainer bank;
	private ItemContainer inventory;
	private ItemContainer beastOfBurden;

	/**
	 * Creates a player with the given name
	 * @param world the world this player is in
	 * @param name the name of this player
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Gets this player's inventory
	 * @return the inventory for this player
	 */
	public ItemContainer getInventory() {
		return this.inventory;
	}
	
	/**
	 * Sets this player's inventory
	 * @param inventory the inventory to set
	 */
	public void setInventory(ItemContainer inventory) {
		this.inventory = inventory;
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

	/** 
	 * Sets the location of this player
	 * @param newLocation the location to set
	 */
	public void setLocation(Point newLocation) {
		if (newLocation.x == this.location.x && newLocation.y == this.location.y) {
			return;
		}
		playerMoved(this.location, newLocation);
		this.location = newLocation;
	}

	/**
	 * Sets the location of this player
	 * @param x the x-coordinate to set
	 * @param y the y-coordinate to set
	 */
	public void setLocation(int x, int y) {
		if (this.location.x == x && this.location.y == y) {
			return;
		}
		Point newLocation = new Point(x, y);
		playerMoved(this.location, newLocation);
		this.location = newLocation;
	}

	/**
	 * Gets the location of this player
	 * @return the location
	 */
	public Point getLocation() {
		return this.location;
	}
}
