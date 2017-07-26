package org.benp.spellcheckproject.module;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ben on 7/6/2017.
 */
public class ScpMisspellingsLoader {

    public Map<String, String> loadMisspellingsMapping() throws Exception {

        ScpFileLoader tempReader = new ScpFileLoader();


        Map<String,String> defaultProgrammingMisspellings = tempReader.loadDefaultMappingFile("ProjectSpellCheckData/default/DefaultProgrammingMisspellings.txt");

//        Map<String, String> wikipediaRawMisspelling = tempReader.loadDefaultMappingFile("ProjectSpellCheckData/default/WikipediaListsOfCommonMisspellingsForMachines.txt");

//        resultMisspellings.put("seperator", "separator");
//        resultMisspellings.put("notificaiton", "notification");
//        resultMisspellings.put("reslut", "result");
//        resultMisspellings.put("definatley", "definitely");
//        resultMisspellings.put("lenght", "length");

        Map<String, String> resultMisspellings = new HashMap<>();
        resultMisspellings.putAll(defaultProgrammingMisspellings);
//        resultMisspellings.putAll(wikipediaRawMisspelling);


        return resultMisspellings;

    }





}
