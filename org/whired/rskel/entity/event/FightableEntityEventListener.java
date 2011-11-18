package org.whired.rskel.entity.event;

/**
 *
 * @author Whired
 */
public interface FightableEntityEventListener {
	/**
	 * Invoked when the health of an entity changes
	 * @param oldHealth the health the entity was before the change
	 * @param newHealth the health the entity is currently
	 */
	public void entityHealthChanged(int oldHealth, int newHealth);
	
	/**
	 * Invoked when the max health of an entity changes
	 * @param oldMaxHealth the max health the entity was before the change
	 * @param newMaxHealth the max health the entity is currently
	 */
	public void entityMaxHealthChanged(int oldMaxHealth, int newMaxHealth);
	
	/**
	 * Invoked when an entity dies
	 */
	public void entityDied();
}
