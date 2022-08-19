package robatortas.code.files.graphics;

public class Fonts {
	
	private static int x, y;
	
	private static String path = "/fontsheet.png";
	private static SpriteSheet fontSheet = new SpriteSheet(path, 864, 112);
	
	private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.,!?'\"-+:/\\%()<>";
	
	private static int spacing = 8;
	
	private static Screen screen;
	
	public Fonts(Screen screen) {
		Fonts.screen = screen;
	}
	
	public static void write(String msg, int x, int y, int size, Screen screen) {
		Fonts.x = x;
		Fonts.y = y;
		
 		try {
 			msg.toUpperCase();
 			for(int i = 0; i < msg.length(); i++) {
 				// Gets the index of EVERY char in the message written.
 	 			int c = chars.indexOf(msg.charAt(i));
 	 			if(c >= 0) {
 	 				screen.renderSprite(x + i * spacing, y, new Sprite(16, c, 6, fontSheet), 0);
 	 			}
 			}
 		} catch(Exception e) {
 			System.err.println("UNHANDLED Exception while writing text!");
 		}
	}
}