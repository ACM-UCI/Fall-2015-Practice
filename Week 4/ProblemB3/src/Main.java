
import java.util.*;

public class Main
{

	public static void main(String[] args)
	{
		long startTime = System.nanoTime();
		/*Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		//Initialize data structure for graph
		//Since all the roads have equal length, it is unweighted.
		ArrayList<ArrayList<Integer>> roads = new ArrayList<ArrayList<Integer>>(n);
		for(int i = 0; i < n; i++)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			roads.add(temp);
		}
		
		for(int i = 0; i < m; i++)
		{
			int cross1 = sc.nextInt() - 1; //Switch to 0-based
			int cross2 = sc.nextInt() - 1;
			roads.get(cross1).add(cross2);
			roads.get(cross2).add(cross1);
		}*/
		
		//We construct a test graph with two cliques of size 250, connected by a single edge.
		int n = 500;
		ArrayList<ArrayList<Integer>> roads = new ArrayList<ArrayList<Integer>>(n);
		for(int i = 0; i < n; i++)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			roads.add(temp);
		}
		
		for(int i = 0; i < n/2; i++)
		{
			for(int j = 0; j < i; j++)
			{
				roads.get(i).add(j);
				roads.get(j).add(i);
			}
		}

		for(int i = n/2; i < n; i++)
		{
			for(int j = n/2; j < i; j++)
			{
				roads.get(i).add(j);
				roads.get(j).add(i);
			}
		}
		
		roads.get(n/2 - 1).add(n/2);
		roads.get(n/2).add(n/2 - 1);
		
		//First we do a breadth-first search in our original graph, to check that Alex and Bob can
		//reach one another.
		int start = 0;
		int end = n - 1;
		boolean visitedCheck[] = new boolean[n];
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(start);
		visitedCheck[start] = true;
		while(queue.size() > 0)
		{
			ArrayList<Integer> newQueue = new ArrayList<Integer>();
			for(int currLoc : queue)
			{
				for(int newLoc : roads.get(currLoc))
				{
					if(!visitedCheck[newLoc])
					{
						newQueue.add(newLoc);
						visitedCheck[newLoc] = true;
					}
				}
			}
			queue = newQueue;
		}
		if(!visitedCheck[end])
		{
			System.out.println(-1);
			return;
		}
		else
		{
			//Next we do a bidirectional breadth-first search in the implicitly defined product graph,
			//which has vertices for the pairs of positions of Alex and Bob, and edges for their pairs
			//of possible moves. However, we ignore the vertices (i,i) for all i.
			//We represent each pair (i,j) as i*n + j.
			
			//Each visited node will store the node it came from, or -1 by default, so we can trace
			//back our route.
			int visited1[] = new int[n*n]; 
			int visited2[] = new int[n*n];
			for(int i = 0; i < n*n; i++)
			{
				visited1[i] = -1;
				visited2[i] = -1;
			}
			
			ArrayList<Integer> pairQueue1 = new ArrayList<Integer>();
			ArrayList<Integer> pairQueue2 = new ArrayList<Integer>();
			start = 0*n + n - 1;
			end = (n - 1)*n + 0;
			pairQueue1.add(start);
			pairQueue2.add(end);
			visited1[start] = 0;
			visited2[end] = 0;
			int distance = 0;
			int meeting = -1;
			
			outerloop: //label the outer loop so we can break from it when we're done
			while(pairQueue1.size() > 0 || pairQueue2.size() > 0)
			{
				ArrayList<Integer> newPairQueue1 = new ArrayList<Integer>();
				ArrayList<Integer> newPairQueue2 = new ArrayList<Integer>();
				for(int currPair : pairQueue1)
				{
					//System.out.println(currPair);
					int cross1 = currPair/n;
					int cross2 = currPair%n;
					//System.out.println("(" + cross1 + ", " + cross2 + ")");
					for(int cross3 : roads.get(cross1))
					{
						for(int cross4 : roads.get(cross2))
						{
							//System.out.println("Next pair: " + cross3 + ", " + cross4);
							if(cross3 == cross4)
								continue; //Skip meeting points
							else
							{
								int newPair = cross3*n + cross4;
								if(visited2[newPair] >= 0)
								{
									//System.out.println("Finished!");
									visited1[newPair] = currPair;
									meeting = newPair;
									distance++;
									break outerloop;
								}
								else
								{
									if(visited1[newPair] < 0)
									{
										newPairQueue1.add(newPair);
										visited1[newPair] = currPair;
									}
								}
							}
						}
					}
				}
					
				for(int currPair : pairQueue2)
				{
					//System.out.println(currPair);
					int cross1 = currPair/n;
					int cross2 = currPair%n;
					//System.out.println("(" + cross1 + ", " + cross2 + ")");
					for(int cross3 : roads.get(cross1))
					{
						for(int cross4 : roads.get(cross2))
						{
							//System.out.println("Next pair: " + cross3 + ", " + cross4);
							if(cross3 == cross4)
								continue; //Skip meeting points
							else
							{
								int newPair = cross3*n + cross4;
								if(visited1[newPair] >= 0)
								{
									//System.out.println("Finished!");
									visited2[newPair] = currPair;
									meeting = newPair;
									distance += 2;
									break outerloop;
								}
								else
								{
									if(visited2[newPair] < 0)
									{
										newPairQueue2.add(newPair);
										visited2[newPair] = currPair;
									}
								}
							}
						}
					}
				}
				pairQueue1 = newPairQueue1;
				pairQueue2 = newPairQueue2;
				distance += 2;
			}
			
			if(meeting < 0)
			{
				System.out.println(-1);
			}
			else
			{
				System.out.println(distance);
				int route1[] = new int[distance + 1];
				int route2[] = new int[distance + 1];
				
				route1[distance/2] = meeting/n;
				route2[distance/2] = meeting%n;
				
				int currPair = meeting;
				int newPair;
				for(int i = distance/2 - 1; i >= 0; i--)
				{
					newPair = visited1[currPair];
					route1[i] = newPair/n;
					route2[i] = newPair%n;
					currPair = newPair;
				}
				
				currPair = meeting;
				for(int i = distance/2 + 1; i <= distance; i++)
				{
					newPair = visited2[currPair];
					route1[i] = newPair/n;
					route2[i] = newPair%n;
					currPair = newPair;
				}
				
				for(int i = 0; i <= distance; i++)
				{
					System.out.print((route1[i] + 1) + " "); //Convert to 1-based
				}
				System.out.println();
					
				for(int i = 0; i <= distance; i++)
				{
					System.out.print((route2[i] + 1) + " ");
				}
			}
		}
		
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}
}
