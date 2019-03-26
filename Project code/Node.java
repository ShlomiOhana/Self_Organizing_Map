import java.util.ArrayList;
import java.util.List;

public class Node {
	private List<Double> weight;
	private Position position;
	private ArrayList<Position> neighberhood;
	private static int rows;
	private static int cols;

	public Node()
	{
		neighberhood = new ArrayList<Position>();
		weight = new ArrayList<Double>();
	}
	
	public static void setDim(int row, int col)
	{
		rows=row;
		cols = col;
	}

	public void setPosition(Position position)
	{
		this.position = position;

		setNeighbers();

	}

	public Position getPosition()
	{
		return position;
	}

	public List<Double> getWeight() {
		return weight;
	}

	public void setWeight(List<Double> weight_n) {
		this.weight.clear();
		for(int i=0 ;i<weight_n.size(); i++)
		{
			this.weight.add(weight_n.get(i));
		}
	}

	private void setNeighbers()
	{
		int x = position.getX();
		int y = position.getY();
		if(withinGrid (x, y-1)) 
			neighberhood.add(new Position( x , y-1 ));
		if(withinGrid (x-1, y)) 
			neighberhood.add(new Position( x-1 , y));
		if(withinGrid (x+1, y)) 
			neighberhood.add(new Position( x+1 , y));
		if(withinGrid (x, y+1)) 
			neighberhood.add(new Position( x , y+1));
	}
	
	public ArrayList<Position> getAllNeighbers()
	{
		return neighberhood;
	}

		



		private boolean withinGrid(int colNum, int rowNum) {

			if((colNum < 0) || (rowNum <0) ) {
				return false;  
			}
			if((colNum >= cols) || (rowNum >= rows)) {
				return false;    
			}
			return true;
		}
		
		public double bmu(List<Double> vect2)
		{
			int bmuSum = 0;
			for (int i=0; i<vect2.size();i++) {
				
				bmuSum += Math.pow(weight.get(i)-vect2.get(i),2);
			}
			return Math.sqrt(bmuSum);
		}
		public String toString()
		{
			StringBuilder stringBuilder = new StringBuilder();
			for(int i=0; i< weight.size(); i++) {
				stringBuilder.append(" "+weight.get(i));
			}
			stringBuilder.append(System.lineSeparator()+"in pos: "+position.getX()+" "+position.getY()+System.lineSeparator());
			return stringBuilder.toString();
		}





	}
