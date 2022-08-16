package robatortas.code.files.level.tile;

import robatortas.code.files.entity.mob.Mob;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.level.Level;
import robatortas.code.files.level.Tile;

public class SpikeBloodTile extends Tile {

	public SpikeBloodTile(Sprite sprite, int id) {
		super(sprite, id);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderSprite(x << 3, y << 3, Sprite.backStone, 0);
		screen.renderTile(x << 3, y << 3, this);
	}
	
	public boolean solid(Level level, int x, int y, Mob mob) {
		return true;
	}
}
