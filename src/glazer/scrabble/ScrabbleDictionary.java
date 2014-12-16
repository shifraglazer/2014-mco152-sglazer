package glazer.scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScrabbleDictionary {
	private ArrayList<String> dictionary;

	public ScrabbleDictionary(){
		dictionary= new ArrayList<String>();
		Scanner inputFile;
		try {
			inputFile = new Scanner(new File ("./OWL.txt"));
		
		while(inputFile.hasNext()){
			dictionary.add(inputFile.next());
			inputFile.nextLine();
			
		}
		inputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean contains(String word){
	if(word==null){
		return false;
	}
		String upper=word.toUpperCase();
		return dictionary.contains(upper);
		
	}
	/*
	public static void main(String [] args){
		String word="hello";
		ScrabbleDictionary scrabble=new ScrabbleDictionary();
		boolean answer=scrabble.contains(word);
		System.out.println(answer);
	
	}
	*/
}

