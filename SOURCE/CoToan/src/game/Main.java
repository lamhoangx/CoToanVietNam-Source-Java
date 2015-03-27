package game;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] agrs) {
		Game game = new Game();
		game.setSize(758, 690);
		game.setVisible(true);
		game.setResizable(false);
		game.setLocationRelativeTo(null);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
