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
	
	public void tick() {
		super.tick();
		
		
	}
	
	public void controls() {
		if(input.w) {
			System.out.println("w");
		}
		if(input.s) {
			System.out.println("s");
		}
		if(input.a) {
			System.out.println("a");
		}
		if(input.d) {
			System.out.println("d");
		}
	}
	
	public void render(Screen screen) {
		screen.renderSprite(x, y, Sprite.player);
	}

	public void die() {
		super.die();
	}
}