package robatortas.code.files.entity.mob;

import robatortas.code.files.InputManager;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;
import robatortas.code.files.graphics.SpriteSheet;

public class Player extends Mob {

	public InputManager input;
	
	public Player(int x, int y, InputManager input) {
		this.input = input;
		this.x = x;
		this.y = y;
	}
	
	protected int xa, ya;
	
	protected int xv = 1, yv = 1;
	
	private int tickTime = 0;
	
	private int animate = 0;
	
	public void tick() {
		super.tick();
		
		tickTime++;
		
		if(animate < 10000) animate++; 
		else animate = 0;
		
		xa = 0;
		ya = 0;
		
		controls();
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else walking = false;
	}
	
	public void controls() {
		if(input.w) ya-=yv;
		if(input.s) ya+=yv;
		if(input.a) xa-=xv;
		if(input.d) xa+=xv;
	}
	
	public void render(Screen screen) {
		
		if(walking) {
			if(dir == 3) {
				if (animate % 40 > 20 && animate % 40 <= 40) {
					screen.renderSprite(x, y, playerWalk2, 1);
				} else screen.renderSprite(x, y, playerWalk1, 1);
			}
			else {
				if (animate % 40 > 20 && animate % 40 <= 40) {
					screen.renderSprite(x, y, playerWalk2, 0);
				} else screen.renderSprite(x, y, playerWalk1, 0);
			}
		} else {
			screen.renderSprite(x, y, Sprite.player, 0);
		}
	}

	public void die() {
		super.die();
	}
	
	public static Sprite playerWalk1 = new Sprite(16, 1, 0, SpriteSheet.mainSheet);
	public static Sprite playerWalk2 = new Sprite(16, 2, 0, SpriteSheet.mainSheet);
}