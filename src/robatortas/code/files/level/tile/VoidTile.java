package robatortas.code.files.level.tile;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.level.Tile;

public class VoidTile extends Tile {
	
	public VoidTile(Sprite sprite, byte id) {
		super(sprite, id);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderSprite(x << 4, y << 4, new Sprite(8, 0xffff00ff));
	}
	
}