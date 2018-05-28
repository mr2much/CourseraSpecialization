
/**
 * Write a description of WeatherParser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.List;
import java.io.IOException;
import java.nio.file.Paths;

public class WeatherParser {
    public FileResource getEmptyFileResource() {
        return new FileResource();
    }
    
    public FileResource getFileResourceFor(String filename) {
        return new FileResource(filename);
    }
    
    public CSVParser getCSVParser(FileResource resource) {
        if (resource != null) {
            return resource.getCSVParser();
        }
        
        return null;
    }
    
    public void test() throws IOException {
        FileResource fr = new FileResource();
        CSVParser parser = getCSVParser(fr);
        
        nullTest(fr, parser);
        
        // TODO: read all files in path using DirectoryResource()
        // TODO: get average records of all files
        // TODO: test if record count is lower than average for a given file
        
        testRecordCount(parser.getRecords());
        testAverages();
        
        // TODO: change getMaxTemp method to return CSVRecord with
        // highest temperature, instead of returning a float
        testMaxTemp();
        
        System.out.println("Tests finished.");
    }
    
    private void nullTest(FileResource fr, CSVParser parser) {
        testNotNull(fr);
        testNotNull(parser);
        
        System.out.println("Finished null testing.");
    }
    
    public void testNotNull(Object obj) {
        if(obj == null) {
            System.out.println(obj.getClass().getSimpleName() + " is null.");
        }
    }
    
    public void testRecordCount(List<CSVRecord> records) {
        if(records == null) {
            System.out.println("No list.");
        }
        
        if (records.size() == 0) {
            System.out.println("No data to read.");
        }
        
        if (records.size() < 23) {
            System.out.println("Possible error, number of records is " +
                records.size());
        }
        
        System.out.println("Finished counting records.");
    }
    
    public void testAverages() {
        FileResource fr = getFileResourceFor(
            Paths.get("nc_weather/2012/weather-2012-01-01.csv").toString());
        float value = getAverageTemp(fr.getCSVParser());
        
        if (value < 52.61) {
            System.out.println("Expected 52.61 but got " + value);
        }
        
        value = getAverageTemp(null);
        
        if (value != 0.0f) {
            System.out.println("Value should be 0.0, got " + value);
        }
        
        System.out.println("Finished testing average temperature.");
    }
    
    public float getAverageTemp(CSVParser parser) {
        
        if (parser == null) {
            return 0.0f;
        }
        
        int recordCount = 0;
        float tempTotal = 0.0f;
        
        for (CSVRecord record : parser) {
            String temp = record.get("TemperatureF");
            
            float t = Float.parseFloat(temp);
            
            tempTotal += t;
            
            recordCount++;
        }
        
        return tempTotal / recordCount;
    }
    
    public void testMaxTemp() {
        FileResource fr = getFileResourceFor(
            Paths.get("nc_weather/2012/weather-2012-01-01.csv").toString());
        float value = getMaxTemp(fr.getCSVParser());
        
        if (value < 66.9) {
            System.out.println("Expected 66.9 but got " + value);
        }
        
        value = getMaxTemp(null);
        
        if (value != 0.0f) {
            System.out.println("Expected 0.0f got " + value);
        }
        
        System.out.println("Finished testing max temperature");
    }
    
    public float getMaxTemp(CSVParser parser) {
        
        if (parser == null) {
            return 0.0f;
        }
        
        float max = 0.0f;
        
        for (CSVRecord record : parser) {
            String tempStr = record.get("TemperatureF");
            float currentTemp = Float.parseFloat(tempStr);
            
            if (currentTemp > max) {
                max = currentTemp;
            }
        }
        
        return max;
    }
}
