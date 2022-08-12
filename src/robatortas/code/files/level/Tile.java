package robatortas.code.files.level;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.level.tile.StoneTile;
import robatortas.code.files.level.tile.VoidTile;

public class Tile {
	
	public int x, y;
	public byte id;
	
	public Tile[] tiles = new Tile[128];
	
	public static Tile stoneTile = new StoneTile(Sprite.stone, (byte) 0);
	public static Tile voidTile = new VoidTile(new Sprite(8, 0xfffff0ff), (byte) 0);
	
	public Tile(Sprite sprite, byte id) {
		this.id = id;
		
		if(tiles[id] != null) {
			System.err.println("Duplicate tile id's!");
		}
		
	}
	
	public void render(int x, int y, Screen screen) {
		this.x = x;
		this.y = y;
	}
}