package org.whired.rskel.item;

import java.util.ArrayList;
import java.util.LinkedList;
import org.whired.rskel.item.event.ItemListEventListener;

/**
 * A managed list for items
 * @author Whired
 */
public class ItemList<I extends Item> {
	private final LinkedList<I> items = new LinkedList<I>();
	private ArrayList<ItemListEventListener> listeners = new ArrayList<ItemListEventListener>();
	
	/**
	 * Adds a listener for this list
	 * @param listener the listener to add
	 */
	public void addListener(ItemListEventListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes a listener from this list
	 * @param listener the listener to remove
	 */
	public void removeListener(ItemListEventListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Gets the items in this list
	 * @return the items as an array
	 */
	public I[] getAll() {
		return this.items.toArray((I[]) new Item[this.items.size()]);
	}
	
	/**
	 * Gets the item at the specified index (slot), or null if the slot is empty
	 * @param index the index of the item to get
	 * @return the item
	 */
	public I get(int index) {
		return this.items.get(index);
	}
	
	/**
	 * Removes all items from this list
	 */
	public void empty() {
		while(!this.items.isEmpty())
			remove(this.items.getLast());
	}
	
	/**
	 * Removes the specified item from this container
	 * @param item the item to remove
	 */
	public void remove(I item) {
		for(ItemListEventListener l : listeners)
			l.itemRemoved(item);
		this.items.remove(item);
	}
	
	/**
	 * Removes the item at the specified index
	 * @param index the index of the item to remove
	 * @return the item that was removed
	 */
	public I remove(int index) {
		I i = this.items.get(index);
		remove(i);
		return i;
	}
	
	/**
	 * Gets the size of this list
	 * @return the size
	 */
	public int getSize() {
		return items.size();
	}
	
	/**
	 * Adds an item to this list
	 * @param item the item to add
	 * @return {@code true}
	 */
	public boolean add(I item) {
		this.items.addLast(item);
		for(ItemListEventListener l : listeners)
			l.itemAdded(item);
		return true;
	}
}
