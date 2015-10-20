
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int maxWeight = 1000030; //A little larger in case of overflow
		
		//Count how many of each size we have
		int countWeights[] = new int[maxWeight];
		for(int i = 0; i < n; i++)
		{
			countWeights[i] = 0;
		}
		
		for(int i = 0; i < n; i++)
		{
			int x = sc.nextInt();
			countWeights[x]++;
		}
		
		// Pair weights of the same size into a single weight that's one larger
		int totalSteps = 0;
		for(int i = 0; i < maxWeight - 1; i++)
		{
			countWeights[i + 1] += countWeights[i]/2;
			totalSteps += countWeights[i]%2;
			//System.out.println(countWeights[i]);
		}
		
		System.out.println(totalSteps);
	}
}
