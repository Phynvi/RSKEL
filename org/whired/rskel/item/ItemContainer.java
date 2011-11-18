package org.whired.rskel.item;

import java.util.LinkedList;
import org.whired.rskel.item.event.ItemEventListener;
import org.whired.rskel.item.event.ItemListEventListener;
import org.whired.rskel.entity.Player;

/**
 * A container for items
 * @author Whired
 */
public abstract class ItemContainer extends ItemList implements ItemEventListener {
	private final int capacity;
	private final Player owner;
	
	/**
	 * Creates a new container with the specified capacity
	 * @param owner the owner of this container
	 * @param capacity the maximum amount of items that can be stored in this container
	 */
	public ItemContainer(Player owner, int capacity) {
		this.owner = owner;
		this.capacity = capacity;
	}
	
	/**
	 * Adds the specified items to this container
	 * @param items the items to add
	 */
	public final void addAll(Item[] items) throws ContainerOverflowException {
		for(int i = 0; i < items.length; i++) {
			if(!add(items[i])) {
				Item[] overflow = new Item[items.length-i];
				for(int x = 0; x < overflow.length; x++)
					overflow[x] = items[i++];
				throw new ContainerOverflowException("Could not add all items. Overflow: "+overflow.length, this, overflow);
			}
		}
	}
	
	/**
	 * Transfers an item from this container to another container
	 * @param item the item to transfer
	 * @param to the container to transfer to
	 */
	public void transfer(Item item, ItemContainer to) {
		remove(item);
		to.add(item);
	}
	
	/**
	 * Transfers all items from this container to the specified container
	 * @param to the container to transfer to
	 * @throws ContainerOverflowException if the specified container does not meet the required capacity
	 */
	public void transferAll(ItemContainer to) throws ContainerOverflowException {
		Item[] iarr = getAll();
		empty();
		to.addAll(iarr);
	}
	
	/**
	 * Adds the specified item to this container
	 * @param item the item to add
	 * @return whether or not the item could be added
	 */
	@Override
	public boolean add(Item item) {
		if(getSize() < capacity) {
			item.setContainer(this);
			return super.add(item);
		}
		else {
			return false;
		}
	}
	
	public void drop(int index) {
		Item i = remove(index);
		itemDropped(i);
	}
	
	public void drop(Item item) {
		remove(item);
		itemDropped(item);
	}
	
	/**
	 * Thrown when an attempt to add items to a full container is made
	 */
	public class ContainerOverflowException extends Exception {
		private final ItemContainer container;
		private final Item[] overflowItems;
		public ContainerOverflowException(String message, ItemContainer container, Item[] overflowItems) {
			super(message);
			this.container = container;
			this.overflowItems = overflowItems;
		}
		
		/**
		 * Gets the items that could not be added
		 */
		public Item[] getOverflowItems() {
			return this.overflowItems;
		}
		
		/**
		 * Gets the container that threw this exception
		 */
		public ItemContainer getSourceContainer() {
			return this.container;
		}
	}
	
	/**
	 * Gets the owner of this container
	 * @return the owner
	 */
	public Player getOwner() {
		return this.owner;
	}
}
