package robatortas.code.files;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int width = 720, height = 520;
	
	private Thread thread;
	private boolean running = false;
	
	public JFrame frame = new JFrame();
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	
	private InputManager input = new InputManager();
	
	public Game() {
		// INITIALIZE SCREEN FIRST!!!
		screen = new Screen(width, height);
		new Display(width, height, "UNTITLED GAME", frame, this);
		frame = new JFrame();
		
		addKeyListener(input);
	}
	
	// ALWAYS END THREAD WITH RUNNING VARIABLE!
	public synchronized void start() {
		thread = new Thread(this, "GAME_THREAD");
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		running = false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		double ns = 1000000000/60;
		int frames = 0;
		int ticks = 0;
		
		while(running) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - lastTime;
			delta += passedTime / ns;
			lastTime = currentTime;
			
			while(delta > 1) {
				delta--;
				tick();
				ticks++;
			}
			
			frames++;
			render();
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("TPS: " + ticks + "  ||  " + "FPS: " + frames);
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	int x, y=0;
	public void tick() {
		input.tick();
		
		if(input.w) {
			y--;
		}
		if(input.a) {
			x--;
		}
		if(input.s) {
			y++;
		}
		if(input.d) {
			x++;
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		screen.clear();
		
		int xOffset = 0;
		int yOffset = 0;
		
		Graphics g = bs.getDrawGraphics();
		
//		screen.renderPixel(x, y);
		screen.renderSprite(x, y, Sprite.player);
		
		g.drawImage(image, 0, 0, width, height, null);
		
		g.dispose();
		bs.show();
	}
}