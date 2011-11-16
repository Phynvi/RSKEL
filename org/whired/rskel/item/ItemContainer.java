package org.whired.rskel.item;

import java.util.LinkedList;
import org.whired.rskel.item.event.ItemEventListener;
import org.whired.rskel.player.Player;

/**
 * A container for items
 * @author Whired
 */
public abstract class ItemContainer implements ItemEventListener {
	private final LinkedList<Item> items;
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
		this.items = new LinkedList<Item>();
	}

	/**
	 * Creates a new container with the specified capacity and initial items
	 * @param owner the owner of this container
	 * @param capacity the maximum amount of items that can be stored in this container
	 * @param items the items that will be initially present in this container
	 */
	public ItemContainer(Player owner, int capacity, Item[] items) {
		this(owner, capacity);
		if(capacity < items.length)
			throw new IllegalArgumentException("items.length cannot be greater than capacity");
		try {
			addAll(items);
		}
		catch (ContainerOverflowException ex) {
			
		}
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
	 * Gets all items currently contained in this container
	 * @return the items as an array
	 */
	public Item[] getAll() {
		return this.items.toArray(new Item[this.items.size()]);
	}
	
	/**
	 * Gets the item at the specified index (slot), or null if the slot is empty
	 * @param index the index of the item to get
	 * @return the item
	 */
	public Item get(int index) {
		return this.items.get(index);
	}
	
	/**
	 * Destroys all items in this container
	 */
	public void empty() {
		while(!this.items.isEmpty())
			remove(this.items.getLast());
	}
	
	/**
	 * Removes the specified item from this container
	 * @param item the item to remove
	 */
	public void remove(Item item) {
		itemRemoved(item);
		this.items.remove(item);
	}
	
	/**
	 * Removes the item at the specified index
	 * @param index the index of the item to remove
	 */
	public void remove(int index) {
		remove(this.items.get(index));
	}
	
	/**
	 * Transfers an item from this container to another container
	 * @param item the item to transfer
	 * @param to the container to transfer to
	 */
	public void transferItem(Item item, ItemContainer to) {
		remove(item);
		to.add(item);
	}
	
	/**
	 * Transfers all items from this container to the specified container
	 * @param to the container to transfer to
	 * @throws ContainerOverflowException if the specified container does not meet the required capacity
	 */
	public void transferAllItems(ItemContainer to) throws ContainerOverflowException {
		Item[] iarr = this.items.toArray(new Item[this.items.size()]);
		empty();
		to.addAll(iarr);
	}
	
	/**
	 * Adds the specified item to this container
	 * @param item the item to add
	 */
	public boolean add(Item item) {
		if(this.items.size() < capacity) {
			this.items.addLast(item);
			itemAdded(item);
			return true;
		}
		else {
			return false;
		}
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
