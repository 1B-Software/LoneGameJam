package robatortas.code.files.entity.mob;

import robatortas.code.files.entity.Entity;
import robatortas.code.files.graphics.Screen;

public class Mob extends Entity {
	
	protected int health = 10;
	protected int attackTime = 10;
	
	protected int dir = 0;
	
	public boolean walking = false;
	
	protected int xa, ya;
	public void move(int xa, int ya) {
		this.xa = xa;
		this.ya = ya;
		
		if(xa!=0&&ya!=0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa>0)dir=1;
		if(ya>0)dir=2;
		if(xa<0)dir=3;
		if(ya<0)dir=0;
		
		if(!collision()) {
			x+=xa;
			y+=ya;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void die() {
		if(health <= 0) {
			remove();
		}
	}
	
	public boolean collision() {
		return false;
	}
	
}