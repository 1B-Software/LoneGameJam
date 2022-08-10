package robatortas.code.files.graphics;

public class Sprite {

	public int[] pixels;
	public int x, y;
	public int size;
	public SpriteSheet sheet;
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.sheet = sheet;
		this.size = size;
		this.x = x*size;
		this.y = y*size;
		
		pixels = new int[size*size];
		load();
	}
	
	public static Sprite player = new Sprite(16, 0, 0, SpriteSheet.mainSheet);
	
	public void load() {
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				pixels[x+y*size] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.size];
			}
		}
	}
}