
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next().trim();

		// isPal[i][j] will be true if the substring between i and j is a palindrome.
		boolean isPal[][] = new boolean[n][n];
		
		// First base case: Strings of length 1 are palindromes.
		for(int i = 0; i < n; i++)
		{
			isPal[i][i] = true;
		}
		
		// Second base case: Strings of length 2 are palindromes if the two characters are the same.
		for(int i = 0; i < n - 1; i++)
		{
			isPal[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
		}
		
		// Build along diagonals
		for(int i = n + 2; i < 2*n; i++)
		{
			for(int j = 0; j < 2*n - i; j++)
			{
				int row = j;
				int column = i - n + j;
				
				// Induction: A string is a palindrome if the first and last characters are the same,
				// and the middle characters are a palindrome.
				isPal[row][column] = (isPal[row + 1][column - 1] && s.charAt(row) == s.charAt(column));
			}
		}
		
		// Uncomment to print entire grid
		/*for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				System.out.print(Boolean.toString(isPal[i][j]) + " ");
			}
			
			System.out.println();
		}*/
		
		// paths[i] stores the number of ways to break up the first i characters into palindromes.
		int paths[] = new int[n + 1];
		paths[0] = 1;
		for(int j = 1; j <= n; j++)
		{
			for(int i = 0; i < j; i++)
			{
				//If the string from i to j is a palindrome, then we can take any method of breaking up
				//the first i - 1 characters and add the palindrome from i to j.
				if(isPal[i][j - 1])
					paths[j] += paths[i];
			}
		}
		
		//Print the number of ways to break up each prefix of characters.
		/*for(int i = 1; i <= n; i++)
		{
			System.out.println(Integer.toString(paths[i]) + " ");
		}*/
		
		System.out.println(paths[n]);
	}
}
