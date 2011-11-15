package org.whired.rskel;

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
		};
		
		Player testPlayer2 = new Player("Admin"){
			@Override
			public void sendMessage(String message) {
				System.out.println(this.getName() + ": "+message);
			}
		};
		
		testWorld.getPlayers().addPlayer(testPlayer);
		testWorld.getPlayers().addPlayer(testPlayer2);
		testWorld.getPlayers().removePlayer(testPlayer);
		testWorld.createMessage("Hello to all!");
		
	}
}
