package robatortas.code.files.entity.mob;

import robatortas.code.files.entity.Entity;
import robatortas.code.files.graphics.Screen;

public class Mob extends Entity {
	
	protected int health = 10;
	protected int attackTime = 10;
	
	protected int dir = 0;
	
	public boolean walking = false;
	
	public double gravity = 0;
	
	protected int animate = 0;
	
	protected double xa, ya;
	public void move(double xa, double ya) {
		this.xa = xa;
		this.ya = ya;
		
		if(xa!=0&&ya!=0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa>0)dir=1;
		if(xa<0)dir=3;
//		
//		if(!collision(xa, 0)) {
//			this.x += xa;
//		}
		for (int x = 0; x < Math.abs(xa); x++) {
		if (!collision(abs(xa), ya)) {
			this.x += abs(xa);
		}
		}

		for (int y = 0; y < Math.abs(ya); y++) {
			if (!collision(xa, abs(ya))) {
				this.y += abs(ya);
			}
		}
	}
	
	private int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}
	
	int tickTime = 0;
	public void tick() {
		tickTime++;
		
		gravity();
	}
	
	protected boolean onAir = false;
	
	public void gravity() {
		if(!collision(0, gravity)) {
			super.move(0, gravity);
			onAir = true;
		} else onAir = false;
		
		if(onAir) {
			gravity+=0.3;
			if(gravity >= 7) gravity = 7;
		} else gravity = 0.5;
	}
	
	public void render(Screen screen) {
		
	}
	
	public void die() {
		if(health <= 0) {
			remove();
		}
	}
	
	//collision for tiles
	public boolean collision(double xa, double ya) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			int xt = (((int)x + (int)xa) + (c % 2 + 1) * 5) / 8;
			double yt = ((y + ya) + (c / 2 + 2) * 2) / 8;
			int iy = (int) Math.ceil(yt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(xt, iy).solid(level, xt, iy, this)) solid = true;
		}
		return solid;
	}
	
}