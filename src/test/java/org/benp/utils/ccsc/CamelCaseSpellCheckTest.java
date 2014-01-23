package org.benp.utils.ccsc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.swabunga.spell.engine.Word;

public class CamelCaseSpellCheckTest
{
	static CamelCaseSpellCheck camelCaseSpellCheck;

	@BeforeClass  // only want to load in dictionary file once!
	public static void initialize() throws Exception
	{	
		camelCaseSpellCheck = new CamelCaseSpellCheck();
		camelCaseSpellCheck.setDictionaryFileLocation("src/main/resources/dictionary/DictionaryPruned.txt");
		camelCaseSpellCheck.init();
	}
	
	
	
	@Test
	public void testSplitCamelCase()
	{
		List<String> actual = camelCaseSpellCheck.splitCamelCase("receivedMessageFromNotifier");
		assertEquals(4, actual.size());
		
		actual = camelCaseSpellCheck.splitCamelCase("RECEIVED_MESSAGE_FROM_NOTIFIER");
		assertEquals(4, actual.size());

	
		actual = camelCaseSpellCheck.splitCamelCase("received");
		assertEquals(1, actual.size());

	
		actual = camelCaseSpellCheck.splitCamelCase("RECEIVED");
		assertEquals(1, actual.size());

	
		actual = camelCaseSpellCheck.splitCamelCase("RECEIVED RECEIVED");
		assertEquals(2, actual.size());
}

	
	
	
	@Test
	public void testConvertCorrectionsToStringForSystemOut()
	{
		// positive test - word has multiple suggested spellings
		String expectedlWord = "recieved";
		CamelCaseSpellCheckResult ccscResult = camelCaseSpellCheck.spellCheckWord(expectedlWord);
		String actual = camelCaseSpellCheck.convertCorrectionsToStringForSystemOut(ccscResult.getSuggestedSpellings());
		assertEquals("received relieved ", actual);

		// positive test - no suggested spellings
		actual = camelCaseSpellCheck.convertCorrectionsToStringForSystemOut(new ArrayList<Word>());
		assertEquals("No suggestions found", actual);
		
	}
	
	
	@Test
	public void testSpellCheckString() throws Exception
	{
		// positive test - spelled correctly
		String expectedlWord = "TestingNotFound";
		CamelCaseSpellCheckResult actual = camelCaseSpellCheck.spellCheckWord(expectedlWord);
		assertEquals(expectedlWord, actual.getOriginalWord());
		assertEquals(CamelCaseSpellCheckMatchType.NOT_FOUND, actual.getMatchType());		
	}

	
	@Test
	public void testSpellCheckWord() throws Exception
	{
		// positive test - spelled correctly
		String expectedlWord = "Test";
		CamelCaseSpellCheckResult actual = camelCaseSpellCheck.spellCheckWord(expectedlWord);
		assertEquals(expectedlWord, actual.getOriginalWord());
		assertEquals(CamelCaseSpellCheckMatchType.FOUND, actual.getMatchType());
		
		// positive test - spelled incorrectly
		expectedlWord = "ttest";
		actual = camelCaseSpellCheck.spellCheckWord(expectedlWord);
		assertEquals(expectedlWord, actual.getOriginalWord());
		assertEquals(CamelCaseSpellCheckMatchType.NOT_FOUND, actual.getMatchType());
		assertEquals(5, actual.getSuggestedSpellings().size());
	}
}
