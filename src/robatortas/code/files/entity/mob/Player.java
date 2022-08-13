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
	
	protected double xa, ya;
	
	protected double xv = 1, yv = 1;
	
	private int tickTime = 0;
	
	private int animate = 0;
	
	private boolean jump = true;
	
	public void tick() {
		super.tick();
		
		tickTime++;
		
		if(animate < 10000) animate++; 
		else animate = 0;
		
		xa = 0;
		ya = 0;
		if(tickTime % 10 == 0) gravity+=tickTime&1;
		controls();
				
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else walking = false;
	}
	
	public void controls() {
		if(input.w) {
			if(!jump) {
				gravity -= 4 + fixAbs(gravity) * 0.5;
				jump = true;
			}
		} else jump = false;
//		if(input.s) gravity+=0.05;
		if(input.a) xa-=xv;
		if(input.d) xa+=xv;
	}
	
	public void render(Screen screen) {
		int x = (int)this.x;
		int y = (int)this.y;
		
		if(walking) {
			if(dir == 3) {
				if (animate % 20 > 10 && animate % 20 <= 20) {
					screen.renderSprite(x, y, playerWalk, 1);
				} else screen.renderSprite(x, y, player, 1);
			}
			else {
				if (animate % 20 > 10 && animate % 20 <= 20) {
					screen.renderSprite(x, y, playerWalk, 0);
				} else screen.renderSprite(x, y, player, 0);
			}
		} else {
			if(dir == 1) screen.renderSprite(x, y, player, 0);
			if(dir == 3) screen.renderSprite(x, y, player, 1);
		}
	}

	public void die() {
		super.die();
	}
	
	public static Sprite player = new Sprite(16, 1, 0, SpriteSheet.mainSheet);
	public static Sprite playerWalk = new Sprite(16, 2, 0, SpriteSheet.mainSheet);
}