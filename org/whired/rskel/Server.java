package org.whired.rskel;

import org.whired.rskel.world.World;
import java.util.Arrays;
import java.util.HashSet;

/**
 * A server
 * @author Whired
 */
public abstract class Server implements Runnable {
	private final int port;
	private final HashSet<World> worlds = new HashSet<World>();
	
	/**
	 * Creates a new server on the default port of {@code 43594} for the specified worlds
	 * @param worlds the worlds to host
	 */
	public Server(World[] worlds) {
		this(43594, worlds);
	}
	
	/**
	 * Creates a new server for the specified port and worlds
	 * @param port the port on which the server will listen
	 * @param worlds the worlds to host
	 */
	public Server(int port, World[] worlds) {
		this.port = port;
		this.worlds.addAll(Arrays.asList(worlds));
	}
	
	/**
	 * Gets the port on which this server is listening
	 * @return the port
	 */
	public int getPort() {
		return this.port;
	}
}
