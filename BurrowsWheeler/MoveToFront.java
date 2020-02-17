
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int lgR = 8;
    public static void encode() {
        char[] ascii =  new char[256];
        for (int i = 0; i < 256; i++) {
            ascii[i] = (char)i;
        }

        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            char temp = ch;
            char temp2 = ch;
            for (int i = 0; i < 256; i++) {
                if (ch == ascii[i]) {
                    BinaryStdOut.write(i, lgR);
                    ascii[i] = temp;
                    break;
                }
                temp2 = ascii[i];
                ascii[i] = temp;
                temp = temp2;
            }
        }
        BinaryStdOut.close();
    }
    

    public static void decode() {
        char[] ascii =  new char[256];
        for (int i = 0; i < 256; i++) {
            ascii[i] = (char)i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            char temp = ascii[ch];
            char temp2 = ascii[ch];
            BinaryStdOut.write(ascii[ch], lgR);
            for (int i = 0; i < 256; i++) {
                if (ascii[ch] == ascii[i]) {
                    ascii[i] = temp2;
                    break;
                }
                temp2 = ascii[i];
                ascii[i] = temp;
                temp = temp2;
            }
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args.length == 0) return;
        if ("-".equals(args[0])) {
            encode();
        } else if ("+".equals(args[0])) {
            decode();
        }
    }
}
