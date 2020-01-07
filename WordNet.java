package wordnet;
import java.io.*; 

class WordNet {

	public void parseSynsets(String filename) throws Exception {
		File file = new File(filename);  
		BufferedReader bufferRead = new BufferedReader(new FileReader(file));  
		String str;
		while ((str = bufferRead.readLine()) != null)
		   System.out.println(str);
	}
	
	public void parseHypernyms(String filename) throws Exception {
		File file = new File(filename);  
		BufferedReader bufferRead = new BufferedReader(new FileReader(file));  
		String str;
		while ((str = bufferRead.readLine()) != null)
		   System.out.println(str);
	}
	
	public static void main(String[] args) throws Exception{
		WordNet obj = new WordNet();
		obj.parseSynsets("C:\\Users\\Divya\\Downloads\\wordnet\\synsets.txt");
		obj.parseHypernyms("C:\\Users\\Divya\\Downloads\\wordnet\\Hypernyms.txt");
	}
}

