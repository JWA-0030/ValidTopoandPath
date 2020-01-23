// Jesse Alsing


import java.io.*;
import java.util.*;

public class TopoPath{

	public static boolean hasTopoPath(String filename){
		
		try{

			// Create the scanner object for file input.
			Scanner in = new Scanner(new File(filename));
			
			// Read numbers from the file.
			int N = in.nextInt();
			boolean [][] matrix = new boolean[N][N];
			
			// I know that each line following the first is the node we are at even though it isnt scanned in
			for(int i = 0; i < N; i++){
				
				// I need to number of vertices out to make our inner for loop size
				int numberOut = in.nextInt();
				
				for(int j = 0; j < numberOut; j++){
					// Here instead of adding one to our max matrix size I just decrement the integer scanned in by 1
					matrix[i][in.nextInt() -1] = true;
				}	
			}
			
			// Thank you almighty Sean for letting us use your toposort code.
			// I only need this to make sure that there is a valid Toposort for the given graph
			int [] incoming = new int[matrix.length];
			int cnt = 0;

			for (int i = 0; i < matrix.length; i++)
				for (int j = 0; j < matrix.length; j++)
					incoming[j] += (matrix[i][j] ? 1 : 0);
			
			Queue<Integer> q = new ArrayDeque<Integer>();

			for (int i = 0; i < matrix.length; i++)
				if (incoming[i] == 0)
					q.add(i);
			
			// from my understanding of Topopath, for a path to exist there can only be one start point 
			// more that 1 start point means we cannot hit all the nodes i.e. the start node we didnt start at
			if(q.size() > 1)
				return false;
		
			while (!q.isEmpty()){

				int node = q.remove();

				++cnt;

				for (int i = 0; i < matrix.length; i++){
					if (matrix[node][i] && --incoming[i] == 0){
						
						// So again there can only be node node in the queue at a time because anymore nodes means we 
						// cant have a valid path even if a valid sort exists
						if(q.size() > 1)
							return false;
						else
							q.add(i);
					}
				}	
			}
		
			if (cnt != matrix.length)
				return false;
			} 
			
		// we want to catch an error if the file isnt found and print it to screen
		catch (FileNotFoundException ex)
		{
			System.out.println("Error "+ ex);
		}
	   
	   	// I need to decrement x and y by one so it will search the appropriate spots in my matrix
		return true;
		
	}
	
	// Honestly this assignment was like four lines of code on top of TopoSort and it scares me 
	// because from my logic it only needed 2 if statements and that scares me but I highly doubt theres
	// an edge case that breaks my logic
	public static double difficultyRating(){
	
		return 1.5;
	}

	 public static double hoursSpent(){

		return 2.2;
	}	

}