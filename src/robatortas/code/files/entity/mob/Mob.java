package robatortas.code.files.entity.mob;

import java.util.Random;

import robatortas.code.files.entity.Entity;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.level.Tile;

public class Mob extends Entity {
	
	protected int attackTime = 10;
	
	protected int dir = 0;
	
	public boolean walking = false;
	
	public double gravity = 0;
	
	protected int animate = 0;
	
	protected Random random = new Random();
	
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
		
		for (int y = 0; y < Math.abs(ya); y++) {
			if (!collision(0, abs(ya))) {
				this.y += abs(ya);
			}
		}
		
		for (int x = 0; x < Math.abs(xa); x++) {
			if (!collision(abs(xa), 0)) {
				this.x += abs(xa);
			}
		}
	}
	
	protected int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}
	
	int tickTime = 0;
	public void tick() {
		tickTime++;
		
		if(level.getTile((int)x+6 >> 3, (int)y+16 >> 3) == Tile.spikeTile) {
			die();
			level.insertTile((int)x+6 >> 3, (int)y+16 >> 3, Sprite.col_spikeBlood);
		}
		
		if(!(this instanceof Cheese)) gravity();
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
		remove();
	}
	
	//collision for tiles
	public boolean collision(double xa, double ya) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			int xt = (((int)x + (int)xa) + (c % 2 + 1) * 5) >> 3; //int xt = (((int)x + (int)xa) + (c % 2 + 1) * 5) >> 3;
			double yt = 0;
			if(!(this instanceof Cheese)) yt = ((y + ya) + (c / 2 + 5) * 1) / 8;
			else yt = ((y + ya) + (c / 2 ) * 1) / 8;
			int yy = (int) Math.ceil(yt);
			if (c / 2 == 0) yy = (int) Math.floor(yt);
			if (level.getTile(xt, yy).solid(level, xt, yy, this)) solid = true;
		}
		return solid;
	}
}