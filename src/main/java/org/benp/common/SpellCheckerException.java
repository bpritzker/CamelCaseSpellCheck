package org.benp.common;

/**
 * Created by Ben on 6/24/2017.
 */
public class SpellCheckerException extends Exception {

    public SpellCheckerException(String customMessage, Exception e) {
        super(customMessage, e);
    }
}
