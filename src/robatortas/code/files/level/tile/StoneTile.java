package robatortas.code.files.level.tile;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.level.Tile;

public class StoneTile extends Tile {
	
	public StoneTile(Sprite sprite, int id) {
		super(sprite, id);
	}
	
	private int color, diff = 10;
	private int r, g, b;
	
	public void render(int x, int y, Screen screen) {
		
		for(int yy = 0; yy < sprite.size; yy++) {
			for(int xx = 0; xx < sprite.size; xx++) {
				
				for(int i = 0; i < sprite.pixels.length; i++) {
					r = (sprite.pixels[i] & 0xff0000) >> 16;
					g = (sprite.pixels[i] & 0xff00) >> 8;
					b = sprite.pixels[i] & 0xff;
					
					color = r << 16 | g << 8 | b;
					
//					if(color == 808080) color - shade;
					
					sprite.pixels[i] = color;
				}
				
			}
		}
		screen.renderTile(x << 3, y << 3, this);
	}
}