import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import sun.awt.SunHints.Value;

public class BoggleSolver {
	
	private static TrieST trie;
	private ArrayList<String> validWords;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
    	trie = new TrieST();
    	validWords = new ArrayList<String>();
    	for(String s : dictionary) {
    		trie.put(s);
    	}
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
    	int rows = board.rows();
    	int cols = board.cols();
    	String str = "";
    	boolean[][] marked = new boolean[rows][cols];
    	for(int  i = 0; i < rows; i++) {
    		for(int j = 0; j < cols; j++) {
    			str += checkForSpecialCase(board.getLetter(i,j));
    			dfs(board, marked, i, j, str);
    		}
    	}
		return validWords;
    }

    private void dfs(BoggleBoard board,boolean[][] marked, int i, int j, String key) {
    	marked[i][j] = true;
    	int rows = board.rows();
    	int cols = board.cols();
    	if (trie.contains(key)  && key.length() > 2) {
			validWords.add(key);
		}
    	if (isValidCell(i - 1, j , rows, cols) && !marked[i - 1][j] && isValidWord(key)) {
			String str = key + checkForSpecialCase(board.getLetter(i - 1, j));
			dfs(board,marked, i - 1, j, str);
			marked[i - 1][j] = false;
		}
    	if (isValidCell(i + 1, j , rows, cols) && !marked[i + 1][j] && isValidWord(key)) {
    		String str  = key + checkForSpecialCase(board.getLetter(i + 1, j));
			dfs(board,marked, i + 1, j, str);
			marked[i + 1][j] = false;
		}
    	if (isValidCell(i, j + 1 , rows, cols) && !marked[i][j + 1] && isValidWord(key)) {
    		String str  = key + checkForSpecialCase(board.getLetter(i, j + 1));
			dfs(board,marked, i, j + 1, str);
			marked[i][j + 1] = false;
		}
    	if (isValidCell(i, j - 1 , rows, cols) && !marked[i][j - 1] && isValidWord(key)) {
    		String str  = key + checkForSpecialCase(board.getLetter(i, j - 1));
			dfs(board,marked, i, j - 1, str);
			marked[i][j - 1] = false;
		}
    	if (isValidCell(i - 1, j - 1 , rows, cols) && !marked[i - 1][j - 1] && isValidWord(key)) {
    		String str  = key + checkForSpecialCase(board.getLetter(i - 1, j - 1));
			dfs(board,marked, i - 1, j - 1, str);
			marked[i - 1][j - 1] = false;
		}
    	if (isValidCell(i + 1, j + 1 , rows, cols) && !marked[i + 1][j + 1] && isValidWord(key)) {
    		String str  = key + checkForSpecialCase(board.getLetter(i + 1, j + 1));
			dfs(board,marked, i + 1, j + 1, str);
			marked[i + 1][j + 1] = false;
		}
    	if (isValidCell(i - 1, j + 1 , rows, cols) && !marked[i - 1][j + 1] && isValidWord(key)) {
    		String str  = key + checkForSpecialCase(board.getLetter(i - 1, j + 1));
			dfs(board,marked, i - 1, j + 1, str);
			marked[i - 1][j + 1] = false;
		}
    	if (isValidCell(i + 1, j - 1 , rows, cols) && !marked[i + 1][j - 1] && isValidWord(key)) {
    		String str  = key + checkForSpecialCase(board.getLetter(i + 1, j - 1));
			dfs(board,marked, i + 1, j - 1, str);
			marked[i + 1][j - 1] = false;
		}
    }
    private boolean isValidWord(String key) {
		// TODO Auto-generated method stub
    	if(trie.hasPrefix(key)) {
    		return true;
    	}
		return false;
	}
   private String checkForSpecialCase(char c) {
	   if (c == 'Q') {
			return "QU";
		}
		return c + "";
	}
	private boolean isValidCell(int i, int j, int rows, int cols) {
		if (i < 0 || i >= rows || j < 0 || j >= cols) {
			return false;
		}
		return true;
	}
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
		if(word == null) {
			return 0;
		}
		int val = (int) trie.get(word);
    	return val;
    }
    public static void main(String[] args) throws FileNotFoundException {
    	 File file = new File("C:\\Users\\Divya\\Downloads\\boggle\\dictionary-algs4.txt"); 
    	 In in = new In(file);
         String[] dictionary = in.readAllStrings();
    	BoggleSolver b = new BoggleSolver(dictionary);
    	BoggleBoard board = new BoggleBoard("board-points26539.txt");
    	for(String s : b.getAllValidWords(board)) {
    		System.out.println(s + ":" + b.scoreOf(s));
    	}
    }
}