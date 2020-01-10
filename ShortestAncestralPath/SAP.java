package wordnet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
import java.util.Scanner;
/**
 * This class is for finding the shortest ancestral path.
 * @author Divya
 *
 */
public class SAP {
	private DiGraph graph;
	
	   // constructor takes a digraph (not necessarily a DAG)
	/**
	 * Constructor
	 * @param g
	 */
	   public SAP(DiGraph g) {
		   graph = new DiGraph(g);
	   }

	   // length of shortest ancestral path between v and w; -1 if no such path
	   /**
	    * This method is used for finding the shortest ancestral path.
	    * @param v vertex
	    * @param w vertex
	    * @return length between v and w
	    */
	   public int length(int v, int w) {
		   BreadthFirstSearch b1 = new BreadthFirstSearch(graph, v);
		   BreadthFirstSearch b2 = new BreadthFirstSearch(graph, w);
		   int tempDistance = Integer.MAX_VALUE;
	        for (int s = 0; s < graph.V(); s++) {
	            if (b1.hasPathTo(s) && b2.hasPathTo(s)) {
	                int dist = b1.distTo(s) + b2.distTo(s);
	                if (dist < tempDistance) {
	                    tempDistance = dist;
	                }
	            }
	        }
	        return tempDistance == Integer.MAX_VALUE ? -1 : tempDistance;
	    }


	   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	   /**
	    * This method returns the shortest ancestor.
	    * @param v vertex
	    * @param w vertex
	    * @return ancestor of v and w
	    */
	   public int ancestor(int v, int w) {
		   BreadthFirstSearch b1 = new BreadthFirstSearch(graph, v);
	        BreadthFirstSearch b2 = new BreadthFirstSearch(graph, w);

	        int shortest = Integer.MAX_VALUE;
	        int ancestor = -1;
	        for (int s = 0; s < graph.V(); ++s) {
	            if (b1.hasPathTo(s) && b2.hasPathTo(s)) {
	                int dist = b1.distTo(s) + b2.distTo(s);
	                if (dist < shortest) {
	                    shortest = dist;
	                    ancestor = s;
	                }
	            }
	        }

	        return ancestor;
	   }

	   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	   /**
	    * This method is used for finding the shortest ancestral path.
	    * @param v vertex
	    * @param w vertex
	    * @return length between v and w
	    */
	   public int length(Iterable<Integer> v, Iterable<Integer> w) {
		   int shortestPath = Integer.MAX_VALUE;
		   for(int j : v) {
			   for(int k : w) {
				   int x = ancestor(j,k);
				   if(x < shortestPath) {
					   shortestPath = x;
				   }
			   }
		   }
		   return shortestPath;
	   }

	   // a common ancestor that participates in shortest ancestral path; -1 if no such path
	   /**
	    * This method returns the shortest ancestor.
	    * @param v vertex
	    * @param w vertex
	    * @return ancestor of v and w
	    */
	   public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		   int ancestor = -1;
		   int shortestPath = Integer.MAX_VALUE;
		   for(int j : v) {
			   for(int k : w) {
				   int x = length(j,k);
				   int y = ancestor(j,k);
				   if(x < shortestPath) {
					   ancestor = y;
				   }
			   }
		   }
		   return ancestor;
	   }

	   // do unit testing of this class
	   /**
	    * main method for unit testing
	    * @param args
	    * @throws FileNotFoundException
	    */
	   public static void main(String[] args) throws FileNotFoundException {
		   ArrayList<String> arr2 = new ArrayList<String>();
		   	 File file = new File("digraph1.txt"); 
			     Scanner sc = new Scanner(file);
			     while (sc.hasNextLine()) {
					 String str = sc.nextLine();
					 arr2.add(str);
		         }
			     int V = Integer.parseInt(arr2.get(0));
			     int E = Integer.parseInt(arr2.get(1));
			     DiGraph graph = new DiGraph(V);
			     for (int i = 2; i < V; i++) {
						String str[] = arr2.get(i).split(" ");
						int v = Integer.parseInt(str[0]);
						int w = Integer.parseInt(str[1]);
						 graph.addEdge(v,w);
					      }
			     for(int v = 0; v < graph.V(); v++) {
			 		System.out.print(v+ "-------------->");
			 			for(int w : graph.adj(v)) {
			 				System.out.print(w+" ");
			 			}
			 			System.out.println();
	   }
			     SAP s = new SAP(graph);
			     System.out.println(s.length(9, 11));
			     System.out.println(s.ancestor(9, 11));
	}
}
