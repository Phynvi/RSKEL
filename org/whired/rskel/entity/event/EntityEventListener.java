package org.whired.rskel.entity.event;

import org.whired.rskel.geom.Point3D;

/**
 * Listens for entity events
 * @author Whired
 */
public interface EntityEventListener {
	/**
	 * Invoked when an entity moves
	 * @param from the point the entity moved from
	 * @param to the point the entity moved to
	 */
	public void entityMoved(Point3D from, Point3D to);
}
