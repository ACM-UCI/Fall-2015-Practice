
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] houses = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			houses[i] = sc.nextInt();
		}
		
		for(int i = 0; i < n; i++)
		{
			int maxSeen = -1;
			for(int j = i + 1; j < n; j++)
			{
				maxSeen = Math.max(maxSeen, houses[j]);
			}
			
			if(maxSeen >= houses[i])
			{
				int toAdd = maxSeen - houses[i] + 1;
				System.out.print(Integer.toString(toAdd) + " ");
			}
			else
			{
				System.out.print("0 ");
			}
		}
	}

}
