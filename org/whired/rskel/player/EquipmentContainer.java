package org.whired.rskel.player;

import org.whired.rskel.item.ItemContainer;

/**
 *
 * @author Whired
 */
public abstract class EquipmentContainer extends ItemContainer {
	public EquipmentContainer(Player player) {
		super(player, 12);
	}
}
