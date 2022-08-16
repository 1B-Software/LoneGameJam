package robatortas.code.files.entity.mob;

import java.util.List;

import robatortas.code.files.entity.Blood;
import robatortas.code.files.entity.Entity;
import robatortas.code.files.entity.Gore;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.graphics.SpriteSheet;
import robatortas.code.files.sound.Sound;

public class Cat extends Mob {
	
	public Cat(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = cat;
	}
	
	public double xa, ya;
	public double xv, yv;
	
	int tickTime = 0;
	
	public void tick() {
		super.tick();
		
		tickTime++;
		
		if(animate < 10000) animate++; 
		else animate = 0;
		
		xa = xv;
		ya += yv;
		
		if(xa!=0||ya!=0) {
			move(xa,ya);
		}
		
		Entity e = null;
		
		List<Entity> entities = level.getEntity(x+4, y-8, x+4, y-3);
		for(int i = 0; i < entities.size(); i++) {
			e = entities.get(i);
			if(!(e instanceof Player) && e != this) {
				e.die = true;
			}
		}
		
		if(!onAir || collision(xa, 0) && e != null) {
			die();
		}
	}
	
	public void render(Screen screen) {
		sprite = cat;
		screen.renderMob(x, y, this, 0);
	}
	
	private Gore gore;
	public void die() {
		Sound.impact.setVolume(-8);
		Sound.impact.play();
		for(int i = 0; i < 25; i++) {
			level.add(gore = new Gore(x, y, new Sprite(8, 0, 9, SpriteSheet.mainSheet)));
			gore.dropBlood = true;
		}
		for(int i = 0; i < 500; i++) level.add(new Blood(x, y));
		level.add(gore = new Gore(x, y, new Sprite(8, 4, 2, SpriteSheet.mainSheet)));
		gore.dropBlood = false;
		gore.setLife(120*2);
		level.add(gore = new Gore(x, y, new Sprite(8, 5, 2, SpriteSheet.mainSheet)));
		gore.dropBlood = true;
		gore.setLife(120*2);
		this.remove();
	}
	
	public static Sprite cat = new Sprite(16, 4, 0, SpriteSheet.mainSheet);
}