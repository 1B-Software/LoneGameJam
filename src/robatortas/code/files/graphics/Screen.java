package robatortas.code.files.graphics;

import java.util.Random;

public class Screen {

	public static int width, height;
	
	private int xOffset, yOffset;
	
	public int[] pixels;
	public int tileSize = 8;
	public int[] tiles = new int[tileSize*tileSize];
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		Screen.width = width;
		Screen.height = height;
		
		pixels = new int[width*height];
		map();
	}
	
	public void clear() {
		// I know that instead of w*h I could've just used pixels.lenghts but I don't give a shit!
		for(int i = 0; i < width*height; i++) {
			pixels[i] = 0;
		}
	}
	
	public void map() {
		for(int i = 0; i < tileSize*tileSize; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void renderPixel(int xp, int yp) {
		for(int y = 0; y < height; y++) {
			int ya = y + yp;
			if(ya < 0 || ya >= height) continue;
			for(int x = 0; x < width; x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width) continue;
				int color = ((xa >> 4) & tileSize-1) + ((ya >> 4) & tileSize-1) * tileSize;
				pixels[x+y*width] = tiles[color];
			}
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		
		for(int y = 0; y < sprite.size; y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.size; x++) {
				int xa = x + xp;
				if(xa < -sprite.size || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				int color = sprite.pixels[x+y*sprite.size];
				if(color != 0xffff00ff) pixels[xa+ya*width] = color;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}