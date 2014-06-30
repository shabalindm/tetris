import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;


public class Panel extends JPanel {
	int  columns, rows; 
	
	public Panel(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		panel = new int [columns][rows];
	
	}

	private int [][] panel;
	
	int [][] shape = new int [][] {{0}};
	int shape_x=4, shape_y=0; //���������� �������� ������ ���� �������, ���������� ������ 
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		int square_width = g.getClipBounds().width/columns;
		int square_height = g.getClipBounds().height/rows;
		
		//���������� ������, ��� ������� �� ���
		for (int x = 0 ; x < columns; x++ )
			for (int y = 0 ; y < rows; y++)
				if (panel[x][y]==1){
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(x*square_width, y*square_height, square_width, square_height);
					g.setColor(Color.BLACK);
					g.drawRect(x*square_width, y*square_height, square_width, square_height);
					
				}
		
		 //���������� ������ ���������� ������
		for (int x = 0; x < shape.length; x++ )
			for (int y = 0; y < shape[0].length; y++)
				if (shape[x][y]==1){
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect((x+ shape_x)*square_width, (y + shape_y)*square_height,
							square_width, square_height);
					g.setColor(Color.BLACK);
					g.drawRect((x + shape_x)*square_width, (y + shape_y)*square_height,
							square_width, square_height);
					
				}
	}
	
		
	synchronized boolean  possible(int [][] shape, int shape_x, int shape_y){
		//������� ���������, ��������� �� ������������ �������� ������ � ������� ���������
		for (int x = 0; x < shape.length; x++ )
			for (int y = 0; y < shape[x].length; y++){
				if(shape[x][y] == 1 && (x+shape_x<0 || x+shape_x >= columns 
						 || y+shape_y >= rows || panel[x+shape_x][y+shape_y] == 1
						))			
					return false;
			}
		
		return true;
		
	}

	synchronized public boolean moveRight(){
		if (!possible(shape, shape_x + 1,  shape_y ) )
			return false;
		
		shape_x++;
		repaint();
		return true;
			
	}
	
	
	synchronized public boolean moveLeft(){
		if (!possible(shape, shape_x - 1,  shape_y ) )
			return false;
		
		shape_x--;
		repaint();
		return true;
		}
	
	synchronized public boolean moveDown(){
		if (!possible(shape, shape_x,  shape_y+1 ) )
			return false;
		
		shape_y++;
		repaint();
		return true;
		}
	
	synchronized public boolean rotateRight(){
		int height = shape[0].length;
		int widht = shape.length;
		
		int[][] new_shape = new int[height][widht];//��������� ��� ����� ������
		
		for(int i = 0; i < widht; i++) //  ���������� ����� ������
			for(int j = 0; j < height; j++) 
				new_shape[height-j-1][i] = shape[i][j];
		
		/*���������� ������� �������� ������ ���� ���������� ������. ���� ��� �������� �� ������, �� �������� ����� 
		 * ����������� ������ �������� ������ ����. ��� ������ �������� �������� ��������� ������ ����������� ������,
		 *  � ���� ������ ����� ������ ����� (�/��� ������) � ����� ��������� ����� �������� - ������ ������,
		 *   ������� ����� (�/��� ����) ������.
		 * */ 
		int new_shape_x = shape_x + (widht-1)/2 - (height-1)/2 ;
		int new_shape_y = shape_y + (height-1)/2 - (widht-1)/2 ;
		
				
		if (!possible(new_shape, new_shape_x,  new_shape_y) )
			return false;
		
		shape = new_shape;
		shape_x= new_shape_x;
		shape_y= new_shape_y;
		repaint();
		return true;
	}
	
	synchronized public boolean rotateLeft(){
		int height = shape[0].length;
		int widht = shape.length;
		
		int[][] new_shape = new int[height][widht];//��������� ��� ����� ������
		
		for(int i = 0; i < widht; i++) //  ���������� ����� ������
			for(int j = 0; j < height; j++) 
				new_shape[j][widht-i-1] = shape[i][j];
		
		/*���������� ������� �������� ������ ���� ���������� ������. ���� ��� �������� �� ������, �� �������� ����� 
		 * ����������� ������ �������� ������ ����. ��� ������ �������� �������� ��������� ������ ����������� ������,
		 *  � ���� ������ ����� ������ ����� (�/��� ������) � ����� ��������� ����� �������� - ������ ������,
		 *   ������� ����� (�/��� ����) ������.
		 * */ 
		int new_shape_x = shape_x + (widht-1)/2 - (height-1)/2 ;
		int new_shape_y = shape_y + (height-1)/2 - (widht-1)/2 ;
		
				
		if (!possible(new_shape, new_shape_x,  new_shape_y) )
			return false;
		
		shape = new_shape;
		shape_x= new_shape_x;
		shape_y= new_shape_y;
		repaint();
		return true;
		
	}
	synchronized public void merge(){
		for (int x = 0; x < shape.length; x++ )
			for (int y = 0; y < shape[x].length; y++)
				if (shape[x][y] == 1)
					panel[x+shape_x][y + shape_y] = shape[x][y];
			
	}
	
	synchronized public void removeFullLines(){
		int shift = 0;
		outer:
		for(int y = rows-1; y>=0; y--){// �������� ����� ����� �� �������
			//�������� ������ �� �������������
			for(int x = 0; x< columns; x++){
				if(panel[x][y] == 0)
					break;				
				if(x ==columns-1){// ��������� ��� ������ � ��� ���������
					shift++;
					continue outer;
				}
			}//inner for
				
			//���� ����� �� ����, �� ������ �� ��������� ���������, � �� ���� ����������� ����
			if (shift>0)
				for(int x = 0; x< columns; x++)				
					panel[x][y+shift] =panel[x][y];
		}
	}

}
