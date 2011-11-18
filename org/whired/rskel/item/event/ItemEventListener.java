package org.whired.rskel.item.event;

import org.whired.rskel.item.Item;

/**
 * Listens for events pertaining to items
 * @author Whired
 */
public interface ItemEventListener {
	
	
	
	/**
	 * Invoked when an item is dropped
	 */
	public void itemDropped(Item item);
}
