import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Timer;

import javax.swing.JFrame;


public class TetrisFrame extends JFrame implements KeyListener  {
	private final int columns = 8, rows = 30;
	private final int scale = 20;	
	private final String title = "“етерис";
	
	Panel panel = new Panel(columns, rows); 
	ShapeGenerator shapeGenerator;
	private boolean game_started = false;
	
	public TetrisFrame() {
		//задаем параметры окна
		setSize(columns*scale, rows*scale);
		setResizable(false);
		setTitle(title);
		setLocationByPlatform(true);
		addKeyListener(this);
		
		
		add(panel, BorderLayout.CENTER);
		
	}
	
	/*ќсуществл€ет перемещение фигур вниз через заданные промежутки времени, 
	 * добавление новых фигур, удаление заполненных р€дов, окончание игры 
	 */
	private class GameProgress extends Thread{
		long delay = 500;
		@Override
		public void run(){
			shapeGenerator = new ShapeGenerator();
			int[][] shape = shapeGenerator.makeShape();
			while(panel.possible(shape, 3, 0)){
				
				//ƒобавл€ем новую фигуру в стартовую позицию
				panel.shape = shape;
				panel.shape_x=3; 
				panel.shape_y=0;				
				repaint();
				
				// ƒвигаем ее вниз
				while(panel.moveDown()){
					repaint();
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				//ѕосле приземлени€ 
				panel.merge();
				panel.removeFullLines();
				shape = shapeGenerator.makeShape();				
			}
			
			// Game over
			
			System.out.println("Game over!");
			
		}

		
		
		
	}

		private void startGame() {
			if (!game_started){
				new GameProgress().start();
				game_started = true;
			
			}
					
	}

		@Override
		public void keyTyped(KeyEvent e) {
		
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (!game_started && e.getKeyCode() == KeyEvent.VK_ENTER )
				startGame(); 
			else {				
				switch (e.getKeyCode()){
					case KeyEvent.VK_ENTER : startGame(); break;
					case KeyEvent.VK_RIGHT: if(panel.moveRight()) repaint(); break;
					case KeyEvent.VK_LEFT: if(panel.moveLeft()) repaint(); break;
					case KeyEvent.VK_DOWN: if(panel.moveDown()) repaint(); break;
					case KeyEvent.VK_A: if(panel.rotateLeft()) repaint(); break;
					case KeyEvent.VK_S: if(panel.rotateLeft()) repaint(); break;
				}
			}

			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

	
}
