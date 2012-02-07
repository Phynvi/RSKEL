package org.whired.forgequest.map;

public class TexturedTile extends BasicTile {
	private final short textureId;

	/**
	 * Creates a new tile with the specified texture
	 * 
	 * @param text the texture
	 */
	public TexturedTile(byte width, byte height, short textureId) {
		super(width, height);
		this.textureId = textureId;
	}

	/**
	 * Gets the texture for this tile
	 * 
	 * @return the texture
	 */
	public short getTextureId() {
		return this.textureId;
	}
}
