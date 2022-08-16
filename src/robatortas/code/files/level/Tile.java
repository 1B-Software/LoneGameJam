package robatortas.code.files.level;

import robatortas.code.files.entity.mob.Mob;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.level.tile.BackStoneTile;
import robatortas.code.files.level.tile.SpikeBloodTile;
import robatortas.code.files.level.tile.SpikeTile;
import robatortas.code.files.level.tile.StoneTile;
import robatortas.code.files.level.tile.VoidTile;
import robatortas.code.files.level.tile.WoodTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	public int id;
	
	public Tile[] tiles = new Tile[128];

	public static Tile voidTile = new VoidTile(new Sprite(8, 0xfffff0ff), 0);
	public static Tile stoneTile = new StoneTile(Sprite.stone, 1);
	public static Tile woodTile = new WoodTile(Sprite.wood, 2);
	public static Tile spikeTile = new SpikeTile(Sprite.spike, 3);
	public static Tile spikeBloodTile = new SpikeBloodTile(Sprite.spikeBlood, 4);
	public static Tile backStoneTile = new BackStoneTile(Sprite.backStone, 4);
	
	public Tile(Sprite sprite, int id) {
		this.sprite = sprite;
		this.id = id;
		
		if(tiles[id] != null) {
			System.err.println("Duplicate tile id's!");
		}
	}
	
	public void render(int x, int y, Screen screen) {
		this.x = x;
		this.y = y;
	}
	
	public boolean solid(Level level, int x, int y, Mob mob) {
		return false;
	}
}