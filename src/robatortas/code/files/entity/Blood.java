package robatortas.code.files.entity;

import java.util.Random;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.graphics.SpriteSheet;
import robatortas.code.files.physics.PhysicsEngine;

public class Blood extends Entity{
	
	public PhysicsEngine physics;
	
	public Blood(int x, int y) {
		this.x = x;
		this.y = y;
		physics = new PhysicsEngine(x, y);
	}
	
	private Random random = new Random();
	
	int tickTime = 0;
	int life = random.nextInt(60*45)-30*50;
	
	public void tick() {
		tickTime++;
		
		if(tickTime >= life) remove();
		
		physics.calculations.physics();
		move((int) physics.calculations.x0 - x, physics.calculations.y0 - y);
	}
	
	public void render(Screen screen) {
		screen.renderPixel(x, (y + 13) - (int) physics.calculations.z0, 2, 2, 0xffff0000);
	}
	
	public static Sprite goreSprite = new Sprite(8, 0, 9, SpriteSheet.mainSheet);
}