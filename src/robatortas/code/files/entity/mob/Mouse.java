package robatortas.code.files.entity.mob;

import java.util.List;

import robatortas.code.files.entity.Blood;
import robatortas.code.files.entity.Entity;
import robatortas.code.files.entity.Gore;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.graphics.SpriteSheet;

public class Mouse extends Mob {
	
	public Mouse(int x, int y) {
		super.x = x;
		super.y = y;
	}
	
	public double xa, ya;
	public double xv, yv;
	
	private Cheese cheese;
	
	public void tick(){
		super.tick();
		
		if(animate < 10000) animate++; 
		else animate = 0;
		
		// JUMP!
		if(tickTime % (random.nextInt(30)+40) == 0) gravity -= 2 + xa * 0.4;
		
		Entity player = null; 
		
		/*
		 * Let me just reiterate through the sin cos and tan :/
		 * 
		 * sen = co/hip
		 * cos = ca/hip
		 * tan = co/ca
		 * 
		 * so to get the triangle angle we need to use tan!!
		 */

		double angle = 0;
		double nx=0, ny=0;
		if(tickTime % 60 == 0) {
		List<Entity> e = level.getEntityFromRadius(this, 130);
		for(int i = 0; i < e.size(); i++) {
			player = e.get(i);
			if(player instanceof Player) {
				level.add(cheese = new Cheese((int)x, (int)y));
				double ex = cheese.x;
				double ey = cheese.y;
				double x = player.x;
				double y = player.y;
				
				// adj
				double dx = x-ex;
				// opp
				double dy = y-ey;
				
				// IN RADIANS
				angle = Math.atan2(dy, dx);
				
				nx = Math.cos(angle);
				ny = Math.sin(angle);
				
//				cheese.die();
			}
		}
	}
		if(cheese!=null) {
			cheese.xv+=nx;
			cheese.yv+=ny;
		}
		
		if(xa!=0||ya!=0) {
			move(xa,ya);
		}
		
		die();
	}
	
	public void render(Screen screen) {
		sprite = mouse;
		
		screen.renderMob((int)x, (int)y, this, 0);
	}
	
	private Gore gore;
	public void die() {
//		super.die();
		if(die) {
			for(int i = 0; i < 25; i++) {
				level.add(gore = new Gore((int)x, (int)y, null));
				gore.setColor(0xff6B6B6B);
				gore.dropBlood = true;
			}
			for(int i = 0; i < 500; i++) level.add(new Blood((int)x, (int)y));
			level.add(gore = new Gore((int)x, (int)y, new Sprite(8, 4, 4, SpriteSheet.mainSheet)));
			gore.dropBlood = false;
			gore.setLife(120*2);
			level.add(gore = new Gore((int)x, (int)y, new Sprite(8, 5, 2, SpriteSheet.mainSheet)));
			gore.dropBlood = true;
			gore.setLife(120*2);
			this.remove();
		}
	}
	
	public static Sprite mouse = new Sprite(16, 7, 0, SpriteSheet.mainSheet);
}