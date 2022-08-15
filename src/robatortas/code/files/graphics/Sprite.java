package robatortas.code.files.graphics;

public class Sprite {

	public int[] pixels;
	public int x, y;
	public int size;
	public SpriteSheet sheet;
	
	private int color;
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.sheet = sheet;
		this.size = size;
		this.x = x*size;
		this.y = y*size;
		
		pixels = new int[size*size];
		load();
	}
	
	public Sprite(int size, int color) {
		this.size = size;
		this.color = color;
		
		pixels = new int[size*size];
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	public static Sprite player = new Sprite(16, 0, 0, SpriteSheet.mainSheet);
	public static Sprite stone = new Sprite(8, 0, 8, SpriteSheet.mainSheet);
	public static Sprite stoneBlood = new Sprite(8, 2, 8, SpriteSheet.mainSheet);
	public static Sprite stoneBlood2 = new Sprite(8, 3, 8, SpriteSheet.mainSheet);
	public static Sprite wood = new Sprite(8, 1, 8, SpriteSheet.mainSheet);
	public static Sprite spike = new Sprite(8, 2, 8, SpriteSheet.mainSheet);
	public static Sprite spikeBlood = new Sprite(8, 3, 8, SpriteSheet.mainSheet);
	
	public static int col_stone = 0xff808080;
	public static int col_spike = 0xffffffff;
	public static int col_spikeBlood = 0xffFFAAAA;
	public static int col_wood = 0xff7F4F2F;
	
	public void load() {
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				pixels[x+y*size] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.size];
			}
		}
	}
}