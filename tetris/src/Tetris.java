import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tetris {

	public static void main(String[] args) {
		Runnable windowThread = new Runnable () {			
			@Override
			public void run() {
				JFrame frame = new TetrisFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				JOptionPane.showMessageDialog(frame, "Press 'enter' to start game. Use left, "
						+ "right and down keys to move the figure, 'A' and 'S' keys to rotate it");
			

			}//run
		}; //new Runnable ()

		EventQueue.invokeLater(windowThread);
	}//main



}
