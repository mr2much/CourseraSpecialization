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
        
        mapByYear.put(new Integer(year), namesList);
    }
    
    public NamesList getListForYear(int year) {
        NamesList nl = null;
        
        Integer key = new Integer(year);
        
        if (mapByYear.containsKey(key)) {
            return mapByYear.get(key);
        }
        
        return nl;
    }
    
    public int getRank(int year, String name, Gender gender) {
        Integer key = new Integer(year);
        
        if (mapByYear.containsKey(key)) {
            NamesList nl = mapByYear.get(key);
            return nl.rankByName(name, gender);
        }
        
        return -1;
    }
    
    public RankedName getName(int year, int rank, Gender gender) {
        Integer key = new Integer(year);
        RankedName name = null;
        
        if (mapByYear.containsKey(key)) {
            NamesList nl = mapByYear.get(key);
            name = nl.getNameByRank(rank, gender);
        }
        
        if (name != null) {
            return name;
        }
        
        return new RankedName("NO NAME", "M", 0);
    }
}
