package robatortas.code.files.level;

import robatortas.code.files.graphics.Screen;

public class Level {
	
	public Level() {
		
	}
	
	public void tick() {
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll << 3;
		int y0 = yScroll << 3;
		int x1 = (xScroll + 8) << 3;
		int y1 = (yScroll + 8) << 3;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				screen.renderSprite(x, y, null);
			}
		}
	}
}