package robatortas.code.files.entity;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.level.Level;

public class Entity {
	
	public int x, y;
	protected Level level;
	public Sprite sprite;
	
	public int health = 10;
	
	public boolean die = false;
	
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
		this.removed = true;
	}
	
	public void move(double xa, double ya) {
		x+=xa;
		y+=ya;
	}
	
	protected int xp = 6;
	protected int yp = 6;
	public boolean intersects(int x0, int y0, int x1, int y1) {
		return (x + xp < x0 || y + yp < y0 || x - xp > x1 || y - yp > y1);
	}
	
	public void render(Screen screen) {
		
	}
	
	public void init(Level level) {
		this.level = level;
	}
}