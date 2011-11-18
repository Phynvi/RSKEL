package org.whired.rskel.item.event;

import org.whired.rskel.item.Item;

/**
 * Listens for item list events
 * @author Whired
 */
public interface ItemListEventListener<I extends Item> {
	/**
	 * Invoked when an item is added to a list
	 * @param item the item that was added
	 */
	public void itemAdded(I item);
	
	/**
	 * Invoked when an item is removed from a list
	 */
	public void itemRemoved(I item);
}
