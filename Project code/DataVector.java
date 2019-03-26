import java.util.ArrayList;
import java.util.List;

public class DataVector {
	private Position position;
	private List<Double> vector;
	private List<DataVector> neighbors;

	
	public DataVector(List<Double> vector)
	{
		this.vector = vector;
		neighbors = new ArrayList<DataVector>();
		position = new Position(0, 0);
	}
	
	public double bmu(List<Double> vect2)
	{
		int bmuSum = 0;
		for (int i=0; i<vect2.size();i++) {
			
			bmuSum += Math.pow(vector.get(i)-vect2.get(i),2);
		}
		return Math.sqrt(bmuSum);
	}

	public List<Double> getVector() {
		return vector;
	}
	
	public void addNeighbor(DataVector node)
	{
		neighbors.add(node);
	}
	
	public List<DataVector> getAllNeighbors()
	{
		return neighbors;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}


	
	public String toString()
	{
		String str = "";
		for (int i=0; i<vector.size();i++)
		{
			str+=vector.get(i)+" ";
		}
		str+=System.lineSeparator();
		return str;
	}


}
