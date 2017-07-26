package org.benp.spellcheckproject;

import org.benp.common.SpellCheckerException;
import org.benp.spellcheckproject.data.ScpFileScanResult;
import org.benp.spellcheckproject.module.ScpFileFinder;
import org.benp.spellcheckproject.module.ScpFileScanner;
import org.benp.spellcheckproject.module.ScpMisspellingsLoader;
import org.benp.spellcheckproject.module.ScpReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 *
 * This program is used to scan file for common programmer misspellings.
 *
 * Created by Ben P on 6/23/2017.
 */
public class SpellCheckProject {

    private final Logger logger = LoggerFactory.getLogger(SpellCheckProject.class);



    public static void main(String[] args) {
        System.out.println("\n\nStarting...\n\n");
        SpellCheckProject projectSpellChecker = new SpellCheckProject();
        projectSpellChecker.run(args);
        System.out.println("\n\nDone.\n\n");
    }


    /**
     * args[0] - This is the directory to serach
     *
     * @param args
     */
    public void run(String[] args) {
        File topLevelDirToScan = getStartingDirectory(args);

        try {
            runReport(topLevelDirToScan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * When looking at the Spell Check Project this is where you want to start.

     * @param topLevelDirToScan
     * @throws Exception
     */
    public void runReport(File topLevelDirToScan) throws Exception {

        Map<String, String> misspellings = loadMisspellingsMap();

        Collection<File> filesToScan = findFilesToScan(topLevelDirToScan);

        Collection<ScpFileScanResult> scanResults = scanFiles(misspellings, filesToScan);

        createReport(scanResults);
    }



    private File getStartingDirectory(String[] args) {
        if (args == null || args.length < 1) {
            logger.error("Could not find strting directory in program arguments");
            return null;
        }

        File resultStartingDir = new File(args[0]);
        return resultStartingDir;
    }


    private void createReport(Collection<ScpFileScanResult> scanResults) {
        ScpReportGenerator reportGenerator = new ScpReportGenerator();
        reportGenerator.createReport(scanResults);
    }

    private Collection<ScpFileScanResult> scanFiles(Map<String, String> misspellings, Collection<File> filesToScan) throws SpellCheckerException {
        ScpFileScanner scanner = new ScpFileScanner(misspellings);
        Collection<ScpFileScanResult> resultScans = scanner.scanFiles(filesToScan);
        return resultScans;
    }

    private Collection<File> findFilesToScan(File topLevelSearch) {

        ScpFileFinder fileFinder = new ScpFileFinder();
        Collection<File> resultFilesToScan = fileFinder.findFilesToScan(topLevelSearch);
        return resultFilesToScan;
    }


    private Map<String,String> loadMisspellingsMap() throws Exception {
        ScpMisspellingsLoader misspellingsLoader = new ScpMisspellingsLoader();
        Map<String, String> resultMisspellingsMap = misspellingsLoader.loadMisspellingsMapping();
        return resultMisspellingsMap;
    }

}
