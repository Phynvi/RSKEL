package org.whired.rskel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.whired.forgequest.client.render.RegionRenderer;
import org.whired.forgequest.editor.MapStore;
import org.whired.forgequest.map.BasicTile;
import org.whired.forgequest.map.Region;
import org.whired.forgequest.map.Texture;
import org.whired.forgequest.map.TextureDefinitions;
import org.whired.rskel.geom.Point3D;
import org.whired.rskel.item.Item;
import org.whired.rskel.item.ItemContainer;
import org.whired.rskel.item.event.ItemListEventListener;
import org.whired.rskel.player.Player;
import org.whired.rskel.player.PlayerList;
import org.whired.rskel.player.event.PlayerListEventListener;
import org.whired.rskel.world.World;
import org.whired.rskel.world.WorldItem;

/**
 * @author Whired
 */
public class Rskel {

	public static void main(String[] args) {
		// Testing
		try {
			TextureDefinitions.register(Texture.load(Rskel.class.getResource("world/BIGTILE.png").getPath(), (byte) 0));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// TexturedTile[] tiles = new TexturedTile[25];
		// for (int i = 0; i < tiles.length; i++) {
		// tiles[i] = new TexturedTile(TextureDefinitions.forId((short) 0));
		// }
		// Region[] regs = new Region[5];
		// for (int i = 0; i < regs.length; i++) {
		// regs[i] = new Region((short) 0, (short) 0, (byte) 5, (byte) 5, (byte) 0, tiles);
		// }
		//
		// try {
		// MapStore.saveMap("testMap.dat", regs);
		// }
		// catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		try {
			final Region[] regs = MapStore.loadMap("testMap.dat");
			System.out.println(regs.length);

			final World testWorld = new World(new BasicTile[0][0][0]);
			testWorld.getItems().addListener(new ItemListEventListener<WorldItem>() {

				@Override
				public void itemAdded(WorldItem item) {
					System.out.println("Debug: " + item + " added to " + testWorld);
				}

				@Override
				public void itemRemoved(WorldItem item) {
					System.out.println("Debug: " + item + " removed from " + testWorld);
				}
			});
			PlayerListEventListener testListener = new PlayerListEventListener() {

				@Override
				public void playerAdded(PlayerList list, Player player) {
					System.out.println("Debug: " + player + " has logged in");
				}

				@Override
				public void playerRemoved(PlayerList list, Player player) {
					System.out.println("Debug: " + player + " has logged out");
				}

			};
			testWorld.getPlayers().addListener(testListener);
			Player testPlayer = new Player("Whired", testWorld, new Point3D(200, 300, 0), 99) {
				@Override
				public void sendMessage(String message) {
					System.out.println("To " + this.getName() + ": " + message);
				}

				@Override
				public void entityMoved(Point3D from, Point3D to) {
					System.out.println("Debug: " + this.getName() + " moved from " + from + " to " + to);
				}

				@Override
				public void entityHealthChanged(int oldHealth, int newHealth) {
					System.out.println("Debug: " + this.getName() + " Health update: " + newHealth + " from " + oldHealth);
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
					this.getOwner().getWorld().createMessage(this.getOwner().getName() + " has dropped an item at " + this.getOwner().getLocation());
				}
			};
			inventory.addListener(new ItemListEventListener() {

				@Override
				public void itemAdded(Item item) {
					System.out.println(inventory.getOwner() + " got " + item);
				}

				@Override
				public void itemRemoved(Item item) {
					System.out.println(inventory.getOwner() + " lost " + item);
				}
			});
			inventory.add(new Item("Crystal of entry", 7));
			testPlayer.setInventory(inventory);

			testPlayer.setLocation(200, 300, 0);
			testPlayer.increaseHealth(100);
			testWorld.createMessage("Hello to all!");
			System.out.println("Debug: " + testPlayer.getName() + " has an item: " + testPlayer.getInventory().get(0));
			Server testServer = new Server(new World[] { testWorld }) {

				@Override
				public void run() {
					System.out.println("Server is running on " + super.getPort());
				}
			};
			testPlayer.getInventory().empty();
			testServer.run();
			JFrame frame = new JFrame("FORGEQUEST WORLD");
			JPanel pan = new JPanel() {
				@Override
				public void paint(Graphics g) {
					// WorldRenderer.renderTiles(g, testWorld.tiles);
					RegionRenderer.renderRegion(g, regs[0]);
				}
			};
			pan.setPreferredSize(new Dimension(400, 400));
			frame.getContentPane().add(pan);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.pack();
			frame.setVisible(true);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
