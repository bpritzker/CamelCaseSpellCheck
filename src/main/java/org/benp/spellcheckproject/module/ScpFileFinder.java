package org.benp.spellcheckproject.module;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ben on 7/6/2017.
 */
public class ScpFileFinder {

    final Logger logger = LoggerFactory.getLogger(ScpFileFinder.class);


    public Collection<File> findFilesToScan(File topLevelSearch) {

        // these are the file extensions to search
        List<String> fileExtensionsToInclude = buildFileExtensionsToInclude();
        IOFileFilter ioFileFilter = new SuffixFileFilter(fileExtensionsToInclude);

        // These are the directories to skip when searching
        List<String> fileDirectoriesToExclude = buildFileDirectoriesToExclude();
        NameFileFilter nameFileFilter = new NameFileFilter(fileDirectoriesToExclude);
        NotFileFilter notFileFilter = new NotFileFilter(nameFileFilter);

        // find the files....
        Collection<File> resultFilesToScan = FileUtils.listFiles(topLevelSearch, ioFileFilter, notFileFilter);
        logger.info("Total Files to Scan: <{}>", resultFilesToScan.size());
        return resultFilesToScan;

    }


    private List<String> buildFileExtensionsToInclude() {
        List<String> resultFileExtensionsToInclude = new ArrayList<String>();
        resultFileExtensionsToInclude.add("java");
        resultFileExtensionsToInclude.add("xml");
        resultFileExtensionsToInclude.add("txt");
        resultFileExtensionsToInclude.add("conf");
        resultFileExtensionsToInclude.add("md"); // Readme
        return resultFileExtensionsToInclude;
    }



    private List<String> buildFileDirectoriesToExclude() {
        List<String> resultFileDirectoriesToExclude = new ArrayList<String>();
        resultFileDirectoriesToExclude.add("target");
        resultFileDirectoriesToExclude.add(".");
//        resultFileDirectoriesToExclude.add("target");
//        resultFileDirectoriesToExclude.add("target");
        return  resultFileDirectoriesToExclude;
    }


}
