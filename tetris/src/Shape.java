


public class Shape { //одна из падающих фигур
	Shape(int[][] shape, Point2 rotation_center) {
		this.shape = shape;
		this.rotation_center = rotation_center;
	}

	private int [][] shape;
	
	private Point2 rotation_center;
	
	public int x_size(){
		return shape.length;
	}
	public int y_size(){
		return shape[0].length;
	}
	public int get(int x, int y){
			return shape[x][y]; }
	
	public void rotateRight(){
		
	}
	
	public void rotateLeft(){
		
	}

}
