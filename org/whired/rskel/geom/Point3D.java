package org.whired.rskel.geom;

import java.awt.Point;

/**
 * A 3D point
 * @author Whired
 */
public class Point3D extends Point {
	public int z;
	
	/**
	 * Creates a new point of (0,0,0)
	 */
	public Point3D() {
		this(0,0,0);
	}
	
	/**
	 * Creates a new point at the given coordinates
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @param z the z-coordinate
	 */
	public Point3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
	
	/**
	 * Gets the z-coordinate of this point
	 * @return the z-coordinate
	 */
	public int getZ() {
		return this.z;
	}
	
	/**
	 * Sets the z-coordinate of this point
	 * @param z the z-coordinate to set
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	@Override
	public String toString() {
		return "("+x+", "+y+", "+z+")";
	}
}
