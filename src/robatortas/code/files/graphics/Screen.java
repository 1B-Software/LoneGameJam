package robatortas.code.files.graphics;

import java.util.Random;

import robatortas.code.files.level.Tile;

public class Screen {

	public int width, height;
	
	private int xOffset, yOffset;
	
	public int[] pixels;
	public int tileSize = 8;
	public int[] tiles = new int[tileSize*tileSize];
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
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
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		
		for(int y = 0; y < tile.sprite.size; y++) {
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.size; x++) {
				int xa = x + xp;
				if(xa < -tile.sprite.size || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				int color = tile.sprite.pixels[x+y*tile.sprite.size];
				if(color != 0xffff00ff) pixels[xa+ya*width] = color;
			}
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		
		for(int y = 0; y < sprite.size; y++) {
			int ya = y + yp;
			int ys = y;
			if(flip == 2 || flip == 3) ys = (sprite.size-1) - y;
			for(int x = 0; x < sprite.size; x++) {
				int xa = x + xp;
				int xs = x;
				if(flip == 1 || flip == 3) xs = (sprite.size-1) - x;
				if(xa < -sprite.size || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				int color = sprite.pixels[xs+ys*sprite.size];
				if(color != 0xffff00ff) pixels[xa+ya*width] = color;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}