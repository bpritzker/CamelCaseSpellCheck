/**
 * Copyright 2014 Morris "Ben" Pritzker
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.benp.utils.ccsc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.engine.Word;
import com.swabunga.spell.event.SpellChecker;


/**
 * See README.txt for explanation of what this program does.
 * 
 * 
   Copyright 2014 Morris "Ben" Pritzker

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 * 
 * @author Ben Pritzker
 *
 */
public class CamelCaseSpellCheck
{
	
    private int MIN_WORD_LENGTH = 4; // minimum word length to check, if it's 3 characters (or less) don't bother checking
    private String dictionaryFileLocation = "dictionary/DictionaryPruned.txt";
    SpellChecker spellChecker = null;
    

	public static void main(String[] args)
    {
    	CamelCaseSpellCheck camelCaseSpellCheck = new CamelCaseSpellCheck();
    	camelCaseSpellCheck.run(args);
    }	    

    
    
    public void run(String[] wordsFromCommandLine)
    {
    	try
    	{
	    	init();
	    	String allWordsToCheck = buildSingleString(wordsFromCommandLine);
	        List<CamelCaseSpellCheckResult> results = spellCheckString(allWordsToCheck);
	        displayToStandardOut(results);
    	} catch (Exception e)  // The program is too simple to worry about handling each type of exception
    	{
    		e.printStackTrace();
    	}
	}

    protected void init() throws Exception 
    {
    	SpellDictionaryHashMap dictionary = null;
    	dictionary = new SpellDictionaryHashMap(new File(dictionaryFileLocation));
    	spellChecker = new SpellChecker(dictionary);
	}



	/**
     * Simple method to append all the words in an array to a string
     * @param wordsFromCommandLine
     * @return
     */
	private String buildSingleString(String[] strings)
	{
    	// append words to a single string
        StringBuilder sb = new StringBuilder();
        for (String currentWord : strings)
        {
            sb.append(currentWord + " ");
        }
        return sb.toString();
	}



	protected List<CamelCaseSpellCheckResult> spellCheckString(String word)
	{
        List<CamelCaseSpellCheckResult> result = new ArrayList<CamelCaseSpellCheckResult>();
        if (word != null) 
        { 
        	List<String> camelCaseSplitList = splitCamelCase(word);
            for (String currentWord : camelCaseSplitList) 
            {
            	CamelCaseSpellCheckResult ccscCurrentResult = spellCheckWord(currentWord);
            	result.add(ccscCurrentResult);
            }
        }
        return result;
    }

	
    @SuppressWarnings("unchecked")
	protected CamelCaseSpellCheckResult spellCheckWord(String word)
    {
    	CamelCaseSpellCheckResult result = new CamelCaseSpellCheckResult(word);
	    
    	if (word.length() < MIN_WORD_LENGTH)
    	{
    		result.setMatchType(CamelCaseSpellCheckMatchType.NOT_CHECKED);
    	}
    	else if (spellChecker.isCorrect(word))
        {
        	result.setMatchType(CamelCaseSpellCheckMatchType.FOUND);
        } else // word not found
        {
        	result.setMatchType(CamelCaseSpellCheckMatchType.NOT_FOUND);
        	result.setSuggestedSpellings(spellChecker.getSuggestions(word, 10));
        }  	            
    	return result;
	}

    
    
	protected String convertCorrectionsToStringForSystemOut(List<Word> corrections)
	{
		if (corrections.size() == 0)
		{
			return "No suggestions found";
		}
		
        StringBuilder sb = new StringBuilder();
        for (Word currentCorrection : corrections)
        {
            sb.append(currentCorrection.getWord() + " ");
        }
        return sb.toString();
    }

    
    /**
     * This method will use a regular expression to split a all the words out.
     * It will split on any delimiter including the start of a new camel case word
     * @param word
     * @return
     */
    protected List<String> splitCamelCase(String word)
    {
         List<String> allMatches = new ArrayList<String>();
         Matcher m = Pattern.compile("([a-zA-Z][a-z]+)|([A-Z][A-Z]*)").matcher(word);
         while (m.find()) {
           allMatches.add(m.group());
         }
        return allMatches;
    }

    private void displayToStandardOut(List<CamelCaseSpellCheckResult> resultList)
    {
        System.out.println("\nCamel Case Spell Check Results:\n");
        String suggestions;
        for (CamelCaseSpellCheckResult currentResult : resultList)
        {
            suggestions = "";
            if (currentResult.getMatchType() == CamelCaseSpellCheckMatchType.NOT_FOUND)
            {
                System.out.print("NOT FOUND   ");
                suggestions = " - " + convertCorrectionsToStringForSystemOut(currentResult.getSuggestedSpellings());
            } else if (currentResult.getMatchType() == CamelCaseSpellCheckMatchType.FOUND)
            {
                System.out.print("CORRECT     ");
            } else if (currentResult.getMatchType() == CamelCaseSpellCheckMatchType.NOT_CHECKED)
            {
                System.out.print("NOT CHECKED ");
            }

            System.out.println("<"+currentResult.getOriginalWord()+">" + suggestions);
        }
        System.out.println("\n");
    }


    
    
    public String getDictionaryFileLocation()
    {
		return dictionaryFileLocation;
	}

	public void setDictionaryFileLocation(String dictionaryFileLocation)
	{
		this.dictionaryFileLocation = dictionaryFileLocation;
	}	
}
