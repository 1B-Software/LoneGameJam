package robatortas.code.files.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fonts {
	
	private int x, y;
	
	private String path = "/fontsheet.png";
	private SpriteSheet fontSheet = new SpriteSheet(path, 864, 112);
	
	private String chars = "abcdefghijklmnopqrstuvwxyz.,!?'\"-+:/\\%()<>";
	
	private Screen screen;
	
	public Fonts(Screen screen) {
		this.screen = screen;
	}
	
	public void write(String msg, int x, int y, int size) {
		this.x = x;
		this.y = y;
		
		BufferedImage image = null;
		int[] pixels = null;
		
 		try {
			image = ImageIO.read(Fonts.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			pixels = new int[w*h];
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			System.err.println("\n IOException: Unable to locate font image at: " + path + " location! :(");
		}
 		
 		try {
 			for(int i = 0; i < msg.length(); i++) {
 				// Gets the index of EVERY char in the message written.
 	 			int c = msg.indexOf(i);
 	 			if(c >= 0) {
 	 				screen.renderSprite(x, y, new Sprite(16, i, 6, fontSheet), 0);
 	 			}
 			}
// 			
 		} catch(Exception e) {
 			
 		}
	}
}