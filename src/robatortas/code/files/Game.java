package robatortas.code.files;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import robatortas.code.files.graphics.Fonts;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.level.Level;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// I know finals should be capitalized but I don't give a shit!
	public static final int width = 256, height = 192;
	private static final int scale = 3;
	
	private static final String title = "Cat Dungeon";
	
	private Thread thread;
	private boolean running = false;
	
	public JFrame frame = new JFrame();
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
		
	private Level level;
	
	private Display display;
	
	private InputManager input = Level.input;
	
	public static int xScroll, yScroll;
	
	public Game() {
		level = Level.level;
		
		// INITIALIZE SCREEN FIRST!!!
		screen = new Screen(width, height);
		display = new Display(width*scale, height*scale, title, frame, this);
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
				display.frame.setTitle(title + "   |   " + "FPS: " + frames);
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick() {
		input.tick();
		level.tick();
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
		
		xScroll = (int)level.player.x - (width-32)/2;
		yScroll = (int)level.player.y - (height-32)/2;
		
		Graphics g = bs.getDrawGraphics();
		
		level.render(xScroll, yScroll, screen);
		
		renderGUI(xScroll, yScroll);
		Fonts font = new Fonts(screen);
		Fonts.write("HOWDY", xScroll, yScroll, 16, screen);
		
		g.drawImage(image, 0, 0, width*scale, height*scale, null);
		
		g.dispose();
		bs.show();
	}
	
	public void renderGUI(int x, int y) {
		screen.renderPixel(x+3, y+3, 13*8, 9, 0xff774A2C);
		for(int i = 0; i < level.player.health; i++) {
			screen.renderPixel((x+i*10)+5, y+5, 10, 5, 0xffff0000);
		}
	}
}