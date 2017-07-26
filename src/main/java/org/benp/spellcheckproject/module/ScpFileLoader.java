package org.benp.spellcheckproject.module;

import org.apache.commons.lang3.StringUtils;
import org.benp.common.SpellCheckerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * This is used to read in mappings files.
 * Mapping files are files that contain a name value pair but can also contain
 * blank lines and comments.
 *
 * It is based off the Wikipedia page for common misspellings. This way you can copy and paste new
 * mappings without having to reformat.
 * It also allows comments about the file to be contained in the file itself.
 * It also ignore blank lines.
 *
 * The delimiter is "->"
 * The left value is the "key" and the right value is the "value" in the map.
 *
 * Created by Ben P
 */
public class ScpFileLoader {

    final Logger logger = LoggerFactory.getLogger(ScpFileLoader.class);


    /**
     * When using this the mapping file should NOT have a slash before the directory.
     *
     * @param mappingFileName
     * @return
     * @throws Exception
     */
    public Map<String, String> loadDefaultMappingFile(String mappingFileName) throws Exception {

        InputStream mappingFileStream = getFileInputStream(mappingFileName);

        Map<String, String> resultMapping = loadFileMappings(mappingFileStream);

        return resultMapping;
    }

    public Set<String> loadDefaultSetFile(String setFileName) throws Exception {

        InputStream setFileStream = getFileInputStream(setFileName);

        Set<String> resultSet = loadFileSet(setFileStream);

        return resultSet;
    }

    private Set<String> loadFileSet(InputStream fileInputStream) throws IOException {
        Set<String> resultFileSet = new HashSet<String>();


        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String line;
            while ((line = br.readLine()) != null) {

                if (! isBlankOrComment(line)) {
                    resultFileSet.add(line.trim());
                    }
                }
            }
        return resultFileSet;
    }


    private InputStream getFileInputStream(String inMappingFileName) throws Exception {

        InputStream resultMappingFileIs;
        try {
            resultMappingFileIs = this.getClass().getClassLoader().getResourceAsStream(inMappingFileName);
        } catch (Exception e) {
//            logger.debug("ERROR: Could not find mapping file!! <{}>", inMappingFileName );
            throw new SpellCheckerException("ERROR: Could not find mapping file!!  --  " + inMappingFileName, e);
        }
        return resultMappingFileIs;
    }

    /**
     * exception
     * Yes I know throwing generic exception is not the best programming practice...
     * If this gets used I will refactor to wrap the exceptions
\     */
    private Map<String, String> loadFileMappings(InputStream fileInputStream) throws Exception {

        Map<String, String> resultFileMapping = new HashMap<String, String>();


        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String line;
            while ((line = br.readLine()) != null) {

                if (! isBlankOrComment(line)) {
                    String[] splits = line.split("->");
                    if (splits == null || splits.length != 2) {
                        logger.error("Found non blank line that is not splittable");
                    } else {
                        // If here, we have a string that was split
                        resultFileMapping.put(splits[0].trim(), splits[1].trim());
                    }
                }
            }
        }
        return resultFileMapping;
    }


    protected boolean isBlankOrComment(String line) {

        if (StringUtils.isAllBlank(line)) {
            return true;
        }

        // now check for comment...
        for (int i =0; i < line.length(); i++) {
            char currChar = line.charAt(i);
            if (currChar > 32 && currChar <= 126) {
                if (currChar == '#') {
                    return true;
                } else {
                    return  false;
                }
            }
        }
        return true;
    }


}
