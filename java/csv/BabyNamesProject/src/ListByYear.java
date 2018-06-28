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
import edu.duke.DirectoryResource;
import org.apache.commons.csv.CSVParser;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.File;

public class ListByYear {
    Map<Integer, NamesList> mapByYear;
    FileManager fileManager;
    String workingDir;
    String filenamePattern;
    
    {
        mapByYear = new HashMap<>();
        fileManager = new FileManager();
    }
    
    public ListByYear(String workingDir, String pattern) {
        this.workingDir = workingDir;
        this.filenamePattern = pattern;
    }
    
    public void loadListForYear(int year) {
        // String pattern = "res/us_babynames_test/yob%dshort.csv";
        fileManager.setFilename(String
            .format(workingDir + filenamePattern, year));
        CSVParser parser = fileManager.getCSVParser();
        NamesList namesList = fileManager.getNamesListFrom(parser);
        
        mapByYear.put(new Integer(year), namesList);
    }
    
    public void loadRangeOfFiles() {
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            int year = extractYear(f.getName());
            loadListForYear(year);
        }        
    }
    
    private int extractYear(String text) {
        text = text.replaceAll("\\D+", "");
        
        return Integer.parseInt(text);
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
    
    public double getAverageRank(String name, Gender gender) {        
        double averageRank = -1.0;
        int count = 0;
        
        Iterator<Map.Entry<Integer, NamesList>> it =
            mapByYear.entrySet().iterator();
        
        int rankTotal = 0;
        
        while (it.hasNext()) {
            Map.Entry<Integer,NamesList> pair = it.next();
            
            int year = pair.getKey();
            int rank = getRank(year, name, gender);
            
            if (rank != -1) {
                rankTotal += rank;
                count++;
            }
        }
        
        if (count != 0) {
            averageRank = rankTotal / (double) count;
        }
        
        // rounds down a decimal number
        // based on user593581 answer on StackOverflow
        // https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
        BigDecimal bd = BigDecimal.valueOf(averageRank)
            .setScale(2, RoundingMode.DOWN);
        averageRank = bd.doubleValue();
        
        return averageRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, 
        Gender gender) {            
        int totalBirths = 0;
        int rank = getRank(year, name, gender);
        RankedName nameToCheck = getName(year, rank, gender);
        int nameCount = nameToCheck.getCount();
        
        NamesList nl = getListForYear(year);
        List<RankedName> males = nl.getAll(gender);
        
        for (RankedName currentName : males) {
            if (currentName == nameToCheck) {
                continue;
            }
            
            if (currentName.getCount() >= nameCount) {
                totalBirths += currentName.getCount();
            }
        }       

        return totalBirths;
    }
}
