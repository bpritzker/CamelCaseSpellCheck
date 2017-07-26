package org.benp.spellcheckproject.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben P
 */
public class ScpLineScanResult {


    private int lineNumber;
    private String line;
    private List<String> wordsFound;

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public List<String> getWordsFound() {
        return wordsFound;
    }

    public void setWordsFound(List<String> wordsFound) {
        this.wordsFound = wordsFound;
    }

    public void addWordsFound(List<String> wordsFound) {
        if (this.wordsFound == null) {
            this.wordsFound = new ArrayList<String>();
        }
        this.wordsFound.addAll(wordsFound);
    }

    public void addWordFound(String wordFound) {
        if (this.wordsFound == null) {
            this.wordsFound = new ArrayList<String>();
        }
        this.wordsFound.add(wordFound);
    }



}
