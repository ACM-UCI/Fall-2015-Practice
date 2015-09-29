
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] houses = new int[n];
		int[] toAdd = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			houses[i] = sc.nextInt();
		}
		
		int maxSeen = houses[n - 1];
		toAdd[n - 1] = 0;
		
		for(int i = n - 2; i >= 0; i--)
		{
			toAdd[i] = Math.max(0, maxSeen - houses[i] + 1);
			maxSeen = Math.max(houses[i], maxSeen);
		}
		
		for(int i = 0; i < n; i++)
		{
			System.out.print(Integer.toString(toAdd[i]));
			
			if(i < n - 1)
			{
				System.out.print(" ");
			}
		}
	}
}
