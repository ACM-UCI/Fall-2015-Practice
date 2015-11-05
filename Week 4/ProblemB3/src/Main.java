
import java.util.*;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
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
		}
		
		/***************************
		//We construct a test graph with two cliques of size n/2, connected by a single edge.
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
		**************************/
		
		//First we do a breadth-first search in our original graph to get the distances
		//of each vertex from the start and from the end, so that we can use them in our
		//A* heuristic.
		int start = 0;
		int end = n - 1;
		int distanceStart[] = new int[n];
		int distanceEnd[] = new int[n];
		breadthFirst(roads, start, distanceStart);
		breadthFirst(roads, end, distanceEnd);

		
		if(distanceStart[end] < 0)
		{
			System.out.println(-1);
			return;
		}
		else
		{
			//Next we do an A* search in the implicitly defined product graph, which has
			//vertices for the pairs of positions of Alex and Bob, and edges for their pairs
			//of possible moves. However, we ignore the vertices (i,i) for all i.
			//We represent each pair (i,j) as i*n + j.
			
			//Our heuristic will be the maximum of the distances of Alex and Bob from their
			//destinations.
			
			//Each visited node will store the node it came from, or -1 by default, so we can
			//trace back our route.
			NodePair backtrace[][] = new NodePair[n][n];
			int distance[][] = new int[n][n];
			int heuristic[][] = new int[n][n];
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
				{
					distance[i][j] = -1;
					heuristic[i][j] = Math.max(distanceEnd[i], distanceStart[j]);
				}
			}
			
			PriorityQueue<NodePair> pairQueue = new PriorityQueue<NodePair>();
			pairQueue.add(new NodePair(start, end, 0 + heuristic[start][end]));
			NodePair currPair;
			distance[start][end] = 0;
			
			outerloop: //label the outer loop so we can break from it when we're done
			while(pairQueue.size() > 0)
			{
				currPair = pairQueue.poll();
				for(int adj1 : roads.get(currPair.node1))
				{
					for(int adj2 : roads.get(currPair.node2))
					{
						//System.out.println("adj1, adj2: " + adj1 + ", " + adj2);
						if(adj1 == adj2)
							continue; //Skip meeting points
						else
						{
							if(adj1 == end && adj2 == start)
							{
								distance[end][start] = distance[currPair.node1][currPair.node2] + 1;
								backtrace[end][start] = currPair;
								break outerloop;
							}
							else
							{
								if(distance[adj1][adj2] < 0)
								{
									distance[adj1][adj2] = distance[currPair.node1][currPair.node2] + 1;
									//System.out.println(distance[adj1][adj2]);
									pairQueue.add(new NodePair(adj1, adj2, distance[currPair.node1][currPair.node2] + 1 + heuristic[adj1][adj2]));
									backtrace[adj1][adj2] = currPair;
								}
							}
						}
					}
				}
			}
			
			if(distance[end][start] < 0)
			{
				System.out.println(-1);
			}
			else
			{
				System.out.println(distance[end][start]);
				int route1[] = new int[distance[end][start] + 1];
				int route2[] = new int[distance[end][start] + 1];
				route1[distance[end][start]] = end;
				route2[distance[end][start]] = start;
				
				NodePair newPair = backtrace[end][start];
				for(int i = distance[end][start] - 1; i >= 0; i--)
				{
					route1[i] = newPair.node1;
					route2[i] = newPair.node2;
					if(i > 0)
						newPair = backtrace[newPair.node1][newPair.node2];
				}
				
				for(int i = 0; i <= distance[end][start]; i++)
				{
					System.out.print((route1[i] + 1) + " "); //Convert to 1-based
				}
				System.out.println();
					
				for(int i = 0; i <= distance[end][start]; i++)
				{
					System.out.print((route2[i] + 1) + " ");
				}
			}
		}
		
		//long endTime = System.nanoTime();
		//System.out.println("Took " + (endTime - startTime) + " ns");
	}
	
	public static void breadthFirst(ArrayList<ArrayList<Integer>> graph, int start, int[] distances)
	{
		for(int i = 0; i < graph.size(); i++)
		{
			distances[i] = -1;
		}
		
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(start);
		distances[start] = 0;
		int currDistance = 1;
		while(queue.size() > 0)
		{
			ArrayList<Integer> newQueue = new ArrayList<Integer>();
			for(int currLoc : queue)
			{
				for(int newLoc : graph.get(currLoc))
				{
					if(distances[newLoc] < 0)
					{
						newQueue.add(newLoc);
						distances[newLoc] = currDistance;
					}
				}
			}
			queue = newQueue;
			currDistance++;
		}
	}
}

class NodePair implements Comparable<NodePair>
{
    int node1;
    int node2;
    int cost;
 
    public NodePair()
    {
    }
 
    public NodePair(int node1, int node2, int cost)
    {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
    }
 
    @Override
    public int compareTo(NodePair otherPair)
    {
    	return Integer.compare(this.cost, otherPair.cost);
    }
}
