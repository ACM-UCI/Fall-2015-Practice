
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int costs[] = new int[n];
		int amounts[] = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			amounts[i] = sc.nextInt();
			costs[i] = sc.nextInt();
		}
		
		int minCost = costs[0];
		int totalCost = amounts[0]*costs[0];
		
		for(int i = 1; i < n; i++)
		{
			if(costs[i] < minCost)
			{
				minCost = costs[i];
			}
			
			totalCost += amounts[i]*minCost;
		}

		System.out.println(totalCost);
	}

}
