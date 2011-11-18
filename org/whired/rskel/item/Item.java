package org.whired.rskel.item;

/**
 * An item
 * @author Whired
 */
public class Item {
	private final String name;
	private final int quantity;
	private ItemContainer container; // TODO: use of this might be unecessary
	
	/**
	 * Creates a new item with the specified name
	 * @param name the name of the item
	 */
	public Item(String name) {
		this.name = name;
		this.quantity = 1;
	}
	
	/**
	 * Creates a new item with the specified name and quantity
	 * @param name the name of the item
	 * @param quantity the quantity of the item
	 */
	public Item(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return this.name + " x" + this.quantity;
	}
	
	/**
	 * Sets the container for this item
	 * @param container the container to set
	 */
	protected void setContainer(ItemContainer container) {
		this.container = container;
	}
	
	/**
	 * Gets the name of this item
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
}
