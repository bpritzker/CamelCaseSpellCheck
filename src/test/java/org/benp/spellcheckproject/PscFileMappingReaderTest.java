package org.benp.spellcheckproject;

import org.benp.spellcheckproject.module.ScpFileLoader;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ben P
 */
public class PscFileMappingReaderTest extends ScpFileLoader {


    @Test
    public void isBlankOrComment() throws Exception {

        assertFalse(isBlankOrComment("name->value"));

        assertTrue(isBlankOrComment(null));
        assertTrue(isBlankOrComment(""));
        assertTrue(isBlankOrComment("#"));
        assertTrue(isBlankOrComment(" #"));
        assertTrue(isBlankOrComment("# Comment here"));

    }

}