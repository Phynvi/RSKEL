package org.whired.rskel.entity;

import org.whired.rskel.entity.event.EntityEventListener;
import org.whired.rskel.geom.Point3D;
import org.whired.rskel.world.World;

/**
 * A game entity
 * @author Whired
 */
public abstract class Entity implements EntityEventListener {
	private final String name;
	private Point3D location;
	private final World world;
	
	/**
	 * Creates a new entity with the specified name and location
	 * @param name the name of the entity
	 * @param location the location of the entity
	 */
	public Entity(String name, World world, Point3D location) {
		this.name = name;
		this.location = location;
		this.world = world;
	}
	
	/**
	 * Gets the name of this entity
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the location of this entity
	 * @return the location
	 */
	public Point3D getLocation() {
		return this.location;
	}
	
	/**
	 * Gets the world of this entity
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
	 * Sets the location of this player
	 * @param newLocation the location to set
	 */
	public void setLocation(Point3D newLocation) {
		if (newLocation.x == this.location.x && newLocation.y == this.location.y && newLocation.z == this.location.z) {
			return;
		}
		entityMoved(this.location, newLocation);
		this.location = newLocation;
	}

	/**
	 * Sets the location of this player
	 * @param x the x-coordinate to set
	 * @param y the y-coordinate to set
	 */
	public void setLocation(int x, int y, int z) {
		if (this.location.x == x && this.location.y == y && this.location.z == z) {
			return;
		}
		Point3D newLocation = new Point3D(x, y, z);
		entityMoved(this.location, newLocation);
		this.location = newLocation;
	}
}
