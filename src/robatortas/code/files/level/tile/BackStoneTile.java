package robatortas.code.files.level.tile;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.level.Tile;

public class BackStoneTile extends Tile {
	
	public BackStoneTile(Sprite sprite, int id) {
		super(sprite, id);
	}
	
	public void render(int x, int y, Screen screen) {
		
		for(int yy = 0; yy < sprite.size; yy++) {
			for(int xx = 0; xx < sprite.size; xx++) {
				
				for(int i = 0; i < sprite.pixels.length; i++) {
					int r = sprite.pixels[i] & 0xff0000 >> 16;
					int g = sprite.pixels[i] & 0xff00 >> 8;
					int b = sprite.pixels[i] & 0xff;
					
					int color = r << 16 | g << 8 | b;
					
					sprite.pixels[i] = color+50;
				}
				
			}
		}
		
		screen.renderTile(x << 3, y << 3, this);
	}
}