package org.whired.rskel.item;

/**
 * An item
 * @author Whired
 */
public class Item {
	private final String name;
	private final int quantity;
	
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
}
