package robatortas.code.files;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	public JFrame frame;
	
	public Display(int w, int h, String title, JFrame frame, Game game) {
		this.frame = frame;
		
		Dimension size = new Dimension(w, h);
		
		frame.setTitle(title);
		
		frame.add(game);
		
		frame.setSize(size);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.pack();
		game.start();
		
		game.requestFocus();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}