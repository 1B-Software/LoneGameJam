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
		
		sprite = player;
	}
	
	private boolean armed = false;
	
	protected double xa, ya;
	
	protected double xv = 1, yv = 1;
	
	private int tickTime = 0;
	
	private boolean jump = true;
	private boolean shoot = true;
	
	public void tick() {
		super.tick();
		
		tickTime++;
		
		if(animate < 10000) animate++; 
		else animate = 0;
		
		xa = 0;
		ya = 0;
		
		// Gravity
		if(tickTime % 10 == 0) gravity+=tickTime&1;
		controls();

		if(cat!=null) {
			if(cat.onAir) cat.xa += 0.01;
		}
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else walking = false;
	}
	
	public void controls() {
		// Jump!
		if(input.w && !onAir) {
			if(!jump) {
				gravity -= 4 + xa * 0.5;
				jump = true;
			}
		} else jump = false;
		
		if(input.a || input.left) xa-=xv;
		if(input.d || input.right) xa+=xv;
		
		if(input.space) {
			if(!shoot) {
				if(armed) shoot();
				shoot = true;
			}
		} else shoot = false;
	}
	
	private Cat cat;
	public void shoot() {
		level.add(cat = new Cat(x, y));
		if(input.up) {
			cat.gravity -= 4 + xa * 0.5;
		}
	}
	
	int flip = 0;
	private boolean oneToggle;
	
	// SUCH A MESS!!!!!! (I DON'T HAVE MUCH TIME LEFT TO FIX IT!!!!)
	public void render(Screen screen) {
		
		oneToggle = input.toggle(input.one, oneToggle);
		if(oneToggle) armed = true;
		else armed = false;
		
		if(walking) {
			if(dir == 3) {
				flip = 1;
				if (animate % 20 > 10 && animate % 20 <= 20) {
					if(oneToggle) {
						sprite = playerWalkGun;
						if(input.up) sprite = playerWalkGunUp;
						if(input.down) sprite = playerWalkGunDown;
					}
					else sprite = playerWalk;
				} else {
					if(oneToggle) {
						sprite = playerGun;
						if(input.up) sprite = playerGunUp;
						if(input.down) sprite = playerGunDown;
					}
					else sprite = player;
				}
			}
			else if(dir == 1){
				flip = 0;
				if (animate % 20 > 10 && animate % 20 <= 20) {
					if(oneToggle) {
						sprite = playerWalkGun;
						if(input.up) sprite = playerWalkGunUp;
						if(input.down) sprite = playerWalkGunDown;
					}
					else sprite = playerWalk;
				} else {
					if(oneToggle) {
						sprite = playerGun;
						if(input.up) sprite = playerGunUp;
						if(input.down) sprite = playerGunDown;
					}
					else sprite = player;
				}
			}
		} else {
			if(dir == 1) {
				flip = 0;
				if(oneToggle) {
					sprite = playerGun;
					if(input.up) sprite = playerGunUp;
					if(input.down) sprite = playerGunDown;
				}
				else sprite = player;
			}
			if(dir == 3) {
				flip = 1;
				if(oneToggle) {
					sprite = playerGun;
					if(input.up) sprite = playerGunUp;
					if(input.down) sprite = playerGunDown;
				}
				else sprite = player;
			}
		}
		
		screen.renderMob(x, y, this, flip);
	}

	public void die() {
		super.die();
	}
	
	public static Sprite player = new Sprite(16, 1, 0, SpriteSheet.mainSheet);
	public static Sprite playerWalk = new Sprite(16, 2, 0, SpriteSheet.mainSheet);
	
	public static Sprite playerGun = new Sprite(16, 0, 1, SpriteSheet.mainSheet);
	public static Sprite playerWalkGun = new Sprite(16, 1, 1, SpriteSheet.mainSheet);

	public static Sprite playerGunUp = new Sprite(16, 0, 2, SpriteSheet.mainSheet);
	public static Sprite playerGunDown = new Sprite(16, 0, 3, SpriteSheet.mainSheet);
	
	public static Sprite playerWalkGunUp = new Sprite(16, 1, 2, SpriteSheet.mainSheet);
	public static Sprite playerWalkGunDown = new Sprite(16, 1, 3, SpriteSheet.mainSheet);
}