package wordnet;

import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * This class is used to give the graph of hypernyms
 * @author Divya
 *
 */

public class WordNet2 {
    /**
     * Array list for storing hypernyms
     */
	static ArrayList<String> arr2 = new ArrayList<String>();
	/**
	 * This method is used for reading the hypernym.txt file and store.
	 * the lines into array list
	 * @param fileName
	 * @throws IOException
	 */
	 public void parseHypernyms(String fileName) throws IOException {
		 File file = new File(fileName); 
	     Scanner sc = new Scanner(file);
	     while (sc.hasNextLine()) {
			 String str = sc.nextLine();
			 arr2.add(str);
	     }
		 }
	 /**
	  * Main method where we actually implement the graph.
	  * @param args
	  * @throws IOException
	  */
	 public static void main(String[] args) throws IOException {
		 WordNet2 obj = new WordNet2();
		obj.parseHypernyms("hypernyms.txt");
		DiGraph graph = new DiGraph(arr2.size());
		for (int i = 0; i < arr2.size(); i++) {
			String str[] = arr2.get(i).split(",");
			int v = Integer.parseInt(str[0]);
			for (int j = 1; j < str.length; j++) {
				int w = Integer.parseInt(str[j]);
			    graph.addEdge(v,w);
		      }
		    }
		/**
		 * Printing of directed graph.
		 */
		for(int v = 0; v < graph.V(); v++) {
//			System.out.println(v);
			for(int w : graph.adj(v)) {
				System.out.println(v + "-------------->" + w);
			}
		}
		 }
}

