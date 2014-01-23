package org.benp.utils.ccsc;

/**
 * Use found/not found instead of correct/incorrect because it's more accurate.
 * FOUND means a word was found spelled that way in the dictionary but there 
 * could have always been a typo that ended up being a word.
 * 
 * NOT_CHECKED - If the word is not long enough we don't bother checking it.
 * 
 * COMMONLY_MISSPELLED - Not implemented, not exactly YAGNI but it's just just an enum ;-)
 * 
 * @author Ben Pritzker
 *
 */

public enum CamelCaseSpellCheckMatchType {
     FOUND, NOT_FOUND, COMMONLY_MISSPELLED, NOT_CHECKED;
}
