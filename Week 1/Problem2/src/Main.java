
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] skillVals = new int[n];
		for(int i = 0; i < n; i++)
		{
			skillVals[i] = sc.nextInt();
		}
		
		int[] remaining = new int[10]; //The number of units of improvement to reach the next level
		int maxExtra = 0; //The most that this skill can improve.
		int currRating = 0;
		for(int i = 0; i < n; i++)
		{
			int newRem =  10 - skillVals[i]%10;
			if(newRem != 10)
			{
				remaining[newRem]++;
			}
			
			currRating += skillVals[i]/10;
			
			maxExtra += (10 - skillVals[i]/10);
		}
		//System.out.println("Initial rating: " + Integer.toString(currRating));
		
		int i = 1;
		while(k >= i && i < 10)
		{
			if(k >= i*remaining[i])
			{
				k -= i*remaining[i];
				currRating += remaining[i];
				maxExtra -= remaining[i];
			}
			else
			{
				currRating += k/i;
				k -= i*(k/i);
				maxExtra -= k/i;
			}
			//System.out.println("New rating: " + Integer.toString(currRating));
			//System.out.println("New k: " + Integer.toString(k));
			
			i++;
		}
		
		int extraAdd = k/10;
		currRating += Math.min(extraAdd, maxExtra);
		
		System.out.println(currRating);
	}

}
