package org.benp.utils.ccsc;

import java.util.List;

import com.swabunga.spell.engine.Word;

public class CamelCaseSpellCheckResult {
    private String originalWord;
    private CamelCaseSpellCheckMatchType matchType;
    private List<Word> suggestedSpellings;
    
    public CamelCaseSpellCheckResult(String originalWord)
    {
    	this.originalWord = originalWord;
    }
    
	public String getOriginalWord() {
        return originalWord;
    }
    public void setOriginalWord(String originalWord) {
        this.originalWord = originalWord;
    }
    public CamelCaseSpellCheckMatchType getMatchType() {
        return matchType;
    }
    public void setMatchType(CamelCaseSpellCheckMatchType matchType) {
        this.matchType = matchType;
    }
    public List<Word> getSuggestedSpellings() {
        return suggestedSpellings;
    }
    public void setSuggestedSpellings(List<Word> suggestedSpellings) {
        this.suggestedSpellings = suggestedSpellings;
    }
}
