
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int numRecords = sc.nextInt();
		
		int records[] = new int[numRecords];
		int maxRecord = 0;
		for(int i = 0; i < numRecords; i++)
		{
			char c = sc.next().charAt(0);
			int nextInt = sc.nextInt();
			if(c == '+')
				records[i] = nextInt;
			else
				records[i] = -1*nextInt;
			
			if(nextInt > maxRecord)
				maxRecord = nextInt;
			
			sc.nextLine(); //Clear the newline character
		}
		
		boolean isInRoom[] = new boolean[maxRecord + 1];
		int minCapacity = 0;
		int currCapacity = 0;
		for(int i = 0; i < numRecords; i++)
		{
			int next = records[i];
			boolean enter = (records[i] > 0);
			int id = Math.abs(records[i]);
			
			if(enter && isInRoom[id])
			{
				System.out.println("Error!");
			}
			else if(enter && !isInRoom[id])
			{
				currCapacity++;
				isInRoom[id] = true;
				
				if(currCapacity > minCapacity)
					minCapacity = currCapacity;
			}
			else if(!enter && isInRoom[id])
			{
				currCapacity--;
				isInRoom[id] = false;
			}
			else //if(!enter && !isInRoom[id])
			{
				minCapacity++; //The person was in our room
			}
			
			//System.out.println(currCapacity + ", " + minCapacity);
		}
		
		System.out.println(minCapacity);
	}
}
