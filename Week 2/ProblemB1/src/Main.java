
import java.util.Scanner;

public class Main
{
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int numObstacles = sc.nextInt();
		
		//Initialize empty grid
		int[][] paths = new int[size][size];
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
				paths[i][j] = 0;
		}
		
		//Read obstacles
		for(int i = 0; i < numObstacles; i++)
		{
			int row = sc.nextInt();
			int column = sc.nextInt();
			
			paths[row][column] = -1;
		}
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				// Base case: There is one empty path to the top left square
				if(i == 0 && j == 0)
					paths[0][0] = 1;
				else if(paths[i][j] == 0) //Skip obstacle squares
				{
					// Induction: Add paths to squares on the left and above our current square.
					if(i != 0 && paths[i - 1][j] > 0)
						paths[i][j] += paths[i - 1][j];
					
					if(j != 0 && paths[i][j - 1] > 0)
						paths[i][j] += paths[i][j - 1];
				}
			}
		}
		
		// Uncomment this section to print the entire grid.
		/*for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
				System.out.format("%4d  ", paths[i][j]);
			
			System.out.println();
		}*/
		
		// Print the final answer.
		System.out.println(paths[size - 1][size - 1]);
	}

}
