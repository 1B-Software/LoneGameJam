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
		
		collision(xa, ya);
		for(int x = 0; x < Math.abs(xa); x++) {
			if(!collision(fixAbs(xa), 0)) {
				this.x += fixAbs(xa);
			}
		}
	}
	
	private int fixAbs(int value) {
		if(value < 0) return -1;
		return 1;
	}
	
	int tickTime = 0;
	public void tick() {
		tickTime++;
//		gravity();

		collision(0, ya);
//		for(int y = 0; y < Math.abs(ya); y++) {
			if(!collision(0, fixAbs(ya))) {
				this.y += fixAbs(ya);
				move(0, 1);
//			}
		}
	}
	
	public void gravity() {
		if(tickTime % 2 == 0) {
			y+=ya++;
			move(0, ya);
		}
	}
	
	public void render(Screen screen) {
		
	}
	
	public void die() {
		if(health <= 0) {
			remove();
		}
	}
	
	//collision for tiles
	public boolean collision(int xa, int ya) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 6 + 5) >> 3;
			int yt = ((y + ya) + c / 2 * 8 + 6) >> 3;
			if(level.getTile(xt, yt).solid(level, xt, yt, this)) {
				solid = true;
			}
		}
		return solid;
	}
	
}