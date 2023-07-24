package SystemClasses;

import java.util.ArrayList;

public class WordSys {
	
	public static ArrayList<Integer> doesInclude(String word, String letter) {
		int 								lastIndex = 1;
		ArrayList<Integer> 					indexes = new ArrayList<Integer>();
		
		lastIndex = word.lastIndexOf(letter);
		
		while(lastIndex != -1) {			
			indexes.add(lastIndex);
			word = word.substring(0, lastIndex);
			lastIndex = word.lastIndexOf(letter);
		}
		
		return indexes;
	}
	
	
	
	
}
