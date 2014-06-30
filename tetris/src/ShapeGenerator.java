import java.util.*;


public class ShapeGenerator {
	ArrayList<int [][]> figures = new ArrayList<int[][]>();
	{ 
		figures.add(new  int[][] {{1},{1},{1},{1}});
		figures.add(new  int[][] {{1,1},{1,1}});
		figures.add(new  int[][] {{1,1,1},{1,0,0}});
		figures.add(new  int[][] {{1,1,1},{0,0,1}});
		figures.add(new  int[][] {{1,1,0},{0,1,1}});
		figures.add(new  int[][] {{0,1,1},{1,1,0}});
		figures.add(new  int[][] {{1,1,1},{0,1,0}});
		
		// more figures
	
	}
	
	Random rand = new Random();
	public int[][] makeShape(){
		int i = rand.nextInt(figures.size());
		return figures.get(i);	
	}

}
