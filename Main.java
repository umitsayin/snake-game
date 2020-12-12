package trailGame;

import javax.swing.JFrame;

public class Main {

	private static final String array = null;

	public static void main(String[] args) {
		JFrame pencere = new JFrame(); 
		pencere.add(new Game()); 
		 
		pencere.setSize(420, 440);
		pencere.setVisible(true); 
		
	}

}
