package robatortas.code.files.entity.mob;

import robatortas.code.files.entity.Blood;
import robatortas.code.files.entity.Gore;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.graphics.SpriteSheet;

public class Mouse extends Mob {
	
	public Mouse(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double xa, ya;
	public double xv, yv;
	
	public void tick(){
		super.tick();
		
		if(animate < 10000) animate++; 
		else animate = 0;
		
		if(xa!=0||ya!=0) {
			move(xa,ya);
		}
	}
	
	public void render(Screen screen) {
		sprite = mouse;
		
		screen.renderMob(x, y, this, 0);
	}
	
	private Gore gore;
	public void die() {
		super.die();
		for(int i = 0; i < 25; i++) {
			level.add(gore = new Gore(x, y, null));
			gore.setColor(0xff6B6B6B);
			gore.dropBlood = true;
		}
		for(int i = 0; i < 500; i++) level.add(new Blood(x, y));
		level.add(gore = new Gore(x, y, new Sprite(8, 4, 3, SpriteSheet.mainSheet)));
		gore.dropBlood = false;
		gore.setLife(120*2);
		level.add(gore = new Gore(x, y, new Sprite(8, 5, 2, SpriteSheet.mainSheet)));
		gore.dropBlood = true;
		gore.setLife(120*2);
	}
	
	public static Sprite mouse = new Sprite(16, 7, 0, SpriteSheet.mainSheet);
}