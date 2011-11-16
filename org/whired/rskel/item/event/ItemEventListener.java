package org.whired.rskel.item.event;

import org.whired.rskel.item.Item;

/**
 * Listens for events pertaining to items
 * @author Whired
 */
public interface ItemEventListener {
	
	/**
	 * Invoked when an item is added to a container
	 * @param item the item that was added
	 */
	public void itemAdded(Item item);
	
	/**
	 * Invoked when an item is removed from a container
	 */
	public void itemRemoved(Item item);
}
