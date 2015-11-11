
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int vals[] = new int[size];
		
		int maxNum = 0;
		for(int i = 0; i < size; i++)
		{
			vals[i] = sc.nextInt();
			if(vals[i] > maxNum)
				maxNum = vals[i];
		}
		
		int smallestFactors[] = getSmallestFactors(maxNum);
		
		Random rand = new Random();
		long hashVals[] = new long[maxNum + 1];
		for(int i = 0; i <= maxNum; i++)
		{
			hashVals[i] = rand.nextLong();
		}
		
		long valHashes[] = new long[size];
		for(int i = 0; i < size; i++)
		{
			int nextVal = vals[i];
			long nextHash = 0;
			while(nextVal > 1)
			{
				nextHash ^= hashVals[smallestFactors[nextVal]];
				nextVal /= smallestFactors[nextVal];
			}

			valHashes[i] = nextHash;
		}
		
		HashMap<Long, Integer> prefValHashes = new HashMap<Long, Integer>();
		long currHash = 0;
		for(int i = 0; i < size; i++)
		{
			currHash ^= valHashes[i];
			if(prefValHashes.containsKey(currHash))
				prefValHashes.put(currHash, prefValHashes.get(currHash) + 1);
			else
			{
				prefValHashes.put(currHash, 1);
			}
		}
		
		long segments = 0;
		for(long hash : prefValHashes.keySet())
		{
			int numVals = prefValHashes.get(hash);
			if(hash != 0)
				segments += (numVals*(numVals - 1))/2;
			else
				segments += (numVals * (numVals - 1))/2 + numVals;
		}
		
		System.out.println(segments);
	}
	
	//Gets smallest prime factor of each number from 1 up to and including maxNum
	public static int[] getSmallestFactors(int maxNum)
	{
		int smallestFactors[] = new int[maxNum + 1];
		smallestFactors[1] = 1;
		
		for(int i = 2; i <= maxNum; i++)
		{
			if(smallestFactors[i] == 0)
			{
				for(int j = i; j <= maxNum; j += i)
				{
					smallestFactors[j] = i;
				}
			}
		}
		
		return smallestFactors;
	}
}
