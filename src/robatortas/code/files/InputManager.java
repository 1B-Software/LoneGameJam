package robatortas.code.files;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean w, a, s, d;
	public boolean up, down, left, right, space;
	
	public boolean one, two;
	
	public void tick() {
		// We have the up and w keys to probably separate their uses in future stuff.
		w = keys[KeyEvent.VK_W];
		a = keys[KeyEvent.VK_A];
		s = keys[KeyEvent.VK_S];
		d = keys[KeyEvent.VK_D];

		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		
		space = keys[KeyEvent.VK_SPACE];
		
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
	}
	
	private boolean toggle;
	private int toggled;
	public boolean toggle(boolean key, boolean b) {
		if(key && !toggle) {
			b = !b;
			toggle = true;
			} else if (key && toggle) {
				if(toggled > 1000) toggle = false;
			}
		if(toggle) toggled++;
		else toggled = 0;
		return b;
	}
	
	public void keyTyped(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	
}