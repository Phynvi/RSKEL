package org.whired.rskel;

import org.whired.rskel.item.WorldItem;
import org.whired.rskel.world.World;
import org.whired.rskel.geom.Point3D;
import org.whired.rskel.item.Item;
import org.whired.rskel.item.ItemContainer;
import org.whired.rskel.item.event.ItemListEventListener;
import org.whired.rskel.entity.Player;
import org.whired.rskel.player.PlayerList;
import org.whired.rskel.player.event.PlayerListEventListener;

/**
 *
 * @author Whired
 */
public class Rskel {

	public static void main(String[] args) {
		// Testing
		final World testWorld = new World();
		testWorld.getItems().addListener(new ItemListEventListener<WorldItem>(){

			@Override
			public void itemAdded(WorldItem item) {
				System.out.println("Debug: "+item+" added to "+testWorld);
			}

			@Override
			public void itemRemoved(WorldItem item) {
				System.out.println("Debug: "+item+" removed from "+testWorld);
			}
		});
		PlayerListEventListener testListener = new PlayerListEventListener() {

			@Override
			public void playerAdded(PlayerList list, Player player) {
				System.out.println("Debug: " + player + " has logged in");
			}

			@Override
			public void playerRemoved(PlayerList list, Player player) {
				System.out.println("Debug: "+ player + " has logged out");
			}
			
			
			
		};
		testWorld.getPlayers().addListener(testListener);
		Player testPlayer = new Player("Whired", testWorld, new Point3D(200, 300, 0), 99){
			@Override
			public void sendMessage(String message) {
				System.out.println("To " + this.getName() + ": "+message);
			}

			@Override
			public void entityMoved(Point3D from, Point3D to) {
				System.out.println("Debug: "+ this.getName() + " moved from "+from+" to "+to);
			}

			@Override
			public void entityHealthChanged(int oldHealth, int newHealth) {
				System.out.println("Debug: "+ this.getName() + " Health update: "+newHealth+" from "+oldHealth);
			}

			@Override
			public void entityMaxHealthChanged(int oldMaxHealth, int newMaxHealth) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void entityDied() {
				System.out.println(this.getName() + " has died.");
			}
		};
		testWorld.getPlayers().add(testPlayer);
		final ItemContainer inventory = new ItemContainer(testPlayer, 1) {

			@Override
			public void itemDropped(Item item) {
				this.getOwner().getWorld().createMessage(this.getOwner().getName() + " has dropped an item at "+this.getOwner().getLocation());
			}
		};
		inventory.addListener(new ItemListEventListener(){

			@Override
			public void itemAdded(Item item) {
				System.out.println(inventory.getOwner() + " got "+item);
			}

			@Override
			public void itemRemoved(Item item) {
				System.out.println(inventory.getOwner() + " lost "+item);
			}
		});
		inventory.add(new Item("Crystal of entry", 7));
		testPlayer.setInventory(inventory);
		
		testPlayer.setLocation(200, 300, 0);
		testPlayer.increaseHealth(100);
		testWorld.createMessage("Hello to all!");
		System.out.println("Debug: "+testPlayer.getName() + " has an item: "+testPlayer.getInventory().get(0));
		Server testServer = new Server(new World[]{testWorld}) {

			@Override
			public void run() {
				System.out.println("Server is running on "+super.getPort());
			}
		};
		testPlayer.getInventory().empty();
		testServer.run();
	}
}
