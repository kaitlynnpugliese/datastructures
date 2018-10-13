import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Problem2 {

	//constructor
	public static AvlTree<String> tree = new AvlTree<>();

	public static void main(String[] args){
		try {
			File file = new File("frank.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;

			//use l to index what line you are on
			int l = 1;
			//make sure line is not null
			while ((line = bufferedReader.readLine()) != null) {
				//splits the line by white space and
				//removes punctuation and capitalization
				String[] splitStr = line.split(" ");
				splitStr = clean(splitStr);


				//calls indexWord method on each element in a line
				int len = splitStr.length;
				for (int i=0; i < len; i++){
					String word = splitStr[i];
					indexWord(word, l);
				}
				l++;
			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tree.printTree();
	}

	//method to "clean" splitstr list
	//(clean meaning put to lower case and remove punctuation)
	private static String[] clean(String[] unclean){
		int length = unclean.length;
		String[] cleanStr = new String[length];
		for (int i=0; i< length; i++){
			String old = unclean[i];
			old = old.toLowerCase();
			String next = old.replaceAll("\\p{Punct}", "");
			cleanStr[i] = next;
		}
		return cleanStr;
	}

	//method to add word to tree
	public static void indexWord(String word, int l){
		if (tree.contains(word)){
			//add line number the word is in to the
			//linked list in the node for that word
			tree.rep(word, l);
		}
		else{
			//make linked list with line number
			LinkedList<Integer> temp = new LinkedList<Integer>();
			temp.add(l);
			//add the word with the list into the tree
			tree.insert(word, temp);
		}
	}

	//looks up a word in the Avl Tree and returns
	//the list of lines associated with its node
	public static void getLinesForWord(String word){
		if(tree.contains(word)){
			returnLinesForWord(word);
		}
	}

	public static LinkedList<Integer> returnLinesForWord(String word){
		return tree.returnList();
	}

	public static void printIndex(){
		tree.printTree();
	}






}
