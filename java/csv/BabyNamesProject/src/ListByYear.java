package src;


/**
 * Write a description of ListByYear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
    
    public RankedName whatIsNameInYear(String name, int year, int newYear, 
        Gender gender) {
        RankedName rankedName = null;
        
        int rank = getRank(year, name, gender);
        
        rankedName = getName(newYear, rank, gender);
        
        return rankedName;
    }
    
    public int yearOfHighestRank(String name, Gender gender) {
        int highestRank = Integer.MAX_VALUE;
        int yearOfHighestRank = -1;
        // iterate over the map
        Iterator<Map.Entry<Integer, NamesList>> it = 
            mapByYear.entrySet().iterator();
            
        while (it.hasNext()) {
            Map.Entry<Integer, NamesList> pair = it.next();

            int year = pair.getKey();            
            int rank = getRank(year, name, gender);
            
            if (rank != -1 && rank < highestRank) {
                highestRank = rank;
                yearOfHighestRank = year;
            }
        }

        return yearOfHighestRank;
    }
}
