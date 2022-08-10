package robatortas.code.files;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	public Display(int w, int h, String title, JFrame frame, Game game) {
		
		Dimension size = new Dimension(w, h);
		
		frame.add(game);
		frame.pack();
		game.start();
		
		frame.setTitle(title);
		frame.setSize(size);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.requestFocus();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}