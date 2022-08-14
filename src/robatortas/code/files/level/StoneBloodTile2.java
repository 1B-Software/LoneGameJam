package robatortas.code.files.level;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;

public class StoneBloodTile2 extends Tile {

	public StoneBloodTile2(Sprite sprite, int id) {
		super(sprite, id);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 3, y << 3, this);
	}
}