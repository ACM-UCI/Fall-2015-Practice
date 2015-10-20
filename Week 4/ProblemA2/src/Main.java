
import java.util.Scanner;

public class Main 
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int money[] = new int[n];
		for(int i = 0; i < n; i++)
		{
			money[i] = sc.nextInt();
		}
		
		int currSeqLen = 1;
		int maxSeqLen = 1;
		for(int i = 1; i < n; i++)
		{
			if(money[i] >= money[i - 1])
			{
				currSeqLen++;
				if(currSeqLen > maxSeqLen)
				{
					maxSeqLen = currSeqLen;
				}
			}
			else
			{
				currSeqLen = 1;
			}
		}
		
		System.out.println(maxSeqLen);
	}

}
