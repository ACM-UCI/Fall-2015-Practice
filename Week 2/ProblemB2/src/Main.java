
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		String s = sc.next().trim();
		
		// bestPal[i][j] gives us the length of the best palindrome
		// between characters i and j.
		// bestPal[0][n - 1] gives us the length of the best palindrome
		// in the entire string.
		int[][] bestPal = new int[n][n];
		
		// Base case: Each string of length 1 is a palindrome.
		for(int i = 0; i < n; i++)
		{
			bestPal[i][i] = 1;
		}
		
		// Now we build out along the diagonals in the upper right half.
		for(int i = n + 1; i < 2*n; i++)
		{
			for(int j = 0; j < 2*n - i; j++)
			{
				int row = j;
				int column = i - n + j;
				//System.out.println(Integer.toString(row) + ", " + Integer.toString(column));
				
				if(s.charAt(row) == s.charAt(column))
				{
					if(j == i + 1)
						bestPal[row][column] = 2;
					else
						bestPal[row][column] = bestPal[row + 1][column - 1] + 2;
				}
				else
				{
					bestPal[row][column] = Math.max(bestPal[row + 1][column], bestPal[row][column - 1]);
				}
			}
		}
		
		// Uncomment to print entire grid
		/*for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				System.out.format("%4d", bestPal[i][j]);
			}
			
			System.out.println();
		}*/
		
		// Backtrack to find the best set of choices
		//System.out.println("Backtracking...");
		String palStrStart = "";
		String palStrEnd = "";
		String palStr;
		int row = 0;
		int column = n - 1;
		while(column > row)
		{
			//System.out.println(Integer.toString(row) + ", " + Integer.toString(column));
			if(bestPal[row][column] == bestPal[row][column - 1])
			{
				column--;
			}
			else if(bestPal[row][column] == bestPal[row + 1][column])
			{
				row++;
			}
			else if(bestPal[row][column] == bestPal[row + 1][column - 1] + 2)
			{
				palStrStart = palStrStart + s.charAt(row);
				palStrEnd = s.charAt(column) + palStrEnd;
				row++;
				column--;
			}
		}
		
		if(row == column)
		{
			palStr = palStrStart + s.charAt(row) + palStrEnd;
		}
		else
		{
			palStr = palStrStart + palStrEnd;
		}
		
		System.out.println(palStr);
	}
}
