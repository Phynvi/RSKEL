package org.whired.forgequest.client.render;

import java.awt.Graphics;

import org.whired.forgequest.map.Region;
import org.whired.forgequest.map.TextureDefinitions;

public class RegionRenderer {
	public static void renderRegion(Graphics g, Region region) {
		int regWid = region.getWidth();
		int xOffs = 0;
		int yOffs = 0;
		for (int i = 0; i < region.tiles.length; i++) {
			if (region.tiles[i] != null) {
				int x = i % regWid;
				int y = (i - x) / regWid;
				xOffs = x != 0 ? x * region.tiles[i].getWidth() : 0;
				yOffs = y != 0 ? y * region.tiles[i].getHeight() : 0;
				g.drawImage(TextureDefinitions.forId(region.tiles[i].getTextureId()).getImage(), xOffs, yOffs, null);
			}
		}
	}
}
