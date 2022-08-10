package robatortas.code.files.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public int[] pixels;
	public String path;
	public int size;
	
	public static SpriteSheet mainSheet = new SpriteSheet("/mainSheet.png", 128);
	
	public SpriteSheet(String path, int size) {
		this.size = size;
		this.path = path;
		pixels = new int[size*size];
		load();
	}
	
	public void load() {
		try {
			System.out.println("Loading spritesheet at: " + path);
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
			System.out.println("Loaded spritesheet at: " + path);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\nUnable to load spritesheet located at: " + path);
		}
	}
	
}