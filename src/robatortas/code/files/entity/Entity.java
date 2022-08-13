package robatortas.code.files.entity;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.level.Level;

public class Entity {
	
	public double x, y;
	protected Level level;
	protected Sprite sprite;
	
	public boolean removed;
	
	public Entity(int x, int y, Sprite sprite) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	public Entity() {
		
	}
	
	public void tick() {
		
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void remove() {
		
	}
	
	public void move(double xa, double ya) {
		x+=xa;
		y+=ya;
	}
	
	public void render(Screen screen) {
		
	}
	
	public void init(Level level) {
		this.level = level;
	}
}