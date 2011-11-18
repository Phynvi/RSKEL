package org.whired.rskel.item;

import org.whired.rskel.geom.Point3D;


/**
 * An item for the world
 * @author Whired
 */
public class WorldItem extends Item {
	private final Point3D location;
	
	/**
	 * Creates a new world item with the given name and location
	 * @param name the name of the item
	 * @param location the location of the item
	 */
	public WorldItem(String name, Point3D location) {
		super(name);
		this.location = location;
	}
	
	/**
	 * Creates a world item version of the given item
	 * @param item the item to create a world item for
	 * @param location the location of the world item
	 * @return the world item that was created
	 */
	public static WorldItem create(Item item, Point3D location) {
		return new WorldItem(item.getName(), location);
	}
	
	/**
	 * Gets the location of this world item
	 * @return the location
	 */
	public Point3D getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return super.toString() + " "+getLocation();
	}
}
