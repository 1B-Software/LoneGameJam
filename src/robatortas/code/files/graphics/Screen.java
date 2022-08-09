package robatortas.code.files.graphics;

public class Screen {

	public static int width, height;
	
	public int[] pixels;
	public int tileSize = 16;
	public int[] map = new int[tileSize*tileSize];
	
	public Screen(int width, int height) {
		Screen.width = width;
		Screen.height = height;
		
		pixels = new int[width*height];
	}
	
	public void clear() {
		// I know that instead of w*h I could've just used pixels.lenghts but I don't give a shit!
		for(int i = 0; i < width*height; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderPixel(int yp, int xp) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x+y*width] = 0xffff00ff;
			}
		}
	}
	
}