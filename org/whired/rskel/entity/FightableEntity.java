package org.whired.rskel.entity;

import org.whired.rskel.entity.event.FightableEntityEventListener;
import org.whired.rskel.geom.Point3D;
import org.whired.rskel.world.World;

/**
 * A fightable entity
 * @author Whired
 */
public abstract class FightableEntity extends Entity implements FightableEntityEventListener {
	private int maxHealth;
	private int health;
	
	/**
	 * Creates a new fightable entity
	 * @param name the name of the entity
	 * @param world the world of the entity
	 * @param location the location of the entity
	 * @param maxHealth the health of the entity
	 */
	public FightableEntity(String name, World world, Point3D location, int maxHealth) {
		super(name, world, location);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}
	
	/**
	 * Sets a new max health for this entity
	 * @param maxHealth the new max health
	 */
	public void setMaxHealth(int maxHealth) {
		entityMaxHealthChanged(this.maxHealth, maxHealth);
		this.maxHealth = maxHealth;
	}
	
	/**
	 * Sets this entity's current health to the specified amount
	 * @param health the amount to set as this entity's current health
	 */
	public void setHealth(int health) {
		health = health > this.maxHealth ? this.maxHealth : health < 0 ? 0 : health;
		if(health == this.health)
			return;
		entityHealthChanged(this.health, health);
		if(this.health <= 0)
			entityDied();
	}
	
	/**
	 * Reduces this entity's health by the specified amount
	 * @param amount the amount of health to reduce
	 */
	public void reduceHealth(int amount) {
		setHealth(this.health - amount);
	}
	
	/**
	 * Increases this entity's health by the specified amount
	 * @param amount the amount of health to increase
	 */
	public void increaseHealth(int amount) {
		setHealth(this.health + amount);
	}
}