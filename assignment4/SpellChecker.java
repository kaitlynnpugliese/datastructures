import java.io.*;
import java.util.*;

public class SpellChecker {
	
	 //constructor
	static Hashtable<String,String> dictionary; 

	public static void main(String[] args){
		//ask name of dictionary file
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the dictionary file. ");
		String dict = in.nextLine();
		//read in file and store words in hash table
		hashMaker(dict);
		
		//ask name of personal word checker file
		Scanner in2 = new Scanner(System.in);
		System.out.println("Enter the name of your personal dictionary file. ");
		String personal = in2.nextLine();
		check(personal);
			
	}
	
	//read in dictionary file and store words in hash table
	public static void hashMaker(String dictName){
		dictionary = new Hashtable<String,String>();
		//open dictionary file and read in words and store 
		//in hash table with line number
		try {
			File file = new File(dictName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			//make sure line is not null
			int l = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String word = clean(line);
				//adds word to hashtable
				String x = Integer.toString(l);
				dictionary.put(word, x);
				l++;
			}
			fileReader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//read in personal dictionary file and check for misspelled words
	public static void check(String personalName){
		try {
			File file = new File(personalName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			//create list of misspelled words
			LinkedList<String> misspelledWords = new LinkedList<String>();
			//make sure line is not null
			int l = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String word = clean(line);
				//check if word is in dictionary
				if (misspelled(word)){
					//if misspelled, add to misspelled words list
					String x = Integer.toString(l);
					String forList = "misspelled word: " + word 
							+ ", line number: " + x;
					misspelledWords.add(forList);
					System.out.println("    The following is a list of "
							+ "suggested words for the misspelt word " + word + ": ");
					//not in dictionary, check if you can add one character
					LinkedList<String> suggestionListOne = addOne(word);
					//not in dictionary, check if you can remove one character
					LinkedList<String> suggestionListTwo = removeOne(word);
					//not in dictionary, check if you can exchange adjacent characters
					LinkedList<String> suggestionListThree = exchange(word);
					System.out.println("Add One letter: " + suggestionListOne);
					System.out.println("Remove One letter: " +suggestionListTwo);
					System.out.println("Exchange two letters: " +suggestionListThree);
				}
				l++;
			}
			System.out.println("");
			System.out.println("The total list of misspelt words is: ");
			int length = misspelledWords.size();
			for (int i = 0; i < length; i++){
				System.out.println(misspelledWords.get(i));
			}
			fileReader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//check if word is in dictionary
	private static boolean misspelled(String test){
		boolean contain = dictionary.containsKey(test);
		//if key is in dictionary, return false for misspelled
		//if key is not in dictionary, return true for misspelled
		return !contain;
	}
	
	//check if you can add one character
	private static LinkedList<String> addOne(String test){
		//make linked list for new words that are in the dictionary
		LinkedList<String> validWords = new LinkedList<String>();
		int len = test.length();
		for (int i = 0; i <= (len); i++){
			String[] alphabet = new String[]{"a", 
					"b", "c", "d", "e", "f", "g", "h", "i", "j", 
					"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
					"u", "v", "w", "x", "y", "z", "'" };
			for (int j = 0; j < 27; j++){
				String newTest = test.substring(0, i) 
						+ alphabet[j] + test.substring(i, len);
				if (dictionary.containsKey(newTest)){
					validWords.add(newTest);
				}
			}
		}
		return validWords;
	}
	
	//check if you can remove one character
	private static LinkedList<String> removeOne(String test){
		LinkedList<String> validWords = new LinkedList<String>();
		int len = test.length();
		for (int i = 0; i < len; i++){
			String newTest = test.substring(0, i) + test.substring((i+1), len);
			if (dictionary.containsKey(newTest)){
				validWords.add(newTest);
			}
		}
		return validWords;
	}
	
	//check if you can exchange adjacent characters
	private static LinkedList<String> exchange(String test){
		LinkedList<String> validWords = new LinkedList<String>();
		int len = test.length();
		for (int i = 0; i < (len-1); i++){
			int j = i+1;
			//**separately test best way to do this
			String newTest = test.substring(0, i) + test.charAt(j) + 
					test.charAt(i) + test.substring(i+2, len);
			if (dictionary.containsKey(newTest)){
				validWords.add(newTest);
			}
		}
		return validWords;	
	}
	
	//upper to lower case and remove punctuation at the end of words
	private static String clean(String unclean){
			String cleanStr = "";
			if (unclean.length() > 1){
				unclean = unclean.toLowerCase();
				int length = unclean.length();
				String last = unclean.substring((length-1));
				String newLast = last.replaceAll("\\p{Punct}", "");
				cleanStr = unclean.substring(0, (length-1)) + newLast;
			}
			return cleanStr;
		}
	
	
}
