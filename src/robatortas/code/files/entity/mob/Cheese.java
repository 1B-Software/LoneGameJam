package robatortas.code.files.entity.mob;

import java.util.List;

import robatortas.code.files.entity.Blood;
import robatortas.code.files.entity.Entity;
import robatortas.code.files.entity.Gore;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.graphics.SpriteSheet;
import robatortas.code.files.sound.Sound;

public class Cheese extends Mob {
	
	public Cheese(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = cheese;
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
		ya = yv;
		
		if(xa!=0||ya!=0) {
			move(xa,ya);
		}
		
		Entity e = null;
		
		List<Entity> entities = level.getEntity(x, y, x-12, y-3);
		for(int i = 0; i < entities.size(); i++) {
			e = entities.get(i);
			if(e instanceof Player && e != this) {
				die();
			}
		}
		
		if(!onAir || collision(xa, ya)) {
//			die();
		}
	}
	
	public void render(Screen screen) {
		sprite = cheese;
		screen.renderMob(x, y, this, 0);
	}
	
	public void die() {
		Sound.impact.setVolume(-8);
		Sound.impact.play();
		super.die();
	}
	
	public static Sprite cheese = new Sprite(8, 1, 9, SpriteSheet.mainSheet);
}