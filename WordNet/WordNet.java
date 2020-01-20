
import java.util.ArrayList;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.In;
/**
 * This class gives the shortest ancestral path between two nouns
 * and their ancestor.
 * @author Divya
 *
 */
public class WordNet {
    // maps synset id to synsets string
    private HashMap<Integer, String> idNoun;

    // maps nouns to set of synset ids
    private HashMap<String, ArrayList<Integer>> nounId;

    private Digraph G;

    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        nounId = new HashMap<String, ArrayList<Integer>>();
        idNoun = new HashMap<Integer, String>();
        int id = -1;
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] array = line.split(",");
            id = Integer.parseInt(array[0]);
            String[] nouns = array[1].split(" ");
            ArrayList<String> List = new ArrayList<String>();
            for (String noun : nouns) {
                List.add(noun);
            }
            idNoun.put(id, array[1]);
            for (String noun : nouns) {
                if (nounId.containsKey(noun)) {
                    nounId.get(noun).add(id);
                } else {
                    ArrayList<Integer> s = new ArrayList<Integer>();
                    s.add(id);
                    nounId.put(noun, s);
                }
            }
        }

        this.G = new Digraph(id + 1);
        in = new In(hypernyms);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            int synsetId = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                G.addEdge(synsetId, Integer.parseInt(tokens[i]));
            }
        }
        sap = new SAP(G);
    }

    // returns all WordNet nouns
    /**
     * This method returns all nouns
     * @return
     */
    public Iterable<String> nouns() {
        return nounId.keySet();
    }

    // is the word a WordNet noun?
    /**
     * This methods specifies whether the given noun is valid or noy
     * @param word
     * @return boolean
     */
    public boolean isNoun(String word) {
        if (word == null) {
            throw new NullPointerException();
        }
        return nounId.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    /**
     * This method gives the distance between two nouns
     * @param nounA
     * @param nounB
     * @return
     */
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idsA = nounId.get(nounA);
        ArrayList<Integer> idsB = nounId.get(nounB);
        return sap.length(idsA, idsB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of
    // nounA and nounB in a shortest ancestral path (defined below)
    /**
     * This method returns the common ancestor of nounA and nounB in a shortest anestral path.
     * @param nounA
     * @param nounB
     * @return ancestor
     */
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idsA = nounId.get(nounA);
        ArrayList<Integer> idsB = nounId.get(nounB);
        int ancestor = sap.ancestor(idsA, idsB);
        return idNoun.get(ancestor);
    }
}