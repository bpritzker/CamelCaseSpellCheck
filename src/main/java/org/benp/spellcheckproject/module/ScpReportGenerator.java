package org.benp.spellcheckproject.module;

import org.benp.spellcheckproject.data.ScpFileScanResult;
import org.benp.spellcheckproject.data.ScpLineScanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by Ben on 7/6/2017.
 */
public class ScpReportGenerator {

    private final Logger logger = LoggerFactory.getLogger(ScpReportGenerator.class);

    public void createReport(Collection<ScpFileScanResult> scanResults) {

        for (ScpFileScanResult currScanResult : scanResults) {
            if (currScanResult.getMissSpellings() != null && currScanResult.getMissSpellings().size() > 0) {
                for (ScpLineScanResult currMisspelling : currScanResult.getMissSpellings()) {
                    logger.info(currScanResult.getFile().getName() + "  --  " + currMisspelling.getLineNumber() + "  --  " + currMisspelling.getWordsFound().get(0));
//                    logger.info("(" + currScanResult.getFile().getName() + ":" + currMisspelling.getLineNumber() + ")");

                }
            }

        }
    }
}
