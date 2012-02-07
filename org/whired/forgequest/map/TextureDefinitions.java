package org.whired.forgequest.map;

import java.util.HashMap;

public class TextureDefinitions {
	private static final HashMap<Short, Texture> textures = new HashMap<Short, Texture>();

	public static Texture forId(Short id) {
		return textures.get(id);
	}

	public static void register(Texture text) {
		textures.put(text.getId(), text);
	}
}
