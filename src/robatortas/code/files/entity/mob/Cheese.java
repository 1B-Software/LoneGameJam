package robatortas.code.files.entity.mob;

import java.util.List;

import robatortas.code.files.Game;
import robatortas.code.files.entity.Blood;
import robatortas.code.files.entity.Entity;
import robatortas.code.files.entity.Gore;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.graphics.SpriteSheet;
import robatortas.code.files.level.Tile;
import robatortas.code.files.sound.Sound;

public class Cheese extends Mob {
	
	public double x, y;
	
	public Cheese(int x, int y) {
		super.x = x;
		super.y = y;
		
		this.x = super.x;
		this.y = super.y;
		
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
		
		List<Entity> entities = level.getEntity(super.x, super.y, super.x-12, super.y-3);
		for(int i = 0; i < entities.size(); i++) {
			e = entities.get(i);
			if(e instanceof Player && e != this) {
				Sound.impact.setVolume(-8);
				Sound.impact.play();
				e.health--;
				die();
			}
		}
		
		if(collision(xa, ya)) {
			Sound.wallHit.setVolume(-8);
			Sound.wallHit.play();
			die();
		}
	}
	
	public void render(Screen screen) {
		sprite = cheese;
		screen.renderMob(super.x, super.y, this, 0);
	}
	
	private Gore gore;
	public void die() {
		for(int i = 0; i < 5; i++) {
			level.add(gore = new Gore(super.x, super.y, null));
			gore.setSize(1);
			gore.setColor(0xffFFDD00);
		}
		for(int i = 0; i < 10; i++) {
			level.add(gore = new Gore(super.x, super.y, null));
			gore.setSize(1);
			gore.setColor(0xffff0000);
		}
		super.die();
	}
	
	public static Sprite cheese = new Sprite(8, 1, 9, SpriteSheet.mainSheet);
}