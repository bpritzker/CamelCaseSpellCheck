package org.benp.spellcheckproject;

import java.io.File;

/**
 *
 * This class is used to run the project manually from your IDE.
 * Since it is convenient to run the program from and IDE and
 * it's helpful for debugging I include this to have a way
 * to easily run the program without needed to compile and mosre
 * importantly, you don't need to change any production code to do any testing.
 *
 *
 * Created by Ben P on 7/4/2017.
 */
public class SpellCheckProjectManualRunner extends SpellCheckProject {


    public static void main(String[] args) {
        System.out.println("\n\nStarting...\n\n");
        SpellCheckProjectManualRunner spellCheckProjectManualRunner = new SpellCheckProjectManualRunner();
        spellCheckProjectManualRunner.run(args);
        System.out.println("\n\nDone.\n\n");
    }

    @Override
    public void runReport(File topLevelDirToScan) throws Exception {

        File topLevelDirToScanHardCoded = new File("C:\\_usr\\IntelliJ\\uncommitted");
//        File topLevelDirToScanHardCoded = new File("C:\\_usr\\IntelliJ\\CamelCaseSpellCheck\\CamelCaseSpellCheck\\src\\test\\resources\\TestFiles");
        super.runReport(topLevelDirToScanHardCoded);
    }


}
