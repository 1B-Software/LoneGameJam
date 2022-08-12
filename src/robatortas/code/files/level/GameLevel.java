package robatortas.code.files.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import robatortas.code.files.InputManager;
import robatortas.code.files.entity.mob.Player;

public class GameLevel extends Level{
	
	public GameLevel(String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(GameLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		add(player = new Player(3 << 3, 2 << 3 , input = new InputManager()));
	}
}