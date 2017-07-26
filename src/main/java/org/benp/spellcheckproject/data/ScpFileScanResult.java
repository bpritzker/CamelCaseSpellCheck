package org.benp.spellcheckproject.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben P
 */
public class ScpFileScanResult {


    private File file;
    private List<ScpLineScanResult> missSpellings;

    public List<ScpLineScanResult> getMissSpellings() {
        return missSpellings;
    }

    public void setMissSpellings(List<ScpLineScanResult> missSpellings) {
        this.missSpellings = missSpellings;
    }


    public void addMissSpelling(ScpLineScanResult missSpelling) {
        if (this.missSpellings == null) {
            this.missSpellings = new ArrayList<ScpLineScanResult>();
        }
        this.missSpellings.add(missSpelling);


    }

    public File getFile() {
        return file;
    }


    public void setFile(File file) {
        this.file = file;
    }




}
