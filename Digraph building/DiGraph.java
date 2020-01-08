package wordnet;

import edu.princeton.cs.algs4.Bag;
/**
 * The {@code Digraph} class represents a directed graph of vertices
 * @author Divya
 *
 */
public class DiGraph {
         private final int V;  // number of vertices
         private Bag<Integer>[] adj; //adj[v] = adjacency list for vertex v
         /**
          * Initializes an empty digraph with <em>V</em> vertices.
          *
          * @param  V the number of vertices
          * @throws IllegalArgumentException if {@code V < 0}
          */
         public DiGraph(int V) {
        	 this.V = V;
        	 adj = (Bag<Integer>[])new Bag[V];
        	 for(int v = 0; v < V; v++) {
        		 adj[v] = new Bag<Integer>();
        	 }
         }
         /**
          * Adds the directed edge v→w to this digraph.
          *
          * @param  v the tail vertex
          * @param  w the head vertex
          * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
          */
         public void addEdge(int v, int w) {
        	 adj[v].add(w);
         }
         /**
          * Returns the vertices adjacent from vertex {@code v} in this digraph.
          *
          * @param  v the vertex
          * @return the vertices adjacent from vertex {@code v} in this digraph, as an iterable
          * @throws IllegalArgumentException unless {@code 0 <= v < V}
          */
         public Iterable<Integer>adj(int v) {
        	 return adj[v];
         }
         /**
          * Returns the number of vertices in this digraph.
          *
          * @return the number of vertices in this digraph
          */
         public int V() {
             return V;
         }
}
