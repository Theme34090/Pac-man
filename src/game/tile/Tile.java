package game.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	// Static stuff
	
	public static Tile[] tiles = new Tile[256];	// can access all tiles instances through Tile class
	public static Tile grassTile = new GrassTile(0);	// set id of grassTile = 0
	public static Tile lavaTile = new LavaTile(1);
	
	// Class stuff
	
	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
	
	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		// add each tile to array
		tiles[id] = this;
	}

	public void update() {

	}
	
	// Check if creatures able to walk over tile
	public boolean isSolid() {
		return true;
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	public int getId() {
		return id;
	}
}
