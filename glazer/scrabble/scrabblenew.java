package glazer.scrabble;
import java.io.File;

import java.io.FileNotFoundException;

import java.util.HashSet;

import java.util.Scanner;

import java.util.Set;
public class scrabblenew {



		private Set<String> dictionary;  
		public scrabblenew(){

	 
			try{

				Scanner readFile = new Scanner (new File("./OWL.txt")); 

				dictionary = new HashSet<String>();

				while(readFile.hasNext()){

					dictionary.add(readFile.next());

					readFile.nextLine();

				}

				readFile.close();

			}

			catch(FileNotFoundException notFound){

				System.out.println("File not found");

			} 

	 

		}
		public boolean contains(String word){

			if(word==null){

					return false;

			}

			String upperCaseWord = word.toUpperCase();

			return dictionary.contains(upperCaseWord);

		}
}
