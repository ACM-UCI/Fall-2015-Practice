
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int dates[] = new int[n];
		int newDates[] = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			dates[i] = sc.nextInt();
		}
		
		//We will always try to make each date as small as possible, subject to
		//our given conditions. This leaves more possibilities for subsequent dates.
		int minDate = 1000;
		int maxDate = 2011;
		int newDate, minNewDate;
		for(int i = 0; i < n; i++)
		{
			//System.out.println(i);
			minNewDate = 9999;
			int dig1 = dates[i]/1000;
			int dig2 = (dates[i]/100)%10;
			int dig3 = (dates[i]/10)%10;
			int dig4 = dates[i]%10;
			
			//We check every possible change that we can make.
			
			//First we change the first digit.
			for(int j = 1; j <= 2; j++)
			{
				newDate = j*1000 + dig2*100 + dig3*10 + dig4;
				if(newDate >= minDate && newDate < minNewDate)
				{
					//System.out.println(newDate);
					minNewDate = newDate;
				}
			}
			
			//Next we try changing the second digit.
			for(int j = 0; j <= 9; j++)
			{
				newDate = dig1*1000 + j*100 + dig3*10 + dig4;
				if(newDate >= minDate && newDate < minNewDate)
				{
					//System.out.println(newDate);
					minNewDate = newDate;
				}
			}
			
			//Next we try changing the third digit.
			for(int j = 0; j <= 9; j++)
			{
				newDate = dig1*1000 + dig2*100 + j*10 + dig4;
				if(newDate >= minDate && newDate < minNewDate)
				{
					//System.out.println(newDate);
					minNewDate = newDate;
				}
			}
			
			//Next we try changing the fourth digit.
			for(int j = 0; j <= 9; j++)
			{
				newDate = dig1*1000 + dig2*100 + dig3*10 + j;
				if(newDate >= minDate && newDate < minNewDate)
				{
					//System.out.println(newDate);
					minNewDate = newDate;
				}
			}
			
			if(minNewDate <= maxDate)
			{
				newDates[i] = minNewDate;
				minDate = minNewDate;
			}
			else
			{
				System.out.println("No solution");
				return;
			}
		}
		
		for(int i = 0; i < n; i++)
			System.out.println(newDates[i]);
	}

}
