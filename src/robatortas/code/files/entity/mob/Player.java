package robatortas.code.files.entity.mob;

import robatortas.code.files.InputManager;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;

public class Player extends Mob {

	public InputManager input;
	
	public Player(int x, int y, InputManager input) {
		this.input = input;
		this.x = x;
		this.y = y;
	}
	
	protected int xa = 0, ya = 0;
	
	protected int xv = 1, yv = 1;
	
	public void tick() {
		super.tick();
		
		controls();
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else walking = false;
	}
	
	public void controls() {
		if(input.w) {
			y-=ya;
		}
		if(input.s) {
			y+=ya;
		}
		if(input.a) {
			x-=xa;
		}
		if(input.d) {
			x+=xa;
		}
	}
	
	public void render(Screen screen) {
		screen.renderSprite(x, y, Sprite.player);
	}

	public void die() {
		super.die();
	}
}