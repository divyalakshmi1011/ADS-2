import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

import java.io.IOException;
import java.util.ArrayList;
/**
 * This class returns the outcast of all nouns
 * @author Divya
 *
 */
public class Outcast {
	private final WordNet words;
	// constructor takes a WordNet object
	/**
	 * Initialization of constructor.
	 * @param wordnet
	 */
	   public Outcast(WordNet wordnet) {
		   words = wordnet;
	   }
	// given an array of WordNet nouns, return an outcast
	   /**
	    * In this method, given an array of WordNet nouns, return an outcast
	    * @param nouns
	    * @return
	    */
	   public String outcast(String[] nouns) {
		   String outcast = null;
	        int max = 0;
	        for (String nounA : nouns) {
	            int distance = 0;
	            for (String nounB : nouns) {
	                if (!nounA.equals(nounB)) {
	                    distance += words.distance(nounA, nounB);
	                }
	            }
	            if (distance > max) {
	                max = distance;
	                outcast = nounA;
	            }
	        }
	        return outcast;
	    }
	// see test client below
//	   public static void main(String[] args) {
//		   WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
//		   Outcast o = new Outcast(w);
//		   Iterable<String> s = w.nouns();
//		   ArrayList<String> List = new ArrayList<String>();
//		   for(String i : s) {
//			   List.add(i);
//		   }
//		   String[] nouns = new String[List.size()];
//		   for (int i =0; i < List.size(); i++) {
//	            nouns[i] = List.get(i); 
//		   }
//		   System.out.println("mmmm");
//		   System.out.println(o.outcast(nouns));
//	   }
	}
