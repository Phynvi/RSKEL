package org.whired.forgequest.map;

import org.whired.rskel.world.WorldObject;

/**
 * A world tile
 * 
 * @author Whired
 */
public class BasicTile {
	private boolean walkable;
	private boolean blocking;
	private WorldObject object;
	private final byte width, height;

	/**
	 * Creates a new tile with the specified width and height
	 * 
	 * @param width the width
	 * @param height the height
	 */
	public BasicTile(byte width, byte height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Gets the width of this tile
	 * 
	 * @return the width
	 */
	public byte getWidth() {
		return this.width;
	}

	/**
	 * Gets the height of this tile
	 * 
	 * @return the height
	 */
	public byte getHeight() {
		return this.height;
	}

	/**
	 * Gets whether or not this tile is walkable
	 * 
	 * @return {@code true} if the tile is walkable, otherwise {@code false}
	 */
	public boolean isWalkable() {
		return walkable;
	}

	/**
	 * Sets whether or not this tile is walkable
	 * 
	 * @param walkable
	 */
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	/**
	 * Gets whether or not this tile blocks projectiles
	 * 
	 * @return {@code true} if the tile blocks projectiles, otherwise {@code false}
	 */
	public boolean isBlocking() {
		return blocking;
	}

	/**
	 * Sets whether or not this tile blocks projectiles
	 * 
	 * @param blocks
	 */
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	/**
	 * Gets the object on this tile
	 * 
	 * @return the object
	 */
	public WorldObject getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(WorldObject object) {
		this.object = object;
	}
}
