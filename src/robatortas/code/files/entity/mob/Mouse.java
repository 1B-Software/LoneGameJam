package robatortas.code.files.entity.mob;

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
	
	public static Sprite mouse = new Sprite(16, 7, 0, SpriteSheet.mainSheet);
}