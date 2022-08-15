package robatortas.code.files.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import robatortas.code.files.InputManager;
import robatortas.code.files.entity.Entity;
import robatortas.code.files.entity.mob.Cat;
import robatortas.code.files.entity.mob.Mouse;
import robatortas.code.files.entity.mob.Player;

public class GameLevel extends Level{
	
	public GameLevel(String path) {
		super(path);
	}
	
	@SuppressWarnings("unchecked")
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
		
		entitiesInTiles = new ArrayList[width*height];
		for(int i = 0; i < width*height; i++) {
			entitiesInTiles[i] = new ArrayList<Entity>();
		}
		
		add(player = new Player(3 << 3, 4 << 3 , input = new InputManager()));
		add(new Mouse(6<<3, 0<<3));
	}
}