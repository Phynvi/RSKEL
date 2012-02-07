package org.whired.forgequest.map;

/**
 * A region that contains tiles
 * 
 * @author Whired
 */
public class Region {
	private final short x, y;
	private final byte width, height, zHeight;
	public final TexturedTile[] tiles;

	/**
	 * Creates a new region for the specified bounds and height with the given tiles
	 * 
	 * @param bounds the bounds of the region
	 * @param zHeight the height (Z) of the region
	 * @param tiles the tiles in the region
	 */
	public Region(short x, short y, byte width, byte height, byte zHeight, TexturedTile[] tiles) {
		if (tiles.length > Byte.MAX_VALUE * Byte.MAX_VALUE) {
			throw new IllegalArgumentException("Region too large! tiles.length must be <= " + Byte.MAX_VALUE * Byte.MAX_VALUE);
		}
		if (width * height != tiles.length) {
			throw new IllegalArgumentException("Region size mismatch! tiles.length != width*height");
		}
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.zHeight = zHeight;
		this.tiles = tiles;
	}

	/**
	 * Gets the upper x-coordinate of this region
	 * 
	 * @return the x
	 */
	public short getX() {
		return x;
	}

	/**
	 * Gets the upper y-coordinate of this region
	 * 
	 * @return the y
	 */
	public short getY() {
		return y;
	}

	/**
	 * Gets the width of this region
	 * 
	 * @return the width
	 */
	public byte getWidth() {
		return width;
	}

	/**
	 * Gets the height of this region
	 * 
	 * @return the height
	 */
	public byte getHeight() {
		return height;
	}

	/**
	 * Gets the height (Z) of this region
	 * 
	 * @return the height (Z)
	 */
	public byte getZHeight() {
		return this.zHeight;
	}
}
