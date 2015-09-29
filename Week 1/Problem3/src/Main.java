
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int x1 = sc.nextInt();
		int y1 = sc.nextInt();
		int x2 = sc.nextInt();
		int y2 = sc.nextInt();
		int x3 = sc.nextInt();
		int y3 = sc.nextInt();

		int area = x1*y1 + x2*y2 + x3*y3;
		int side = -1; 
		for(int i = 0; i*i <= area; i++)
		{
			if(i*i == area)
				side = i;
		}
		
		boolean found = false;
		if(side > 0)
		{
			//The total area is the same as a square of the given side length.
			//Try to arrange the pieces. One needs to cover an entire side, while
			//the other two are next to it.
			
			//Try all 8 subsets of possible rotations
			int xr1, xr2, xr3, yr1, yr2, yr3;
			for(int i = 0; i < 8 && !found; i++)
			{
				if(i%2 == 0)
				{
					xr1 = x1;
					yr1 = y1;
				}
				else
				{
					xr1 = y1;
					yr1 = x1;
				}
				
				if(i%4 <= 1)
				{
					xr2 = x2;
					yr2 = y2;
				}
				else
				{
					xr2 = y2;
					yr2 = x2;
				}
				
				if(i <= 4)
				{
					xr3 = x3;
					yr3 = y3;
				}
				else
				{
					xr3 = y3;
					yr3 = x3;
				}
				
				//Case 1: First logo at top, second and third next to each other
				if(xr1 == side && xr2 + xr3 == side && yr2 == yr3 && yr1 + yr2 == side)
				{
					found = true;
					//Put A's in first y1 rows, B's up to x2 and C's afterwards in next y2 rows
					for(int j = 0; i < side; i++)
					{
						for(int k = 0; k < side; k++)
						{
							if(j < yr1)
							{
								System.out.print("A");
							}
							else if(k < xr2)
							{
								System.out.print("B");
							}
							else
							{
								System.out.print("C");
							}
						}
						System.out.println();
					}
				}

				//Case 2: Second logo at top, first and third next to each other
				else if(xr2 == side && xr1 + xr3 == side && yr1 == yr3 && yr2 + yr1 == side)
				{
					found = true;
					//Put B's in first y2 rows, A's up to x1 and C's afterwards in next y1 rows
					for(int j = 0; j < side; j++)
					{
						for(int k = 0; k < side; k++)
						{
							if(j < yr2)
							{
								System.out.print("B");
							}
							else if(k < xr1)
							{
								System.out.print("A");
							}
							else
							{
								System.out.print("C");
							}
						}
						System.out.println();
					}
				}
				
				//Case 3: Third logo at top, first and second next to each other
				else if(xr3 == side && xr1 + xr2 == side && yr1 == yr2 && yr3 + yr1 == side)
				{
					found = true;
					//Put C's in first y3 rows, A's up to x1 and B's afterwards in next y1 rows
					for(int j = 0; j < side; j++)
					{
						for(int k = 0; k < side; k++)
						{
							if(j < yr3)
							{
								System.out.print("C");
							}
							else if(k < yr1)
							{
								System.out.print("A");
							}
							else
							{
								System.out.print("B");
							}
						}
						System.out.println();
					}
				}
				
				//Case 4: All logos stacked vertically
				else if(xr1 == side && xr2 == side && xr3 == side && yr1 + yr2 + yr3 == side)
				{
					found = true;
					for(int j = 0; j < side; j++)
					{
						for(int k = 0; k < side; k++)
						{
							if(j < yr1)
							{
								System.out.print("A");
							}
							else if(j < yr1 + yr2)
							{
								System.out.print("B");
							}
							else
							{
								System.out.print("C");
							}
						}
						System.out.println();
					}
				}
			}
		}
		
		if(!found)
			System.out.print("-1");		
	}
}
