package src;


/**
 * Write a description of ListByYear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import org.apache.commons.csv.CSVParser;

public class ListByYear {
    Map<Integer, NamesList> mapByYear;
    FileManager fileManager;
    
    {
        mapByYear = new HashMap<>();
        fileManager = new FileManager();
    }
    
    public void loadListForYear(int year) {
        String pattern = "res/us_babynames_test/yob%dshort.csv";
        fileManager.setFilename(String.format(pattern, year));
        CSVParser parser = fileManager.getCSVParser();
        NamesList namesList = fileManager.getNamesListFrom(parser);
    }
    
    public NamesList getListForYear(int year) {
        NamesList nl = new NamesList();
        
        nl.insert(new RankedName("Sophia", "F", 10));
        nl.insert(new RankedName("Emma", "F", 9));
        nl.insert(new RankedName("Isabella", "F", 8));
        nl.insert(new RankedName("Olivia", "F", 7));
        nl.insert(new RankedName("Ava", "F", 6));
        
        return nl;
    }
}
