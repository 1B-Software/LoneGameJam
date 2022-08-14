package robatortas.code.files.entity.mob;

import robatortas.code.files.entity.Blood;
import robatortas.code.files.entity.Gore;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.graphics.SpriteSheet;
import robatortas.code.files.level.Tile;

public class Cat extends Mob {
	
	public Cat(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = cat;
		shoot();
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
		
		die();
	}
	
	public void shoot() {
		
	}
	
	public void render(Screen screen) {
		int x = (int)this.x;
		int y = (int)this.y;
		
		sprite = cat;
		screen.renderMob(x, y, this, 0);
	}
	
	private Gore gore;
	private Blood blood;
	public void die() {
		if(!onAir) {
			for(int i = 0; i < 10; i++) {
				level.add(gore = new Gore(x, y, new Sprite(8, 0, 9, SpriteSheet.mainSheet)));
				gore.dropBlood = true;
			}
			for(int i = 0; i < 10; i++) level.add(blood = new Blood(x, y));
			level.add(gore = new Gore(x, y, new Sprite(8, 4, 2, SpriteSheet.mainSheet)));
			gore.dropBlood = false;
			gore.setLife(120*2);
			level.add(gore = new Gore(x, y, new Sprite(8, 5, 2, SpriteSheet.mainSheet)));
			gore.dropBlood = true;
			gore.setLife(120*2);
			this.remove();
		}
	}
	
	public static Sprite cat = new Sprite(16, 4, 0, SpriteSheet.mainSheet);
}