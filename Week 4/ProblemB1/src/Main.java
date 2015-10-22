
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		String s1 = sc.nextLine(); //Remove end of line
		s1 = sc.nextLine().trim();
		String s2 = sc.nextLine().trim();
		sc.close();
		
		int matches = 0;
		for(int i = 0; i < n; i++)
		{
			if(s1.charAt(i) == s2.charAt(i))
				matches++;
		}
		int makeSame = n - t; //Guaranteed that t < n
		
		if(n - matches > 2*t)
		{
			//By the triangle inequality, the strings are too far apart.
			System.out.println(-1);
		}
		else if(matches >= makeSame)
		{
			//We choose makeSame of the matches, and then choose something different from both
			//strings at every other location.
			
			//Use a StringBuilder instead of appending to a string
			//directly, because strings are immutable
			StringBuilder tempStr = new StringBuilder();
			int foundSame = 0;
			for(int i = 0; i < n; i++)
			{
				if(s1.charAt(i) == s2.charAt(i) && foundSame < makeSame)
				{
					foundSame++;
					tempStr.append(s1.charAt(i));
				}
				else
				{
					if(s1.charAt(i) != 'a' && s2.charAt(i) != 'a')
						tempStr.append('a');
					else if(s1.charAt(i) != 'b' && s2.charAt(i) != 'b')
						tempStr.append('b');
					else
						tempStr.append('c');
				}
			}
			
			System.out.println(tempStr.toString());
		}
		else // matches < makeSame
		{
			//We copy the string for every matching position.
			//Then for (makeSame - matches) differing positions, we choose s1.
			//For another (makeSame - matches) differing positions, we choose s2.
			//For everything else, we choose something different from both strings.
			int foundDiff = 0;
			StringBuilder tempStr = new StringBuilder();
			for(int i = 0; i < n; i++)
			{
				if(s1.charAt(i) == s2.charAt(i))
					tempStr.append(s1.charAt(i));
				else if(foundDiff < makeSame - matches)
				{
					foundDiff++;
					tempStr.append(s1.charAt(i));
				}
				else if(foundDiff < 2*(makeSame - matches))
				{
					foundDiff++;
					tempStr.append(s2.charAt(i));
				}
				else
				{
					if(s1.charAt(i) != 'a' && s2.charAt(i) != 'a')
						tempStr.append('a');
					else if(s1.charAt(i) != 'b' && s2.charAt(i) != 'b')
						tempStr.append('b');
					else
						tempStr.append('c');
				}
			}
			
			System.out.println(tempStr.toString());
		}
	}
}
