package org.whired.rskel.player;

import org.whired.rskel.entity.FightableEntity;
import org.whired.rskel.item.Item;
import org.whired.rskel.world.World;
import org.whired.rskel.geom.Point3D;
import org.whired.rskel.item.ItemContainer;

/**
 * A player
 * @author Whired
 */
public abstract class Player extends FightableEntity {
	private ItemContainer bank;
	private ItemContainer inventory;
	private ItemContainer beastOfBurden;
	private EquipmentContainer equipment = new EquipmentContainer(this) {

		@Override
		public void itemDropped(Item item) {
		}
		
	};

	/**
	 * Creates a player with the given name
	 * @param world the world this player is in
	 * @param name the name of this player
	 */
	public Player(String name, World world, Point3D location, int maxHealth) {
		super(name, world, location, maxHealth);
	}

	/**
	 * Gets this player's inventory
	 * @return the inventory for this player
	 */
	public ItemContainer getInventory() {
		return this.inventory;
	}
	
	/**
	 * Sets this player's inventory
	 * @param inventory the inventory to set
	 */
	public void setInventory(ItemContainer inventory) {
		this.inventory = inventory;
	}

	/**
	 * Sends a message to this player
	 * @param message the message to send
	 */
	public abstract void sendMessage(String message);
}
