package robatortas.code.files.entity;

import java.util.Random;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.graphics.SpriteSheet;
import robatortas.code.files.level.Tile;
import robatortas.code.files.physics.PhysicsEngine;

public class Gore extends Entity{
	
	private PhysicsEngine physics;
	
	private Sprite sprite;
	
	public Gore(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		physics = new PhysicsEngine(x, y);
		this.sprite = sprite;
	}
	
	private Random random = new Random();
	
	int tickTime = 0;
	int life = random.nextInt(60*10)-30*10;
	
	private Blood blood;
	
	public void setLife(int value) {
		life = value;
	}
	
	public boolean dropBlood = false;
	
	public int bloodChance;
	
	public void tick() {
		tickTime++;
		if(tickTime > life) remove();
		
		if(dropBlood) {
			if(tickTime % 2 == 0) {
				level.add(blood = new Blood(x+4, y+2));
				blood.physics.calculations.x1 = random.nextGaussian() * 0.02;
				blood.physics.calculations.z1 = 1.0;
				
				bloodChance = random.nextInt(2) - 3;
			}
		}
		
		physics.calculations.physics();
		move((int) physics.calculations.x0 - x, physics.calculations.y0 - y);
	}
	
	public void render(Screen screen) {
		screen.renderSprite(x, (y + 13) - (int) physics.calculations.z0, sprite, 0);
	}
	
	public static Sprite goreSprite = new Sprite(8, 0, 9, SpriteSheet.mainSheet);
}