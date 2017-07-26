package org.benp.spellcheckproject.module;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.benp.common.SpellCheckerException;
import org.benp.spellcheckproject.data.ScpFileScanResult;
import org.benp.spellcheckproject.data.ScpLineScanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Ben on 7/6/2017.
 */
public class ScpFileScanner {



    final Logger logger = LoggerFactory.getLogger(ScpFileScanner.class);

    private int lineCountDisplay = 100;
    private boolean breakAfterOneMisspelling = false;
    private Map<String, String> misspellingsMap;

    public ScpFileScanner(Map<String, String> misspellingsMap) {
        this.misspellingsMap = misspellingsMap;
    }

    public Collection<ScpFileScanResult> scanFiles(Collection<File> filesToScan) throws SpellCheckerException {

        int fileCounter = 1;
        Collection<ScpFileScanResult> resultScanResults = new ArrayList<ScpFileScanResult>();

        for (File currFile : filesToScan) {

            if (fileCounter % lineCountDisplay == 0) {
                logger.info("Processing File: " + fileCounter + " out of " + filesToScan.size());
            }

            ScpFileScanResult tempScanResult = scanFile(currFile);
            if (tempScanResult != null) {
                resultScanResults.add(tempScanResult);
            }
            fileCounter++;
        }
        return resultScanResults;
    }




    private ScpFileScanResult scanFile(File fileToScan) throws SpellCheckerException {
        BufferedReader reader = null;
        ScpFileScanResult resultScanResult = new ScpFileScanResult();
        resultScanResult.setFile(fileToScan);

        try {

            reader = new BufferedReader(new FileReader(fileToScan));

            String currLine = reader.readLine();
            int lineNumber = 1;
//            String foundMisSpelling = true;
            while (currLine != null) {

                if (StringUtils.isNotBlank(currLine)) {
//                    for (String currMisSpelling : misspellingsMap.keySet()) {
                        String normalizedFileLine = currLine.toUpperCase();


                        ScpLineScanResult missSpelling = findMisspellings(normalizedFileLine);

                        if (missSpelling != null) {
                            missSpelling.setLine(currLine);
                            missSpelling.setLineNumber(lineNumber);

                            resultScanResult.addMissSpelling(missSpelling);

                        }
//
//
//                    if (normalizedFileLine.contains(currMisSpelling.toUpperCase())) {
//                        foundMisSpelling = currMisSpelling;
//                        if (breakAfterOneMisspelling) {
//                            break;
//                        }
//                    }
                    }
//                }
                currLine = reader.readLine();
                lineNumber++;
            }

//            if (foundMisSpelling != null) {
//                tempScanResult.setScanResult(("Found Missspelling <" + foundMisSpelling + "> in file: " + currFile.getAbsolutePath()));
//            }
        } catch (FileNotFoundException fnfe) {
            throw new SpellCheckerException("File Not Found Exception", fnfe);
        } catch (IOException ioe) {
            throw new SpellCheckerException("IO Exception", ioe);
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return resultScanResult;
    }

    private ScpLineScanResult findMisspellings(String normalizedFileLine) {

        ScpLineScanResult resultMisspellings = null;
        for (String currMisspellingsKey : misspellingsMap.keySet()) {

            if (normalizedFileLine.contains(currMisspellingsKey.toUpperCase())) {

                if (resultMisspellings == null) {
                    resultMisspellings = new ScpLineScanResult();
                }
                resultMisspellings.addWordFound(currMisspellingsKey);

                if (breakAfterOneMisspelling) {
                    return resultMisspellings;
                }
            }

        }
        return resultMisspellings;
    }


}
