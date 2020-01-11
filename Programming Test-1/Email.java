import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//import wordnet.Digraph;
/**
 * This class is used to find the top 10 popular mails
 * @author Divya
 *
 */
public class Email {
	/**
	 * attributes
	 */
	static Digraph graph;
	static ArrayList<String> arr2 = new ArrayList<String>();
	private static HashMap<Integer, String> emailIds;
	private static HashMap<Integer, Integer> degrees;
	/**
	 * Initialisation of constructor
	 */
	public Email() {
		emailIds = new HashMap<>();
		degrees = new HashMap<>();
	}
	/**
	 * This method is used for storing emails and its ids into hashmap.
	 * @param fileName
	 * @throws IOException
	 */
	public void parseEmails(String fileName) throws IOException {
		 File file = new File(fileName); 
	     Scanner sc = new Scanner(file);
	     while (sc.hasNextLine()) {
			 String str = sc.nextLine();
//			 System.out.println(str);
			 String[] arr = str.split(";",2);
			 int i = Integer.parseInt(arr[0]);
			 emailIds.put(i, arr[1]);
	     }
	}
	/**
	 * This method creates the graph on email=logs.txt
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public void parseEmailLogs(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String str = sc.nextLine();
			String[] arr = str.split(" ",2);
			String[] t = arr[1].split(",", 2);
			String s = t[0] + " " + t[1].substring(4);
			arr2.add(s);
		}
		graph = new Digraph(arr2.size());
		for (int i = 0; i < arr2.size(); i++) {
			String str[] = arr2.get(i).split(" ");
			int v = Integer.parseInt(str[0]);
			for (int j = 1; j < str.length; j++) {
				int w = Integer.parseInt(str[j]);
			    graph.addEdge(v,w);
		      }
		}
		
	}
	     /**
	      * This method prints top n mails which recieves the highest number of mails.
	      * @param n
	      */
	     public void topNmails(int n) {
	    	 int[] arr = new int[graph.V()];
	    	 for(int i = 0; i < graph.V(); i++) {
	    		  arr[i] = graph.indegree(i);
	    		 }
	    	 for(int i = 0; i < graph.V(); i++) {
	    		  degrees.put(graph.indegree(i),i);
	    		 }
	    	 Arrays.sort(arr);
	    	 ArrayList<Integer> all = new ArrayList<Integer>();
	    	 for(int i = 1; i < n+1; i++ ) {
	    	 all.add(arr[graph.V()-i]);
	    	 }
	    	 for(int j : all) {
	    		 int l = degrees.get(j);
	    		 System.out.println(emailIds.get(l) + " " + j);
	    	 }
	     }
	     /**
	      * This is the main method for unit testing.
	      * @param args
	      * @throws IOException
	      */
	     public static void main(String[] args) throws IOException {
	    	 Email e = new Email();
	    	 e.parseEmailLogs("C:\\Users\\Divya\\Downloads\\ADS-2Exam-1\\ADS - 2 Exam - 1\\email-logs.txt");
	    	 e.parseEmails("C:\\Users\\Divya\\Downloads\\ADS-2Exam-1\\ADS - 2 Exam - 1\\emails.txt");
	    	 // Top 200 mails.
             e.topNmails(200);
	    	 }
	     }

