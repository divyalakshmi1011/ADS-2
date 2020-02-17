
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;
public class CircularSuffixArray {
    private String inputString;
    private Integer[] array;
	private int l;
    public CircularSuffixArray(String s) {
        inputString = s;
        l = inputString.length();
        array = new Integer[l];
        for(int i  = 0 ; i < array.length; i++) {
            array[i] = i;
        }
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int first = a;
                int second = b;
                for(int i = 0; i < inputString.length(); i++) {
                    char c1 = inputString.charAt(first);
                    char c2 = inputString.charAt(second);
                    if( c1 < c2) {
                        return -1;
                    } else if(c1 > c2) {
                        return 1;
                    }
                    first++;
                    second++;
                 }
                 return 0;
            }
        });
    }

    /**
     * returns index of ith sorted suffix
     *
     * @param i
     *            the index of the ith sorted suffix
     * @return
     */
    public int index(int i) {
    	return array[i];
    }
     public static void main(String[] args) {
    	 String str = "ABRACADABRA!";
         CircularSuffixArray c = new CircularSuffixArray(str);
         for (int i = 0; i < c.l; ++i) {
             StdOut.print(c.index(i) + " ");
         }
     }

	public int length() {
		// TODO Auto-generated method stub
		return array.length;
	}
}
       
