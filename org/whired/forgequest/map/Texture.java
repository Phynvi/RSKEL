package org.whired.forgequest.map;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A graphical texture that can be applied to tiles
 * 
 * @author Whired
 */
public class Texture {

	private final Image image;
	private final byte width;
	private final byte height;
	private final short id;

	private Texture(BufferedImage img, short id) {
		this.id = id;
		this.image = img;
		this.width = (byte) img.getWidth();
		this.height = (byte) img.getHeight();
	}

	public static Texture load(String path, byte id) throws IOException {
		return new Texture(ImageIO.read(new File(path)), id);
	}

	/**
	 * Gets the image for this texture
	 * 
	 * @return the image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Gets the width for this image
	 * 
	 * @return the width
	 */
	public byte getWidth() {
		return this.width;
	}

	/**
	 * Gets the height for this image
	 * 
	 * @return the height
	 */
	public byte getHeight() {
		return this.height;
	}

	/**
	 * Gets the id of this tile
	 * 
	 * @return the id
	 */
	public short getId() {
		return this.id;
	}
}
