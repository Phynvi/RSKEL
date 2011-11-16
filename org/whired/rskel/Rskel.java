package org.whired.rskel;

import java.awt.Point;
import org.whired.rskel.item.Item;
import org.whired.rskel.item.ItemContainer;
import org.whired.rskel.player.Player;
import org.whired.rskel.player.PlayerList;
import org.whired.rskel.player.event.PlayerListEventListener;

/**
 *
 * @author Whired
 */
public class Rskel {

	public static void main(String[] args) {
		// Testing
		World testWorld = new World();
		PlayerListEventListener testListener = new PlayerListEventListener() {

			@Override
			public void playerAdded(PlayerList list, Player player) {
				System.out.println(player + " was added to " + list);
			}

			@Override
			public void playerRemoved(PlayerList list, Player player) {
				player.getWorld().createMessage(player + " has logged out.");
			}
			
			
			
		};
		testWorld.getPlayers().addListener(testListener);
		Player testPlayer = new Player("Whired"){
			@Override
			public void sendMessage(String message) {
				System.out.println(this.getName() + ": "+message);
			}

			@Override
			public void playerMoved(Point oldLocation, Point newLocation) {
				this.getWorld().createMessage(this.getName() + " moved from "+oldLocation+" to "+newLocation);
			}
		};
		testWorld.getPlayers().addPlayer(testPlayer);
		
		Player testPlayer2 = new Player("Admin"){
			@Override
			public void sendMessage(String message) {
				System.out.println(this.getName() + ": "+message);
			}

			@Override
			public void playerMoved(Point oldLocation, Point newLocation) {
				this.getWorld().createMessage(this.getName() + " moved from "+oldLocation+" to "+newLocation);
			}
		};testWorld.getPlayers().addPlayer(testPlayer2);
		ItemContainer inventory = new ItemContainer(testPlayer, 1, new Item[] { new Item("Crystal of Entry", 7)}) {

			@Override
			public void itemAdded(Item item) {
				this.getOwner().getWorld().createMessage(this.getOwner().getName()+" obtained a "+item.toString());
			}

			@Override
			public void itemRemoved(Item item) {
				this.getOwner().getWorld().createMessage(this.getOwner().getName()+" lost a "+item.toString());
			}
		};
		testPlayer.setInventory(inventory);
		
		testPlayer.setLocation(200, 300);
		testPlayer2.setLocation(testPlayer.getLocation());
		testWorld.createMessage("Hello to all!");
		testWorld.createMessage(testPlayer.getName() + " has an item: "+testPlayer.getInventory().get(0));
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
