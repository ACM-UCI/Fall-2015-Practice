
import java.util.*;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int vals[] = new int[size];
		
		//System.out.println(size);
		
		for(int i = 0; i < size; i++)
		{
			vals[i] = sc.nextInt();
			//System.out.println(vals[i]);
		}
		
		HashMap<Integer, Integer> prefVals = new HashMap<Integer, Integer>();
		int currSum = 0;
		for(int i = 0; i < size; i++)
		{
			currSum += vals[i];
			if(prefVals.containsKey(currSum))
				prefVals.put(currSum, prefVals.get(currSum) + 1);
			else
			{
				prefVals.put(currSum, 1);
			}
			//System.out.println("Prefix sum: " + currSum);
		}
		
		int segments = 0;
		for(int sum : prefVals.keySet())
		{
			int numVals = prefVals.get(sum);
			if(sum != 0)
				segments += (numVals*(numVals - 1))/2;
			else
				segments += (numVals * (numVals - 1))/2 + numVals;
			
			//System.out.println("Key sum: " + sum);
			//System.out.println("Number of values: " + numVals);
		}
		
		System.out.println(segments);
	}
}
