package org.benp.utils.ccsc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * The dictionary I downloaded is HUGE. When I want to "ship" I don't need anything in the dictionary that is 
 * less than 4 characters since I don't really check those words. 
 * This very simple program will remove all words less than 4 characters or if there is an apostrophe since we don't check those either.
 * 
 * 
 * @author Ben Pritzker
 *
 */
public class DictionaryPruner {

	public static void main(String[] args) {
		
		DictionaryPruner dictionaryPruner = new DictionaryPruner();
		try 
		{
			dictionaryPruner.run();
			System.out.println("Done.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void run() throws Exception {
		File dictionaryToPrune = new File("C:\\_usr\\workspace\\CamelCaseSpellCheck\\src\\main\\dictionary\\Dictionary.txt");
		File prunedDictionary  = new File("C:\\_usr\\workspace\\CamelCaseSpellCheck\\src\\main\\dictionary\\DictionaryPruned.txt");
		
		Scanner scanner = new Scanner(dictionaryToPrune);
		FileWriter fw = new FileWriter(prunedDictionary.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		while (scanner.hasNext())
		{
			String line = scanner.next();
			if (line.length() > 3 && (line.indexOf("'") == -1))
			{
				bw.write(line + "\n");
			} 
			
		}
		bw.close();
		scanner.close();
	}


}
