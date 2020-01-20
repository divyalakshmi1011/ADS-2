import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

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
	private Digraph graph;
	private boolean[] marked;
	   // constructor takes a digraph (not necessarily a DAG)
	/**
	 * Constructor
	 * @param g
	 */
	   public SAP(Digraph g) {
		   graph = new Digraph(g);
		   marked = new boolean[g.V()];
	   }

	   // length of shortest ancestral path between v and w; -1 if no such path
	   /**
	    * This method is used for finding the shortest ancestral path.
	    * @param v vertex
	    * @param w vertex
	    * @return length between v and w
	    */
	   public int length(int v, int w) {
		   validateVertex(v);
		   validateVertex(w);
		   BreadthFirstDirectedPaths b1 = new BreadthFirstDirectedPaths(graph, v);
		   BreadthFirstDirectedPaths b2 = new BreadthFirstDirectedPaths(graph, w);
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
		   if(v>graph.V()-1||w>graph.V()-1||v<0||w<0){
		        throw new java.lang.IndexOutOfBoundsException();
		    }
		   validateVertex(v);
		   validateVertex(w);
		   BreadthFirstDirectedPaths b1 = new BreadthFirstDirectedPaths(graph, v);
		   BreadthFirstDirectedPaths b2 = new BreadthFirstDirectedPaths(graph, w);

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

	        return ancestor == Integer.MAX_VALUE ? -1 : ancestor;
	   }
	   private void validateVertex(int v) {
	        int V = marked.length;
	        if (v < 0 || v >= V)
	            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	    }
	   private void validateVertices(Iterable<Integer> vertices) {
	        if (vertices == null) {
	            throw new IllegalArgumentException("argument is null");
	        }
	        int V = marked.length;
	        for (int v : vertices) {
	            if (v < 0 || v >= V) {
	                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	            }
	        }
	    }

	   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	   /**
	    * This method is used for finding the shortest ancestral path.
	    * @param v vertex
	    * @param w vertex
	    * @return length between v and w
	    */
	   public int length(Iterable<Integer> v, Iterable<Integer> w) {
		   int min = Integer.MAX_VALUE;
			BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(graph, v);
			BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(graph, w);
			for (int i = 0; i < graph.V(); i++) {
				//check whether there is a path or not first
				if(bfdv.hasPathTo(i) && bfdw.hasPathTo(i)) {
					if ( bfdv.distTo(i) + bfdw.distTo(i) < min) {
						min = bfdw.distTo(i) + bfdv.distTo(i);
					}
				}
			}
			if (min == Integer.MAX_VALUE) return -1;
			return min;
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
			int min = Integer.MAX_VALUE;
			BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(graph, v);
			BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(graph, w);
			for (int i = 0; i < graph.V(); i++) {
				if(bfdv.hasPathTo(i) && bfdw.hasPathTo(i)) {
					if ( bfdv.distTo(i) + bfdw.distTo(i) < min) {
						min = bfdv.distTo(i) + bfdw.distTo(i);
						ancestor = i;
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
//	   public static void main(String[] args) {
//		   ArrayList<String> arr2 = new ArrayList<String>();
//		   	 File file = new File("digraph1.txt"); 
//			     Scanner sc = new Scanner(file);
//			     while (sc.hasNextLine()) {
//					 String str = sc.nextLine();
//					 arr2.add(str);
//		         }
//			     int V = Integer.parseInt(arr2.get(0));
////			     int E = Integer.parseInt(arr2.get(1));
//			     Digraph graph = new Digraph(V);
//			     for (int i = 2; i < V; i++) {
//						String str[] = arr2.get(i).split(" ");
//						int v = Integer.parseInt(str[0]);
//						int w = Integer.parseInt(str[1]);
//						 graph.addEdge(v,w);
//					      }
//			     for(int v = 0; v < graph.V(); v++) {
//			 		System.out.print(v+ "-------------->");
//			 			for(int w : graph.adj(v)) {
//			 				System.out.print(w+" ");
//			 			}
//			 			System.out.println();
//	   }
//			     SAP s = new SAP(graph);
//			     System.out.println(s.length(9, 11));
//			     System.out.println(s.ancestor(9, 11));
//	}
}
