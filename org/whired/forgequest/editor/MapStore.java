package org.whired.forgequest.editor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.whired.forgequest.map.Region;
import org.whired.forgequest.map.TexturedTile;

public class MapStore {
	public static void saveMap(String path, Region[] regions) throws IOException {
		FileOutputStream os = new FileOutputStream(path);

		// Write region length
		os.write(regions.length);

		for (Region r : regions) {
			// Write region x
			writeShort(os, r.getX());

			// Write region y
			writeShort(os, r.getY());

			// Write region width
			byte width = r.getWidth();
			os.write(width);

			// Write region height
			os.write(r.getHeight());

			// Write region z-height
			os.write(r.getZHeight());

			// Write tile length
			writeShort(os, (short) r.tiles.length);

			for (int i = 0; i < r.tiles.length; i++) {
				TexturedTile t = r.tiles[i];
				if (t != null) {
					os.write(1);

					// Write tile x
					int x = i % width;
					os.write(x);

					// Write tile y
					os.write((i - x) / width);

					// Write tile width
					os.write(t.getWidth());

					// Write tile height
					os.write(t.getHeight());

					// Write texture id
					writeShort(os, t.getTextureId());

					// Write the tile walkable flag
					os.write(t.isWalkable() ? 1 : 0);

					// Write the blockable flag
					os.write(t.isBlocking() ? 1 : 0);
				}
				else {
					os.write(0);
				}
			}
		}
		os.close();
	}

	private static void writeShort(OutputStream os, short s) throws IOException {
		os.write((byte) ((s >> 8) & 0xFF));
		os.write((byte) (s & 0xFF));
	}

	private static short readShort(InputStream is) throws IOException {
		byte high = (byte) is.read();
		byte low = (byte) is.read();
		return (short) (((high & 0xFF) << 8) | (low & 0xFF));
	}

	public static Region[] loadMap(String path) throws IOException {
		FileInputStream is = new FileInputStream(path);

		// Read region length
		Region[] reg = new Region[is.read()];

		// Read regions
		for (byte i = 0; i < reg.length; i++) {

			// Read region props
			short x, y;
			byte width, height, zHeight;
			x = readShort(is);
			y = readShort(is);
			width = (byte) is.read();
			height = (byte) is.read();
			zHeight = (byte) is.read();

			// Read tile length
			short len = readShort(is);
			TexturedTile[] tiles = new TexturedTile[len];

			// Read tiles
			for (short s = 0; s < len; s++) {
				// Read tile availability
				if (is.read() == 1) {
					TexturedTile tile;

					// Read location
					byte tx = (byte) is.read();
					byte ty = (byte) is.read();

					tile = new TexturedTile((byte) is.read(), (byte) is.read(), readShort(is));
					tile.setWalkable(is.read() == 1);
					tile.setBlocking(is.read() == 1);
					tiles[tx + ty * width] = tile;
				}
			}
			reg[i] = new Region(x, y, width, height, zHeight, tiles);
		}
		return reg;
	}
}
