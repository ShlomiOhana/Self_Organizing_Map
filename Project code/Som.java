import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Som {

	private Node[][] tablemap;
	Map<DataVector,Position> mapDateVectorToPositions;
	int dimX;
	int dimY;
	int dimVectorData;

	public Som(int x, int y, int dimVector)
	{
		mapDateVectorToPositions = new HashMap<DataVector,Position>();
		dimX = x;
		dimY = y;
		dimVectorData = dimVector;
		tablemap = new Node[dimX][dimY];
		Node.setDim(x, y);
		for(int i=0; i<dimX; i++) {
			for(int j=0; j<dimY; j++)
			{
				tablemap[i][j] = new Node();
				tablemap[i][j].setPosition(new Position(i,j));
			}
		}
		shuffelWeight();

	}

	private void shuffelWeight()
	{
		Random rand = new Random();
		List<Double> weight = new ArrayList<Double>();
		for(int i=0; i<dimX; i++) 
			for(int j=0; j<dimY; j++)
			{
				weight.clear();
				for(int index=0; index < dimVectorData; index++)
				{
					double generatedDouble = rand.nextInt(255) + 1;
					weight.add((double) generatedDouble);
				}
				tablemap[i][j].setWeight(weight);
			}




	}

	public double gaussian(double d)
	{
		return Math.exp(-Math.pow(d, 2)/(2*Math.pow(d, 2)));
	}



	public double h(double distance, double t)
	{
		return Math.exp(-Math.pow(distance, 2)/(2*Math.pow(t, 2)));
	}



	private List<Double> subVector(List<Double> vect1, List<Double> vect2)
	{
		List<Double> vec = new ArrayList<Double>();
		for(int i=0; i<vect1.size(); i ++)
		{
			vec.add(vect1.get(i)-vect2.get(i));
		}

		return vec;
	}
	private List<Double> muxVectors(double distance, List<Double> weight) {
		List<Double> vector = new ArrayList<Double>();

		for(int i=0; i<weight.size(); i++)
			vector.add(weight.get(i)*distance);
		return vector;

	}
	private List<Double> addVectors(List<Double> list, List<Double> vect2) {
		List<Double> vector = new ArrayList<Double>();

		for(int i=0; i<list.size(); i++)
			vector.add(list.get(i)+vect2.get(i));
		return vector;

	}

	public String toString()
	{
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("PRINT WEIGHT"+System.lineSeparator());
		for(int i=0; i<dimX; i++) {
			for(int j=0; j<dimY; j++){
				stringBuilder.append("Position: "+i +" "+j+" : "+System.lineSeparator()+tablemap[i][j]);
			}
			stringBuilder.append(System.lineSeparator());		
		}
		stringBuilder.append("END"+System.lineSeparator());
		return stringBuilder.toString();
	}
	
	public void printMap()
	{

		for (DataVector key: mapDateVectorToPositions.keySet()){

			System.out.print(mapDateVectorToPositions.get(key).toString());
			System.out.print(key.toString());
			System.out.println();
		


		} 
	}


	public void setSomMap(int maxCycle, List<DataVector> vectorAllNodes)
	{
		double n0=0.1;
		Node minNode = null;
		for(int cycle=0; cycle<maxCycle; cycle++)
		{
			/*data*/
			for(DataVector dataVector: vectorAllNodes)
			{
				double min = 10000;
				double bmuResult;
				/*som node*/
				for(int i=0; i<dimX; i++) {
					for(int j=0; j<dimY; j++)
					{
						bmuResult = dataVector.bmu(tablemap[i][j].getWeight());
						if( min>bmuResult ) {
							min = bmuResult;
							minNode = tablemap[i][j];
						}
					}
				}
				mapDateVectorToPositions.put(dataVector, minNode.getPosition());
				ArrayList<Position> allNeighbers = minNode.getAllNeighbers();
				
				
				
				Node neighNode1 = minNode;
				/**/
				List<Double> currentWeight1 = neighNode1.getWeight();
				List<Double> weight1 = subVector( dataVector.getVector(),currentWeight1);
				List<Double> weightScaling1 = muxVectors((n0*1),weight1);
				List<Double> newWeight1 =addVectors(currentWeight1 ,weightScaling1);
				neighNode1.setWeight(newWeight1);
				
				
				for (Position position :allNeighbers) {
					Node neighNode = tablemap[position.getX()][position.getY()];
					double distance = h(neighNode.bmu(minNode.getWeight()),1);
					/**/
					List<Double> currentWeight = neighNode.getWeight();
					List<Double> weight = subVector( dataVector.getVector(),currentWeight);
					List<Double> weightScaling = muxVectors((n0*distance),weight);
					List<Double> newWeight =addVectors(currentWeight ,weightScaling);
					neighNode.setWeight(newWeight);
					//node.weight(cycle+1)=node.weight+(n0)*math.exp((-cycle/maxCycle)*h(node.bmu(nodeMin),cycle))*wheight_function(node.position, nodeMin.position)

				}
				System.out.println("ITER :"+cycle);
				System.out.println(toString());
				System.out.println();


			}

		}

	}


}
